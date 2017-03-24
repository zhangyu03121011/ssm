package com.mornsun.app.core.service.user.question.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.atta.IAttaService;
import com.mornsun.app.core.service.user.answer.IUserAnswerService;
import com.mornsun.app.core.service.user.order.IUserOrderService;
import com.mornsun.app.core.service.user.question.IUserQuestionService;
import com.mornsun.jsw.api.api.user.question.IUserQuestionApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户问题Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserQuestionServiceImpl extends BasePageHelperApiServiceImpl<UserQuestionVo, IUserQuestionApi>
		implements IUserQuestionService {

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IUserAnswerService userAnswerService;

	@Autowired
	private IAttaService attaService;
	
	@Autowired
	private IUserOrderService userOrderService;

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserQuestionVo> list(UserQuestionVo obj, PageListVo<UserQuestionVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<UserQuestionVo> resultVo = new ResultVo<UserQuestionVo>();
		int res = RESULT_FAILURE;
		try {
			
			// 获取登录信息
			UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());

			if (!obj.isAnswerFlag() && !obj.isHotFlag()) {
				obj.setUserId(null);
				// 问我的问题列表
				if (StringUtils.isNotEmpty(obj.getQueryType()) && userVo!=null) {
					if (AppConstant.QUESTION_TYPE_MY_RESPONSE.equals(obj.getQueryType())) {
						obj.setAnswerUserId(userVo.getId());
					} else if (AppConstant.QUESTION_TYPE_MY_REQUEST.equals(obj.getQueryType())) {
						obj.setUserId(userVo.getId());
					}
				}
			}
			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();
			if (obj.isAnswerFlag() && res == RESULT_SUCCESS) {

				List<UserQuestionVo> userQuestionVos = resultVo.getPageListVo().getRows();
				for (UserQuestionVo userQuestionVoTmp : userQuestionVos) {
					UserAnswerVo userAnswerVo = new UserAnswerVo();
					userAnswerVo.setQuestionId(userQuestionVoTmp.getId());

					PageListVo<UserAnswerVo> pageListVoTmp = userAnswerService.getPage(userAnswerVo,
							new PageListVo<>());
					res = resultVo.getRes();
					if (resultVo.getRes() == RESULT_SUCCESS) {

						List<UserAnswerVo> userAnswerVos = pageListVoTmp.getRows();
						for (UserAnswerVo userAnswerVoTmp : userAnswerVos) {
							
							// 判断是否已经支付
							userAnswerVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_NO);
							if (userAnswerVoTmp.getPayMoney() == 0) {
								userAnswerVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_YES);
							}else if (userVo != null){
								UserOrderVo userOrderVo = new UserOrderVo();
								userOrderVo.setUserId(userVo.getId());
								userOrderVo.setSourceId(userAnswerVoTmp.getId());
								Object object = userOrderService.getOneObject(userOrderVo);
								if (object != null && Integer.parseInt(object.toString()) > 0) {
									userAnswerVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_YES);
								}
							}
							
							// 查询附件
							AttaVo attaVo = new AttaVo();
							attaVo.setSourceId(userAnswerVoTmp.getId());
							userAnswerVoTmp.setAttaVos(attaService.getAll(attaVo));
						}
						userQuestionVoTmp.setUserAnswerVos(pageListVoTmp.getRows());

					}
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][list][" + obj.getClass().getSimpleName() + "="
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
