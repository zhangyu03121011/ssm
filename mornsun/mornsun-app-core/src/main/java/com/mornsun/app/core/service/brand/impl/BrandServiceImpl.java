package com.mornsun.app.core.service.brand.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.brand.IBrandService;
import com.mornsun.jsw.api.api.brand.IBrandApi;
import com.mornsun.jsw.api.vo.brand.BrandVo;

/**
 * 品牌Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class BrandServiceImpl extends BasePageHelperApiServiceImpl<BrandVo, IBrandApi> implements IBrandService {

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<BrandVo> list(BrandVo obj, HttpServletRequest request) throws Exception {
		ResultVo<BrandVo> resultVo = new ResultVo<BrandVo>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			if (StringUtils.isNotEmpty(obj.getCatalogId())) {
				obj.setFlag(true);
			}
			resultVo = super.list(obj, request);
			res = resultVo.getRes();

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
