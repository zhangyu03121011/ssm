package com.mornsun.wechat.manager.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.base.vo.base.ResultVo;
import com.common.controller.base.BaseController;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.wechat.core.service.user.IUserService;

/**
 * 用户Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年4月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 用户分享信息
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/share")
	public String share(String id, Model model, HttpServletRequest request) {
		try {
			ResultVo<UserVo> resultVo = userService.share(id, request);
			model.addAttribute("resultVo", resultVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "share/share";
	}

	/**
	 * 获取用户AccessToken
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping("/auth")
	public String auth(String code, HttpServletRequest request) {
		try {
			userService.auth(code, null, request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:http://www.meibaxiu.com/wechat/page_main.html#Home";
	}
	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx01d932c110af2031&redirect_uri=http%3A%2F%2Fwww.meibaxiu.com%2Fapi%2Fuser%2Fauth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
}
