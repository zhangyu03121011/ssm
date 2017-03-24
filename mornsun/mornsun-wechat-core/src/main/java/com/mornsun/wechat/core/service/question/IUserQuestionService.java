package com.mornsun.wechat.core.service.question;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户问答Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserQuestionService {

	/**
	 * 获取用户问答信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<UserQuestionVo> detail(String id, HttpServletRequest request) throws Exception;

}
