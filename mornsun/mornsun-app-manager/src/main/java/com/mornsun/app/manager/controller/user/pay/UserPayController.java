package com.mornsun.app.manager.controller.user.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.user.deal.IUserDealService;
import com.mornsun.app.core.service.user.pay.IUserPayService;
import com.mornsun.jsw.api.vo.user.pay.UserDealVo;
import com.mornsun.jsw.api.vo.user.pay.UserPayVo;

/**
 * 用户交易Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/pay")
public class UserPayController extends BasePageHelperApiServiceController<UserPayVo, IUserPayService> {

	@Autowired
	private IUserDealService userDealService;

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deal")
	public ResultVo<UserDealVo> deal(UserDealVo obj, PageListVo<UserDealVo> page, HttpServletRequest request) {
		ResultVo<UserDealVo> resultVo = new ResultVo<>();
		try {
			resultVo = userDealService.list(obj, page, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}
}
