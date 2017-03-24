package com.mornsun.app.core.service.user.profit.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.user.profit.IUserProfitService;
import com.mornsun.jsw.api.api.user.profit.IUserProfitApi;
import com.mornsun.jsw.api.vo.user.profit.UserProfitVo;

/**
 * 用户收益Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserProfitServiceImpl extends BasePageHelperApiServiceImpl<UserProfitVo, IUserProfitApi>
		implements IUserProfitService {

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserProfitVo> list(UserProfitVo obj, PageListVo<UserProfitVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<UserProfitVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 查询当前用户收益列表
			if (StringUtils.isNotEmpty(obj.getUserId())) {
				resultVo = super.list(obj, pageListVo, request);
				res = resultVo.getRes();
				// 查询总金额
				if (pageListVo.getCurrPage() == 1) {
					Object totalMoney = super.getOneObject(obj);
					if (totalMoney != null) {
						obj.setTotalMoney(Double.parseDouble(totalMoney.toString()));
						resultVo.setObj(obj);
					}
				}
			}

			logger.info("[" + this.getClass().getSimpleName() + "][list][" + obj.getClass().getSimpleName() + "="
					+ JSON.toJSONString(obj) + "][res=" + res + "]");
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
