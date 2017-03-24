package com.mornsun.wechat.core.service.course;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;

/**
 * 用户秒懂Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserCourseService {

	/**
	 * 获取用户秒懂信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<UserCourseVo> detail(String id, HttpServletRequest request) throws Exception;

}
