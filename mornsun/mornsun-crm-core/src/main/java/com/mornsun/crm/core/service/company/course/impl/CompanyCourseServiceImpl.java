package com.mornsun.crm.core.service.company.course.impl;

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
import com.mornsun.crm.api.constant.AttaTypeConstant;
import com.mornsun.crm.api.constant.CrmConstant;
import com.mornsun.crm.core.service.company.course.ICompanyCourseService;
import com.mornsun.jsw.api.api.atta.IAttaApi;
import com.mornsun.jsw.api.api.company.base.ICompanyApi;
import com.mornsun.jsw.api.api.company.course.ICompanyCourseApi;
import com.mornsun.jsw.api.api.product.base.IProductApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.company.base.CompanyVo;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;

/**
 * 企业秒懂Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class CompanyCourseServiceImpl extends BasePageHelperApiServiceImpl<CompanyCourseVo, ICompanyCourseApi>
		implements ICompanyCourseService {

	@Value("${ftp.excel_path}")
	private String excelPath;

	@Autowired
	private ICompanyApi companyApi;

	@Autowired
	private IProductApi productApi;

	@Autowired
	private IAttaApi attaApi;

	@Autowired
	private ICompanyCourseApi companyCourseApi;

	private static SimpleDateFormat formatFolder = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat formatFile = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	/**
	 * 导入Excel文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<CompanyCourseVo> importExcelFile(HttpServletRequest request) throws Exception {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
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
							String tmpPath = FILE_SEPARATOR + CrmConstant.CRM_FILE_IMPORT_TYPE_COMPANY_COURSE
									+ FILE_SEPARATOR + formatFolder.format(date);
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
							paramNameMap.put(0, "companyName");
							paramNameMap.put(1, "companyBrand");
							paramNameMap.put(2, "mobile");
							paramNameMap.put(3, "productNo");
							paramNameMap.put(4, "title");
							paramNameMap.put(5, "courseType");
							paramNameMap.put(6, "attaName");
							List<com.mornsun.crm.api.vo.company.CompanyCourseVo> companyCourseVos = ExcelUtil
									.getInstance().readExcel(filePath,
											com.mornsun.crm.api.vo.company.CompanyCourseVo.class, paramNameMap);
							for (com.mornsun.crm.api.vo.company.CompanyCourseVo courseVo : companyCourseVos) {

								try {
									// 处理公司
									CompanyVo companyVo = new CompanyVo();
									companyVo.setCompanyName(courseVo.getCompanyName().trim());
									companyVo = companyApi.getOne(companyVo);
									if (companyVo == null) {

										companyVo = new CompanyVo();
										companyVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
										companyVo.setCompanyName(courseVo.getCompanyName().trim());
										companyVo.setCompanyBrand(courseVo.getCompanyBrand());
										companyVo.setMobile(courseVo.getMobile());
										companyVo.setCreateBy("sys");
										int result = companyApi.insert(companyVo);
										if (result > 0) {
											res = RESULT_SUCCESS;
										} else {
											res = RESULT_FAILURE;
										}

									} else {
										res = RESULT_SUCCESS;
									}

									if (res == RESULT_SUCCESS) {
										// 处理产品信息
										ProductVo productVo = new ProductVo();
										productVo.setProductNo(courseVo.getProductNo().trim());
										productVo = productApi.getOne(productVo);
										if (productVo != null) {

											// 处理公司秒懂
											CompanyCourseVo companyCourseVo = new CompanyCourseVo();
											companyCourseVo.setCompanyId(companyVo.getId());
											companyCourseVo.setProductId(productVo.getId());
											companyCourseVo = companyCourseApi.getOne(companyCourseVo);
											if (companyCourseVo != null) {
												continue;
											}

											companyCourseVo = new CompanyCourseVo();
											companyCourseVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
											companyCourseVo.setCompanyId(companyVo.getId());
											companyCourseVo.setProductId(productVo.getId());
											companyCourseVo.setTitle(courseVo.getTitle());
											if (StringUtils.isNotEmpty(courseVo.getCourseType())) {
												companyCourseVo.setCourseType(String.valueOf(Integer.parseInt(courseVo.getCourseType())));
											}
											companyCourseVo.setPayMoney(0);
											companyCourseVo.setCreateBy("sys");
											int result = companyCourseApi.insert(companyCourseVo);
											if (result > 0) {
												res = RESULT_SUCCESS;
											} else {
												res = RESULT_FAILURE;
											}

											// 处理秒懂附件信息
											if (StringUtils.isNotEmpty(courseVo.getAttaName())) {
												String[] attaArray = courseVo.getAttaName().trim().split(",");
												for (String attaName : attaArray) {
													if (StringUtils.isEmpty(attaName)) {
														continue;
													}

													AttaVo attaVo = new AttaVo();
													attaVo.setSourceId("-1");
													attaVo.setSourceType(AttaTypeConstant.COMPANY_COURSE.getType());
													attaVo.setFileName(attaName);
													attaVo = attaApi.getOne(attaVo);
													if (attaVo != null) {
														attaVo.setSourceId(companyCourseVo.getId());
														int count = attaApi.update(attaVo);
														if (count > 0) {
															res = RESULT_SUCCESS;
														} else {
															break;
														}
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
