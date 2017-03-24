package com.mornsun.crm.core.service.catalog.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.DateUtil;
import com.common.util.ExcelUtil;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.mornsun.crm.api.constant.CrmConstant;
import com.mornsun.crm.api.vo.catalog.CatalogParamVo;
import com.mornsun.crm.core.service.catalog.ICatalogService;
import com.mornsun.jsw.api.api.catalog.ICatalogApi;
import com.mornsun.jsw.api.api.param.IParamApi;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;
import com.mornsun.jsw.api.vo.param.ParamVo;

/**
 * 分类Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class CatalogServiceImpl extends BasePageHelperApiServiceImpl<CatalogVo, ICatalogApi>
		implements ICatalogService {

	@Value("${ftp.excel_path}")
	private String excelPath;

	@Autowired
	private IParamApi paramApi;

	@Autowired
	private ICatalogApi catalogApi;

	private static SimpleDateFormat formatFolder = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat formatFile = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	/**
	 * 导入Excel文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<CatalogVo> importExcelFile(HttpServletRequest request) throws Exception {
		ResultVo<CatalogVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {

				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {

					// 记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();

					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {

						// 取得当前上传文件的文件名称
						String fileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (!StringUtils.isEmpty(fileName)) {

							logger.info("import excel fileName:" + fileName);

							// 定义上传路径
							Date date = DateUtil.getInstance().getCurrDate();

							// 获取文件后缀
							String suffix = StringUtils.substringAfterLast(fileName, ".");
							logger.info("file suffix:" + suffix);

							// 文件路径
							String newFileName = formatFile.format(date) + "." + suffix;
							// 数据库保存路径
							String tmpPath = FILE_SEPARATOR + CrmConstant.CRM_FILE_IMPORT_TYPE_CATALOG + FILE_SEPARATOR
									+ formatFolder.format(date);
							// 完整路径
							String fullPath = excelPath + FILE_SEPARATOR + tmpPath;

							// 文件路径
							logger.info("excel file dir:" + fullPath);
							File localFolder = new File(fullPath);
							if (!localFolder.exists()) {
								localFolder.mkdirs();
								logger.info("create excel dir:" + fullPath);
							}

							// 文件完整路径
							String filePath = fullPath + FILE_SEPARATOR + newFileName;
							logger.info("import excel filePath:" + filePath);
							File localFile = new File(filePath);
							try {
								file.transferTo(localFile);
							} catch (IllegalStateException e) {
								logger.error(e.getMessage(), e);
							} catch (IOException e) {
								logger.error(e.getMessage(), e);
							}

							// 解析文件
							Map<Integer, String> paramNameMap = new HashMap<>();
							paramNameMap.put(0, "parentName");
							paramNameMap.put(1, "catalogName");
							paramNameMap.put(2, "paramName");
							List<CatalogParamVo> catalogVos = ExcelUtil.getInstance().readExcel(filePath,
									CatalogParamVo.class, paramNameMap);
							for (CatalogParamVo catalogParamVo : catalogVos) {

								try {
									// 处理父级分类
									CatalogVo parentCatalogVo = new CatalogVo();
									parentCatalogVo.setCatalogName(catalogParamVo.getParentName().trim());
									parentCatalogVo = catalogApi.getOne(parentCatalogVo);
									if (parentCatalogVo == null) {

										parentCatalogVo = new CatalogVo();
										parentCatalogVo.setCatalogName(catalogParamVo.getParentName().trim());
										parentCatalogVo.setParentId("-1");
										parentCatalogVo.setIsavailable(STATUS_YES);
										parentCatalogVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
										parentCatalogVo.setCreateBy("sys");
										int result = catalogApi.insert(parentCatalogVo);
										if (result > 0) {
											res = RESULT_SUCCESS;
										} else {
											res = RESULT_FAILURE;
										}

									} else {
										res = RESULT_SUCCESS;
									}

									if (res == RESULT_SUCCESS
											&& StringUtils.isNotEmpty(catalogParamVo.getCatalogName())) {
										// 处理子级分类
										CatalogVo catalogVo = new CatalogVo();
										catalogVo.setCatalogName(catalogParamVo.getCatalogName().trim());
										catalogVo = catalogApi.getOne(catalogVo);
										if (catalogVo != null) {

											catalogVo.setParentId(parentCatalogVo.getId());
											int result = catalogApi.update(catalogVo);
											if (result > 0) {
												res = RESULT_SUCCESS;
											} else {
												res = RESULT_FAILURE;
											}

										} else {

											catalogVo = new CatalogVo();
											catalogVo.setCatalogName(catalogParamVo.getCatalogName().trim());
											catalogVo.setParentId(parentCatalogVo.getId());
											catalogVo.setIsavailable(STATUS_YES);
											catalogVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
											catalogVo.setCreateBy("sys");
											int result = catalogApi.insert(catalogVo);
											if (result > 0) {
												res = RESULT_SUCCESS;
											} else {
												res = RESULT_FAILURE;
											}

										}

										if (res == RESULT_SUCCESS
												&& StringUtils.isNotEmpty(catalogParamVo.getParamName())) {

											String[] paramArray = catalogParamVo.getParamName().trim().split(",");
											for (String paramName : paramArray) {
												if (StringUtils.isEmpty(paramName)) {
													continue;
												}

												ParamVo paramVo = new ParamVo();
												paramVo.setParamName(paramName.trim());
												paramVo = paramApi.getOne(paramVo);
												if (paramVo != null) {

													paramVo.setCatalogId(catalogVo.getId());
													int result = paramApi.update(paramVo);
													if (result > 0) {
														res = RESULT_SUCCESS;
													} else {
														res = RESULT_FAILURE;
													}

												} else {

													paramVo = new ParamVo();
													paramVo.setParamName(paramName.trim());
													paramVo.setCatalogId(catalogVo.getId());
													paramVo.setIsavailable(STATUS_YES);
													paramVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
													paramVo.setCreateBy("sys");
													int result = paramApi.insert(paramVo);
													if (result > 0) {
														res = RESULT_SUCCESS;
													} else {
														res = RESULT_FAILURE;
													}

												}

											}

										} else {
											res = RESULT_SUCCESS;
										}
									}

								} catch (Exception e) {
									String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
									logger.error(msg, e);
								}

							}

						}
					}

					logger.info("import excel success");
					// 记录上传该文件后的时间
					int finaltime = (int) System.currentTimeMillis();
					logger.info("import excel time:" + (finaltime - pre));
				}
			}

		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

}
