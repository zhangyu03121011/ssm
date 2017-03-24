package com.mornsun.app.core.service.user.praise.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.company.course.ICompanyCourseService;
import com.mornsun.app.core.service.user.answer.IUserAnswerService;
import com.mornsun.app.core.service.user.course.IUserCourseService;
import com.mornsun.app.core.service.user.praise.IUserPraiseService;
import com.mornsun.jsw.api.api.user.praise.IUserPraiseApi;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.praise.UserPraiseVo;

/**
 * 用户点赞Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserPraiseServiceImpl extends BasePageHelperApiServiceImpl<UserPraiseVo, IUserPraiseApi>
		implements IUserPraiseService {

	@Autowired
	private IUserCourseService userCourseService;

	@Autowired
	private IUserAnswerService userAnswerService;

	@Autowired
	private ICompanyCourseService companyCourseService;

	@Autowired
	private IMemcacheService memcacheService;

	/**
	 * 保存分享信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserPraiseVo> save(UserPraiseVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserPraiseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				// 判断用户是否已经点赞过
				UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
				UserPraiseVo userPraiseVo = new UserPraiseVo();
				userPraiseVo.setUserId(userVo.getId());
				userPraiseVo.setSourceId(obj.getSourceId());
				userPraiseVo = super.getOne(userPraiseVo);
				if (userPraiseVo != null) {
					res = RESULT_HAS_EXISTS;
				} else {
					resultVo = super.save(obj, request);
					res = resultVo.getRes();
					if (res == RESULT_SUCCESS) {

						// 秒懂
						if (AppConstant.SOURCETYPE_COURSE.equals(obj.getSourceType())) {

							UserCourseVo tmpVo = new UserCourseVo();
							tmpVo.setId(obj.getSourceId());
							tmpVo.setPraiseCount(1);
							userCourseService.update(tmpVo);

						} else if (AppConstant.SOURCETYPE_ANSWER.equals(obj.getSourceType())) {// 问答

							UserAnswerVo tmpVo = new UserAnswerVo();
							tmpVo.setId(obj.getSourceId());
							tmpVo.setPraiseCount(1);
							userAnswerService.update(tmpVo);

						} else if (AppConstant.SOURCETYPE_COMPANY.equals(obj.getSourceType())) {// 企业秒懂

							CompanyCourseVo tmpVo = new CompanyCourseVo();
							tmpVo.setId(obj.getSourceId());
							tmpVo.setPraiseCount(1);
							companyCourseService.update(tmpVo);

						}

					}
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][save][" + obj.getClass().getSimpleName() + "="
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
