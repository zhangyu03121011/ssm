package com.mornsun.crm.core.service.product.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.common.base.constant.CommonConstant;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.DateUtil;
import com.common.util.ExcelUtil;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.mornsun.crm.api.constant.AttaTypeConstant;
import com.mornsun.crm.api.constant.CrmConstant;
import com.mornsun.crm.core.service.product.IProductService;
import com.mornsun.jsw.api.api.applyarea.IApplyAreaApi;
import com.mornsun.jsw.api.api.atta.IAttaApi;
import com.mornsun.jsw.api.api.brand.IBrandApi;
import com.mornsun.jsw.api.api.catalog.ICatalogApi;
import com.mornsun.jsw.api.api.coupon.ICouponApi;
import com.mornsun.jsw.api.api.product.applyarea.IProductApplyAreaApi;
import com.mornsun.jsw.api.api.product.atta.IProductAttaApi;
import com.mornsun.jsw.api.api.product.base.IProductApi;
import com.mornsun.jsw.api.api.product.base.IProductBaseApi;
import com.mornsun.jsw.api.api.product.param.IProductParamApi;
import com.mornsun.jsw.api.api.product.replace.IProductReplaceApi;
import com.mornsun.jsw.api.api.product.tag.IProductTagApi;
import com.mornsun.jsw.api.api.tag.ITagApi;
import com.mornsun.jsw.api.api.user.coupon.IUserCouponApi;
import com.mornsun.jsw.api.vo.applyarea.ApplyAreaVo;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.brand.BrandVo;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;
import com.mornsun.jsw.api.vo.coupon.CouponVo;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;
import com.mornsun.jsw.api.vo.product.atta.ProductAttaVo;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;
import com.mornsun.jsw.api.vo.tag.TagVo;
import com.mornsun.jsw.api.vo.user.coupon.UserCouponVo;

