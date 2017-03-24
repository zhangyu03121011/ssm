package com.mornsun.wechat.core.service.user;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserService {

	/**
	 * 获取分享用户信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<UserVo> share(String id, HttpServletRequest request) throws Exception;

	/**
	 * 获取用户AccessToken
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public ResultVo<UserVo> auth(String code, String source, HttpServletRequest request) throws Exception;

}
