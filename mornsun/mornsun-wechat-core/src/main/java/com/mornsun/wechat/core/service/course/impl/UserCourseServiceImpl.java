package com.mornsun.wechat.core.service.course.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.ResultVo;
import com.common.service.service.base.impl.BaseControllerServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.atta.IAttaApi;
import com.mornsun.jsw.api.api.user.course.IUserCourseApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.wechat.core.service.course.IUserCourseService;

/**
 * 用户秒懂Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserCourseServiceImpl implements IUserCourseService, BaseConstant {

	private final static Logger logger = Logger.getLogger(BaseControllerServiceImpl.class);

	@Autowired
	private IUserCourseApi userCourseApi;

	@Autowired
	private IAttaApi attaApi;

	/**
	 * 获取用户秒懂信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserCourseVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<UserCourseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			if (StringUtils.isEmpty(id)) {
				res = RESULT_DATA_NULL;
			}

			// 获取用户秒懂信息
			UserCourseVo userCourseVo = userCourseApi.getOneById(id);
			if (userCourseVo != null) {

				// 查询秒懂附件信息
				AttaVo attaVo = new AttaVo();
				attaVo.setSourceId(userCourseVo.getId());
				List<AttaVo> attaVos = attaApi.getAll(attaVo);
				userCourseVo.setAttaVos(attaVos);

				res = RESULT_SUCCESS;
				resultVo.setObj(userCourseVo);
			}

			logger.info("[UserCourseServiceImpl][detail][id=" + id + "][res=" + res + "]");
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
