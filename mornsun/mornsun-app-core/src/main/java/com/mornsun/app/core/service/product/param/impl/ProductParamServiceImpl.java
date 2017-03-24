package com.mornsun.app.core.service.product.param.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.Base64Util;
import com.common.util.ExceptionUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.product.base.IProductService;
import com.mornsun.app.core.service.product.param.IProductParamService;
import com.mornsun.jsw.api.api.product.param.IProductParamApi;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 产品参数Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductParamServiceImpl extends BasePageHelperApiServiceImpl<ProductParamVo, IProductParamApi>
		implements IProductParamService {

	@Autowired
	private IProductService productService;

	@Autowired
	private IMemcacheService memcacheService;

	/**
	 * 保存搜索信息
	 * 
	 * @param obj
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultVo<ProductParamVo> save(ProductParamVo obj, HttpServletRequest request) throws Exception {
		ResultVo<ProductParamVo> resultVo = new ResultVo<ProductParamVo>();
		int res = RESULT_FAILURE;
		try {
			// 验证对象必填项
			if (StringUtils.isEmpty(obj.getProductNo()) || StringUtils.isEmpty(obj.getBrandId())
					|| StringUtils.isEmpty(obj.getCatalogId()) || StringUtils.isEmpty(obj.getParamJson())) {

				res = RESULT_DATA_NULL;// 数据错误

			} else {

				String productId = null;

				// 判断产品基本信息
				ProductVo productVo = new ProductVo();
				productVo.setProductNo(obj.getProductNo());
				productVo = productService.getOne(productVo);
				if (productVo != null) {
					productId = productVo.getId();
					productVo = new ProductVo();
					productVo.setId(productId);
					productVo.setBrandId(obj.getBrandId());
					productVo.setCatalogId(obj.getCatalogId());
					productVo.setProductNo(obj.getProductNo());
					ResultVo<ProductVo> resultVoTmp = productService.update(productVo, request);
					res = resultVoTmp.getRes();
				} else {

					// 保存产品信息
					productVo = new ProductVo();
					productVo.setBrandId(obj.getBrandId());
					productVo.setCatalogId(obj.getCatalogId());
					productVo.setProductNo(obj.getProductNo());
					productVo.setState(AppConstant.STATE_WAIT);
					ResultVo<ProductVo> resultVoTmp = productService.save(productVo, request);
					res = resultVoTmp.getRes();
					if (res == RESULT_SUCCESS) {
						productId = resultVoTmp.getObj().getId();
					}

				}

				ProductParamVo productParamVo = new ProductParamVo();
				productParamVo.setProductId(productId);
				super.delete(productParamVo);

				if (res == RESULT_SUCCESS) {

					String paramJosn = Base64Util.getInstance().decodeStr(obj.getParamJson());
					logger.info("paramJson====>" + paramJosn);
					List<ProductParamVo> vos = JSON.parseArray(paramJosn, ProductParamVo.class);

					UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());

					for (ProductParamVo objTmp : vos) {

						ProductParamVo productParamVoTmp = new ProductParamVo();
						productParamVoTmp.setParamId(objTmp.getParamId());
						productParamVoTmp.setState(AppConstant.STATE_WAIT);
						productParamVoTmp.setProductId(productId);
						productParamVoTmp.setParamValue(objTmp.getParamValue());
						productParamVoTmp.setCreateBy(userVo.getId());
						resultVo = super.save(productParamVoTmp, request);
						res = resultVo.getRes();
						if (res != RESULT_SUCCESS) {
							break;
						}

					}

				}
			}

			logger.info("[" + this.getClass().getSimpleName() + "][save][list=" + JSON.toJSONString(obj) + "][res="
					+ res + "]");
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
