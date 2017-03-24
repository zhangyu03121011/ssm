package com.mornsun.wechat.manager.controller.question;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.base.vo.base.ResultVo;
import com.common.controller.base.BaseController;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;
import com.mornsun.wechat.core.service.question.IUserQuestionService;

/**
 * 用户问答Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年4月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/question")
public class UserQuestionController extends BaseController {

	@Autowired
	private IUserQuestionService userQuestionService;

	/**
	 * 用户问答信息
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(String id, Model model, HttpServletRequest request) {
		try {
			ResultVo<UserQuestionVo> resultVo = userQuestionService.detail(id, request);
			model.addAttribute("resultVo", resultVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "question/detail";
	}
}