/**
 * 产品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductServiceImpl extends BasePageHelperApiServiceImpl<ProductVo, IProductApi>
		implements IProductService {

	@Value("${ftp.excel_path}")
	private String excelPath;

	@Autowired
	private ICatalogApi catalogApi;

	@Autowired
	private IBrandApi brandApi;

	@Autowired
	private IProductApi productApi;

	@Autowired
	private IProductBaseApi productBaseApi;

	@Autowired
	private IProductAttaApi productAttaApi;

	@Autowired
	private IProductReplaceApi productReplaceApi;

	@Autowired
	private IProductParamApi productParamApi;

	@Autowired
	private IProductTagApi productTagApi;

	@Autowired
	private IProductApplyAreaApi productApplyAreaApi;

	@Autowired
	private IApplyAreaApi applyAreaApi;

	@Autowired
	private ITagApi tagApi;

	@Autowired
	private IAttaApi attaApi;
	
	@Autowired
	private IUserCouponApi userCouponApi;
	
	@Autowired
	private ICouponApi couponApi;

	private static SimpleDateFormat formatFolder = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat formatFile = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	/**
	 * 修改产品基本
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<ProductBaseVo> updateBase(ProductBaseVo obj) {
		ResultVo<ProductBaseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			if (StringUtils.isNotEmpty(obj.getProductId())) {
				boolean flag = false;
				ProductVo productVo = new ProductVo();
				productVo.setId(obj.getProductId());
				if (StringUtils.isNotEmpty(obj.getCatalogId())) {
					productVo.setCatalogId(obj.getCatalogId());
					flag = true;
				}
				if (StringUtils.isNotEmpty(obj.getBrandId())) {
					productVo.setBrandId(obj.getBrandId());
					flag = true;
				}
				if (StringUtils.isNotEmpty(obj.getProductNo())) {
					productVo.setProductNo(obj.getProductNo());
					flag = true;
				}
				
				ProductBaseVo productBaseVo = productBaseApi.getOneById(obj.getId());
				if(null != productBaseVo) {
					//当前状态
					String currentState = productBaseVo.getState();
					pushUserCoupon(obj.getId(),currentState,obj.getState(),productBaseVo.getCreateBy());
				}
				
				if (flag) {
					int count = productApi.update(productVo);
					if (count > 0) {
						res = RESULT_SUCCESS;
					}
				}
			}

			int count = productBaseApi.update(obj);
			if (count > 0) {
				res = RESULT_SUCCESS;
			}
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			res = RESULT_EXCEPTION;
		}
		resultVo.setRes(res);
		return resultVo;
	}
	
	/**
	 * 检查当前用户是否已领取过
	 * 提交产品/品牌等数据类别优惠券
	 * 没有领取过则赠送
	 * @param id
	 * @param state
	 * @param createBy
	 */
	public int pushUserCoupon(String id,String currentState,String state,String createBy) {
		int res = RESULT_SUCCESS;
		try {
			//赠送优惠券
			if (!CrmConstant.STATE_PASS.equals(currentState) && CrmConstant.STATE_PASS.equals(state)) {
				UserCouponVo userCouponVo = new UserCouponVo();
				userCouponVo.setUserId(createBy);
				userCouponVo.setIsavailable(CommonConstant.STATUS_YES);
				List<UserCouponVo> userCouponList = userCouponApi.getAll(userCouponVo);
				if(null != userCouponList) {
					boolean flag2 = false;
					for (UserCouponVo userCouponVo2 : userCouponList) {
						CouponVo couponVo = couponApi.getOneById(userCouponVo2.getCouponId());
						String couponType = couponVo.getCouponType();
						if(CrmConstant.COUPON_TYPE_UPLOAD_DATA.equals(couponType)) {
							flag2 = true;
							break;
						}
					}
					if(!flag2) {
						CouponVo couponVo = new CouponVo();
						couponVo.setCouponType(CrmConstant.COUPON_TYPE_UPLOAD_DATA);
						couponVo.setIsavailable(STATUS_YES);
						couponVo = couponApi.getOne(couponVo);
						if(null != couponVo) {
							userCouponVo.setCouponId(couponVo.getId());
							userCouponVo.setState(STATUS_NO);
							userCouponVo.setCreateBy("sys");
							userCouponVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							int insert = userCouponApi.insert(userCouponVo);
							if(insert <= 0) {
								res = RESULT_FAILURE;
							}
						} else {
							logger.info("无当前类型的可用优惠券userid="+createBy);
						}
					}
				}
			}
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			res = RESULT_EXCEPTION;
			logger.error("赠送优惠券业务异常"+e.getMessage());
		}
		return res;
	}
	/**
	 * 修改产品替换
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<ProductReplaceVo> updateReplace(ProductReplaceVo obj) {
		ResultVo<ProductReplaceVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			ProductReplaceVo productReplaceVo = productReplaceApi.getOneById(obj.getId());
			if(null != productReplaceVo) {
				//当前状态
				String currentState = productReplaceVo.getState();
				res = pushUserCoupon(obj.getId(),currentState,obj.getState(),productReplaceVo.getCreateBy());
			}
			int count = productReplaceApi.update(obj);
			if (count > 0) {
				res = RESULT_SUCCESS;
			}
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			res = RESULT_EXCEPTION;
		}
		resultVo.setRes(res);
		return resultVo;
	}

	/**
	 * 修改产品附件
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<ProductAttaVo> updateAtta(ProductAttaVo obj) {
		ResultVo<ProductAttaVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			ProductAttaVo productAttaVo = productAttaApi.getOneById(obj.getId());
			if(null != productAttaVo) {
				//当前状态
				String currentState = productAttaVo.getState();
				res = pushUserCoupon(obj.getId(),currentState,obj.getState(),productAttaVo.getCreateBy());
			}
			int count = productAttaApi.update(obj);
			if (count > 0) {
				res = RESULT_SUCCESS;
			}
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			res = RESULT_EXCEPTION;
		}
		resultVo.setRes(res);
		return resultVo;
	}

	/**
	 * 修改产品参数
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<ProductParamVo> updateParam(ProductParamVo obj) {
		ResultVo<ProductParamVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			ProductParamVo productParamVo = productParamApi.getOneById(obj.getId());
			if(null != productParamVo) {
				//当前状态
				String currentState = productParamVo.getState();
				res = pushUserCoupon(obj.getId(),currentState,obj.getState(),productParamVo.getCreateBy());
			}
			int count = productParamApi.update(obj);
			if (count > 0) {
				res = RESULT_SUCCESS;
			}
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			res = RESULT_EXCEPTION;
		}
		resultVo.setRes(res);
		return resultVo;
	}

	/**
	 * 查询产品基本分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductBaseVo> pagination(ProductBaseVo obj, PageListVo<ProductBaseVo> page,
			HttpServletRequest request) {
		PageListVo<ProductBaseVo> resultVo = new PageListVo<>();
		try {
			resultVo = productBaseApi.getPage(obj, page);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询产品替换分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductReplaceVo> pagination(ProductReplaceVo obj, PageListVo<ProductReplaceVo> page,
			HttpServletRequest request) {
		PageListVo<ProductReplaceVo> resultVo = new PageListVo<>();
		try {
			resultVo = productReplaceApi.getPage(obj, page);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询产品附件分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductAttaVo> pagination(ProductAttaVo obj, PageListVo<ProductAttaVo> page,
			HttpServletRequest request) {
		PageListVo<ProductAttaVo> resultVo = new PageListVo<>();
		try {
			resultVo = productAttaApi.getPage(obj, page);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询产品参数分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductParamVo> pagination(ProductParamVo obj, PageListVo<ProductParamVo> page,
			HttpServletRequest request) {
		PageListVo<ProductParamVo> resultVo = new PageListVo<>();
		try {
			resultVo = productParamApi.getPage(obj, page);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 导入产品信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ResultVo<ProductVo> importExcelFile(HttpServletRequest request, String type) throws Exception {
		ResultVo<ProductVo> resultVo = new ResultVo<>();
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
							String tmpPath = FILE_SEPARATOR + CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT + FILE_SEPARATOR
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

							// 解析excel
							if (type.equals(CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT_BASE)) {
								importBaseExcelFile(resultVo, filePath);
							} else if (type.equals(CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT_REPLACE)) {
								importReplaceExcelFile(resultVo, filePath);
							} else if (type.equals(CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT_ATTA)) {
								importAttaExcelFile(resultVo, filePath);
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
			resultVo.setRes(res);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		
		return resultVo;
	}

	/**
	 * 解析产品基本信息
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void importBaseExcelFile(ResultVo<ProductVo> resultVo, String filePath) throws Exception {
		int res = RESULT_FAILURE;
		// 解析文件
		Map<Integer, String> paramNameMap = new HashMap<>();
		paramNameMap.put(0, "productNoName");
		paramNameMap.put(1, "catalogName");
		paramNameMap.put(2, "brandName");
		paramNameMap.put(3, "tagName");
		paramNameMap.put(4, "productApplyArea");
		paramNameMap.put(5, "productState");
		paramNameMap.put(6, "productDesc");

		List<com.mornsun.crm.api.vo.product.ProductBaseVo> productBaseVos = ExcelUtil.getInstance().readExcel(filePath,
				com.mornsun.crm.api.vo.product.ProductBaseVo.class, paramNameMap);
		for (com.mornsun.crm.api.vo.product.ProductBaseVo productBaseVo : productBaseVos) {
			try {
				// 处理分类
				CatalogVo catalogVo = new CatalogVo();
				catalogVo.setCatalogName(productBaseVo.getCatalogName().trim());
				catalogVo = catalogApi.getOne(catalogVo);
				if (catalogVo == null) {

					catalogVo = new CatalogVo();
					catalogVo.setCatalogName(productBaseVo.getCatalogName().trim());
					catalogVo.setParentId("-1");
					catalogVo.setCreateBy("sys");
					catalogVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
					int result = catalogApi.insert(catalogVo);
					if (result > 0) {
						res = RESULT_SUCCESS;
					} else {
						res = RESULT_FAILURE;
					}

				} else {
					res = RESULT_SUCCESS;
				}

				// 处理品牌
				BrandVo brandVo = new BrandVo();
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(productBaseVo.getBrandName())) {

					brandVo.setBrandName(productBaseVo.getBrandName().trim());
					brandVo = brandApi.getOne(brandVo);
					if (brandVo == null) {

						brandVo = new BrandVo();
						brandVo.setBrandName(productBaseVo.getBrandName().trim());
						brandVo.setState(CrmConstant.STATE_PASS);
						brandVo.setCreateBy("sys");
						brandVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
						int result = brandApi.insert(brandVo);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					} else {
						res = RESULT_SUCCESS;
					}

				}

				// 处理产品信息
				ProductVo productVo = new ProductVo();
				if (res == RESULT_SUCCESS) {
					productVo.setProductNo(productBaseVo.getProductNoName().trim());
					productVo = productApi.getOne(productVo);
					if (productVo != null) {

						productVo.setCatalogId(catalogVo.getId());
						productVo.setBrandId(brandVo.getId());
						int result = productApi.update(productVo);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					} else {

						productVo = new ProductVo();
						productVo.setProductNo(productBaseVo.getProductNoName().trim());
						productVo.setCatalogId(catalogVo.getId());
						productVo.setBrandId(brandVo.getId());
						productVo.setCreateBy("sys");
						productVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
						productVo.setState(CrmConstant.STATE_PASS);
						int result = productApi.insert(productVo);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					}
				}

				// 处理产品基本信息
				if (res == RESULT_SUCCESS) {
					ProductBaseVo productBaseVoTmp = new ProductBaseVo();
					productBaseVoTmp.setProductId(productVo.getId());
					productBaseVoTmp = productBaseApi.getOne(productBaseVoTmp);
					if (productBaseVoTmp != null) {

						if (StringUtils.isNotEmpty(productBaseVo.getProductState())) {
							productBaseVoTmp.setProductState(
									String.valueOf(Double.valueOf(productBaseVo.getProductState()).intValue()));
						}
//						productBaseVoTmp.setProductState(productBaseVo.getProductState());
						productBaseVoTmp.setProductDesc(productBaseVo.getProductDesc());
						int result = productBaseApi.update(productBaseVoTmp);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					} else {

						productBaseVoTmp = new ProductBaseVo();
						productBaseVoTmp.setProductId(productVo.getId().trim());
						if (StringUtils.isNotEmpty(productBaseVo.getProductState())) {
							productBaseVoTmp.setProductState(
									String.valueOf(Double.valueOf(productBaseVo.getProductState()).intValue()));
						}
						productBaseVoTmp.setProductDesc(productBaseVo.getProductDesc());
						productBaseVoTmp.setCreateBy("sys");
						productBaseVoTmp.setId(PrimaryUtil.getInstance().getPrimaryValue());
						productBaseVoTmp.setState(CrmConstant.STATE_PASS);
						int result = productBaseApi.insert(productBaseVoTmp);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					}
				}

				// 处理产品标签
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(productBaseVo.getTagName())) {

					ProductTagVo productTagVo = new ProductTagVo();
					productTagVo.setProductId(productVo.getId());
					productTagApi.delete(productTagVo);

					String[] tagArray = productBaseVo.getTagName().trim().split(",");
					for (String tagName : tagArray) {
						if (StringUtils.isEmpty(tagName)) {
							continue;
						}

						TagVo tagVo = new TagVo();
						tagVo.setTagName(tagName.trim());
						tagVo.setCatalogId(catalogVo.getId().trim());
						tagVo = tagApi.getOne(tagVo);
						if (tagVo == null) {

							tagVo = new TagVo();
							tagVo.setTagName(tagName.trim());
							tagVo.setCatalogId(catalogVo.getId());
							tagVo.setCreateBy("sys");
							tagVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							int result = tagApi.insert(tagVo);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						}

						if (res == RESULT_SUCCESS) {

							productTagVo = new ProductTagVo();
							productTagVo.setProductId(productVo.getId());
							productTagVo.setTagId(tagVo.getId());
							productTagVo.setCreateBy("sys");
							productTagVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							int count = productTagApi.insert(productTagVo);
							if (count > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}
						}

					}

				}

				// 处理产品应用领域
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(productBaseVo.getProductApplyArea())) {

					ProductApplyAreaVo productApplyAreaVo = new ProductApplyAreaVo();
					productApplyAreaVo.setProductId(productVo.getId());
					productApplyAreaApi.delete(productApplyAreaVo);

					String[] applyAreaArray = productBaseVo.getProductApplyArea().trim().split(",");
					for (String applyAreaName : applyAreaArray) {
						if (StringUtils.isEmpty(applyAreaName)) {
							continue;
						}

						ApplyAreaVo applyAreaVo = new ApplyAreaVo();
						applyAreaVo.setAreaName(applyAreaName.trim());
						applyAreaVo = applyAreaApi.getOne(applyAreaVo);
						if (applyAreaVo == null) {

							applyAreaVo = new ApplyAreaVo();
							applyAreaVo.setAreaName(applyAreaName.trim());
							applyAreaVo.setCreateBy("sys");
							applyAreaVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							int result = applyAreaApi.insert(applyAreaVo);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						}

						if (res == RESULT_SUCCESS) {

							productApplyAreaVo = new ProductApplyAreaVo();
							productApplyAreaVo.setProductId(productVo.getId());
							productApplyAreaVo.setApplyAreaId(applyAreaVo.getId());
							productApplyAreaVo.setCreateBy("sys");
							productApplyAreaVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							int count = productApplyAreaApi.insert(productApplyAreaVo);
							if (count > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}
						}

					}

				}

			} catch (Exception e) {
				String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
				logger.error(msg, e);
				throw new RuntimeException("导入excel产品数据业务逻辑异常");
			}

		}
		if(CollectionUtils.isNotEmpty(productBaseVos)) {
			resultVo.setCount(productBaseVos.size());
		}
		resultVo.setRes(res);
	}

	/**
	 * 解析产品替换信息
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private void importReplaceExcelFile(ResultVo<ProductVo> resultVo, String filePath) throws Exception {
		int res = RESULT_FAILURE;
		// 解析文件
		Map<Integer, String> paramNameMap = new HashMap<>();
		paramNameMap.put(0, "productNoName");
		paramNameMap.put(1, "replaceProductNoName");
		paramNameMap.put(2, "replaceCatalogName");
		paramNameMap.put(3, "replaceBrandName");
		paramNameMap.put(4, "matchType");
		paramNameMap.put(5, "replaceProductDesc");

		List<com.mornsun.crm.api.vo.product.ProductReplaceVo> productReplaceVos = ExcelUtil.getInstance()
				.readExcel(filePath, com.mornsun.crm.api.vo.product.ProductReplaceVo.class, paramNameMap);
		for (com.mornsun.crm.api.vo.product.ProductReplaceVo productReplaceVo : productReplaceVos) {

			try {

				// 处理产品信息
				ProductVo productVo = new ProductVo();
				productVo.setProductNo(productReplaceVo.getProductNoName().trim());
				productVo = productApi.getOne(productVo);
				if (productVo != null) {

					// 处理分类
					CatalogVo catalogVo = new CatalogVo();
					catalogVo.setCatalogName(productReplaceVo.getReplaceCatalogName().trim());
					catalogVo = catalogApi.getOne(catalogVo);
					if (catalogVo == null) {

						catalogVo = new CatalogVo();
						catalogVo.setCatalogName(productReplaceVo.getReplaceCatalogName().trim());
						catalogVo.setParentId("-1");
						catalogVo.setCreateBy("sys");
						catalogVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
						int result = catalogApi.insert(catalogVo);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					} else {
						res = RESULT_SUCCESS;
					}

					// 处理品牌
					BrandVo brandVo = new BrandVo();
					if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(productReplaceVo.getReplaceBrandName())) {

						brandVo.setBrandName(productReplaceVo.getReplaceBrandName().trim());
						brandVo = brandApi.getOne(brandVo);
						if (brandVo == null) {

							brandVo = new BrandVo();
							brandVo.setBrandName(productReplaceVo.getReplaceBrandName().trim());
							brandVo.setState(CrmConstant.STATE_PASS);
							brandVo.setCreateBy("sys");
							brandVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							int result = brandApi.insert(brandVo);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						} else {
							res = RESULT_SUCCESS;
						}

					}

					// 处理产品信息
					ProductVo productVoTmp = new ProductVo();
					if (res == RESULT_SUCCESS) {
						productVoTmp.setProductNo(productReplaceVo.getReplaceProductNoName().trim());
						productVoTmp = productApi.getOne(productVoTmp);
						if (productVoTmp != null) {

							productVoTmp.setCatalogId(catalogVo.getId());
							productVoTmp.setBrandId(brandVo.getId());
							int result = productApi.update(productVoTmp);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						} else {

							productVoTmp = new ProductVo();
							productVoTmp.setProductNo(productReplaceVo.getReplaceProductNoName().trim());
							productVoTmp.setCatalogId(catalogVo.getId());
							productVoTmp.setBrandId(brandVo.getId());
							productVoTmp.setCreateBy("sys");
							productVoTmp.setId(PrimaryUtil.getInstance().getPrimaryValue());
							productVoTmp.setState(CrmConstant.STATE_PASS);
							int result = productApi.insert(productVoTmp);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						}
					}

					// 处理产品基本信息
					if (res == RESULT_SUCCESS) {
						ProductBaseVo productBaseVo = new ProductBaseVo();
						productBaseVo.setProductId(productVoTmp.getId());
						productBaseVo = productBaseApi.getOne(productBaseVo);
						if (productBaseVo != null) {
							if (StringUtils.isNotEmpty(productReplaceVo.getReplaceProductState())) {
								productBaseVo.setProductState(String
										.valueOf(Double.valueOf(productReplaceVo.getReplaceProductState()).intValue()));
							}
//							productBaseVo.setProductState(productReplaceVo.getReplaceProductState());
							productBaseVo.setProductDesc(productReplaceVo.getReplaceProductDesc());
							int result = productBaseApi.update(productBaseVo);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						} else {

							productBaseVo = new ProductBaseVo();
							productBaseVo.setProductId(productVoTmp.getId().trim());
							if (StringUtils.isNotEmpty(productReplaceVo.getReplaceProductState())) {
								productBaseVo.setProductState(String
										.valueOf(Double.valueOf(productReplaceVo.getReplaceProductState()).intValue()));
							}
							productBaseVo.setProductDesc(productReplaceVo.getReplaceProductDesc());
							productBaseVo.setCreateBy("sys");
							productBaseVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							productBaseVo.setState(CrmConstant.STATE_PASS);
							int result = productBaseApi.insert(productBaseVo);
							if (result > 0) {
								res = RESULT_SUCCESS;
							} else {
								res = RESULT_FAILURE;
							}

						}
					}

					// 处理产品替换信息
					if (res == RESULT_SUCCESS) {

						ProductReplaceVo productReplaceVoTmp = new ProductReplaceVo();
						productReplaceVoTmp.setProductId(productVoTmp.getId());
						productReplaceApi.delete(productReplaceVoTmp);

						productReplaceVoTmp = new ProductReplaceVo();
						productReplaceVoTmp.setProductId(productVo.getId().trim());
						productReplaceVoTmp.setReplaceProductId(productVoTmp.getId().trim());
						if (StringUtils.isNotEmpty(productReplaceVo.getMatchType())) {
							productReplaceVoTmp.setMatchType(
									String.valueOf(Double.valueOf(productReplaceVo.getMatchType()).intValue()));
						}
						productReplaceVoTmp.setCreateBy("sys");
						productReplaceVoTmp.setId(PrimaryUtil.getInstance().getPrimaryValue());
						productReplaceVoTmp.setState(CrmConstant.STATE_PASS);
						int result = productReplaceApi.insert(productReplaceVoTmp);
						if (result > 0) {
							res = RESULT_SUCCESS;
						} else {
							res = RESULT_FAILURE;
						}

					}

				} else {
					res = RESULT_SUCCESS;
				}

			} catch (Exception e) {
				String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
				logger.error(msg, e);
			}

		}
		resultVo.setRes(res);
	}

	/**
	 * 解析产品附件信息
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private void importAttaExcelFile(ResultVo<ProductVo> resultVo, String filePath) throws Exception {
		int res = RESULT_FAILURE;
		// 解析文件
		Map<Integer, String> paramNameMap = new HashMap<>();
		paramNameMap.put(0, "productNoName");
		paramNameMap.put(1, "attaName");

		List<com.mornsun.crm.api.vo.product.ProductAttaVo> productAttaVos = ExcelUtil.getInstance().readExcel(filePath,
				com.mornsun.crm.api.vo.product.ProductAttaVo.class, paramNameMap);
		for (com.mornsun.crm.api.vo.product.ProductAttaVo productAttaVo : productAttaVos) {

			try {

				// 处理产品信息
				ProductVo productVo = new ProductVo();
				productVo.setProductNo(productAttaVo.getProductNoName().trim());
				productVo = productApi.getOne(productVo);
				if (productVo != null) {
					if (StringUtils.isNotEmpty(productAttaVo.getAttaName())) {

						// 删除产品附件
						ProductAttaVo productAttaVoTmp = new ProductAttaVo();
						productAttaVoTmp.setProductId(productVo.getId());
						productAttaApi.delete(productAttaVoTmp);

						// 处理产品附件信息
						String[] attaArray = productAttaVo.getAttaName().trim().split(",");
						for (String attaName : attaArray) {
							if (StringUtils.isEmpty(attaName)) {
								continue;
							}

							// 更新附件信息
							AttaVo attaVo = new AttaVo();
							attaVo.setSourceId("-1");
							attaVo.setSourceType(AttaTypeConstant.PRODUCT.getType());
							attaVo.setFileName(attaName);
							attaVo = attaApi.getOne(attaVo);
							if (attaVo != null) {
								attaVo.setSourceId(productVo.getId());
								int count = attaApi.update(attaVo);
								if (count > 0) {
									res = RESULT_SUCCESS;
								} else {
									break;
								}
							}

							// 插入产品附件关系
							if (res == RESULT_SUCCESS) {
								productAttaVoTmp = new ProductAttaVo();
								productAttaVoTmp.setProductId(productVo.getId());
								productAttaVoTmp.setAttaId(attaVo.getId());
								productAttaVoTmp.setState(CrmConstant.STATE_PASS);
								productAttaVoTmp.setCreateBy("sys");
								productAttaVoTmp.setId(PrimaryUtil.getInstance().getPrimaryValue());
								int result = productAttaApi.insert(productAttaVoTmp);
								if (result > 0) {
									res = RESULT_SUCCESS;
								} else {
									res = RESULT_FAILURE;
								}
							}

						}

					}
				} else {
					res = RESULT_SUCCESS;
				}

			} catch (Exception e) {
				String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
				logger.error(msg, e);
			}

		}
		resultVo.setRes(res);
	}

	/**
	 * 导入产品基本信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<ProductVo> importBaseExcelFile(HttpServletRequest request) throws Exception {
		return importExcelFile(request, CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT_BASE);
	}

	/**
	 * 导入产品替换信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<ProductVo> importReplaceExcelFile(HttpServletRequest request) throws Exception {
		return importExcelFile(request, CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT_REPLACE);
	}

	/**
	 * 导入产品附件信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<ProductVo> importAttaExcelFile(HttpServletRequest request) throws Exception {
		return importExcelFile(request, CrmConstant.CRM_FILE_IMPORT_TYPE_PRODUCT_ATTA);
	}

}
