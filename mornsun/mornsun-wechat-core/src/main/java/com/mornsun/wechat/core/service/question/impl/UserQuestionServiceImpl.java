package com.mornsun.wechat.core.service.question.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.service.service.base.impl.BaseControllerServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.atta.IAttaApi;
import com.mornsun.jsw.api.api.user.answer.IUserAnswerApi;
import com.mornsun.jsw.api.api.user.question.IUserQuestionApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;
import com.mornsun.wechat.core.service.question.IUserQuestionService;

/**
 * 用户问答Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserQuestionServiceImpl implements IUserQuestionService, BaseConstant {

	private final static Logger logger = Logger.getLogger(BaseControllerServiceImpl.class);

	@Autowired
	private IUserQuestionApi userQuestionApi;

	@Autowired
	private IUserAnswerApi userAnswerApi;

	@Autowired
	private IAttaApi attaApi;

	/**
	 * 获取用户问答信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserQuestionVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<UserQuestionVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			if (StringUtils.isEmpty(id)) {
				res = RESULT_DATA_NULL;
			}

			// 获取用户问答信息
			UserQuestionVo userQuestionVo = userQuestionApi.getOneById(id);
			if (userQuestionVo != null) {

				// 查询回答列表
				UserAnswerVo userAnswerVo = new UserAnswerVo();
				userAnswerVo.setQuestionId(userQuestionVo.getId());
				PageListVo<UserAnswerVo> pageListVo = userAnswerApi.getPage(userAnswerVo, new PageListVo<>());
				List<UserAnswerVo> userAnswerVos = pageListVo.getRows();
				
				// 查询附件信息
				for (UserAnswerVo userAnswerVoTmp : userAnswerVos) {
					AttaVo attaVo = new AttaVo();
					attaVo.setSourceId(userAnswerVoTmp.getId());
					List<AttaVo> attaVos = attaApi.getAll(attaVo);
					userAnswerVoTmp.setAttaVos(attaVos);
				}
				userQuestionVo.setUserAnswerVos(userAnswerVos);

				res = RESULT_SUCCESS;
				resultVo.setObj(userQuestionVo);
			}

			logger.info("[UserQuestionServiceImpl][detail][id=" + id + "][res=" + res + "]");
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
