package com.mornsun.app.manager.controller.user.question;

import com.mornsun.app.core.service.user.question.IUserQuestionService;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户问题Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/question")
public class UserQuestionController extends BasePageHelperApiServiceController<UserQuestionVo, IUserQuestionService> {

	@Autowired
	private IUserQuestionService userQuestionService;

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/answer")
	public ResultVo<UserQuestionVo> pageAnswer(UserQuestionVo obj, PageListVo<UserQuestionVo> page,
			HttpServletRequest request) {
		ResultVo<UserQuestionVo> resultVo = new ResultVo<>();
		try {
			obj.setAnswerFlag(true);
			resultVo = userQuestionService.list(obj, page, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/hot")
	public ResultVo<UserQuestionVo> hot(UserQuestionVo obj, PageListVo<UserQuestionVo> page,
			HttpServletRequest request) {
		ResultVo<UserQuestionVo> resultVo = new ResultVo<>();
		try {
			obj.setHotFlag(true);
			resultVo = userQuestionService.list(obj, page, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

}
