package com.mornsun.app.core.service.catalog.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.catalog.ICatalogService;
import com.mornsun.jsw.api.api.catalog.ICatalogApi;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;

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

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<CatalogVo> list(CatalogVo obj, HttpServletRequest request) throws Exception {
		ResultVo<CatalogVo> resultVo = new ResultVo<CatalogVo>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			if (StringUtils.isNotEmpty(obj.getBrandId())) {

				obj.setFlag(true);
				resultVo = super.list(obj, request);
				res = resultVo.getRes();

			} else {

				obj.setFlag(true);
				obj.setParentId("-1");
				resultVo = super.list(obj, request);
				res = resultVo.getRes();
				if (resultVo.getRes() == RESULT_SUCCESS) {
					List<CatalogVo> catalogVos = resultVo.getList();
					for (CatalogVo catalogVo : catalogVos) {
						CatalogVo vo = new CatalogVo();
						vo.setParentId(catalogVo.getId());
						vo.setFlag(true);
						List<CatalogVo> catalogVosTmp = super.getAll(vo);
						catalogVo.setCatalogVos(catalogVosTmp);
					}
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
