package com.mornsun.wechat.manager.controller.course;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.base.vo.base.ResultVo;
import com.common.controller.base.BaseController;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.wechat.core.service.course.IUserCourseService;

/**
 * 用户秒懂Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年4月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/course")
public class UserCourseController extends BaseController {

	@Autowired
	private IUserCourseService userCourseService;

	/**
	 * 用户秒懂信息
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(String id, Model model, HttpServletRequest request) {
		try {
			ResultVo<UserCourseVo> resultVo = userCourseService.detail(id, request);
			model.addAttribute("resultVo", resultVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "course/detail";
	}
}
