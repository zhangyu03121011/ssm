package com.mornsun.app.core.service.user.answer.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.service.service.atta.IUploadService;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.api.constant.AttaTypeConstant;
import com.mornsun.app.core.service.atta.IAttaService;
import com.mornsun.app.core.service.user.answer.IUserAnswerService;
import com.mornsun.app.core.service.user.order.IUserOrderService;
import com.mornsun.app.core.service.user.question.IUserQuestionService;
import com.mornsun.jsw.api.api.user.answer.IUserAnswerApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 用户回答Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserAnswerServiceImpl extends BasePageHelperApiServiceImpl<UserAnswerVo, IUserAnswerApi>
		implements IUserAnswerService {

	@Autowired
	private IUploadService uploadService;

	@Autowired
	private IAttaService attaService;

	@Autowired
	private IUserOrderService userOrderService;

	@Autowired
	private IUserQuestionService userQuestionService;

	@Autowired
	private IMemcacheService memcacheService;

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserAnswerVo> list(UserAnswerVo obj, PageListVo<UserAnswerVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<UserAnswerVo> resultVo = new ResultVo<UserAnswerVo>();
		int res = RESULT_FAILURE;
		try {

			if (StringUtils.isNotEmpty(obj.getQuestionId())) {
				obj.setUserId(null);
			}

			// 获取登录信息
			UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
			if (userVo != null) {
				obj.setCurrUserId(userVo.getId());
			}

			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();
			if (resultVo.getRes() == RESULT_SUCCESS) {

				List<UserAnswerVo> userAnswerVos = resultVo.getPageListVo().getRows();
				for (UserAnswerVo userAnswerVoTmp : userAnswerVos) {

					// 获取登录信息
					if (userVo != null) {
						// 判断是否已经支付
						userAnswerVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_NO);
						if (userAnswerVoTmp.getPayMoney() == 0) {
							userAnswerVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_YES);
						} else {
							UserOrderVo userOrderVo = new UserOrderVo();
							userOrderVo.setUserId(userVo.getId());
							userOrderVo.setSourceId(userAnswerVoTmp.getId());
							Object object = userOrderService.getOneObject(userOrderVo);
							if (object != null && Integer.parseInt(object.toString()) > 0) {
								userAnswerVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_YES);
							}
						}
					}

					// 查询附件
					AttaVo attaVo = new AttaVo();
					attaVo.setSourceId(userAnswerVoTmp.getId());
					userAnswerVoTmp.setAttaVos(attaService.getAll(attaVo));
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

	/**
	 * 保存秒懂信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserAnswerVo> save(UserAnswerVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserAnswerVo> resultVo = new ResultVo<UserAnswerVo>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				// 保存问答信息
				obj.setPayMoney(1);// 使用问答目前都为1元
				resultVo = super.save(obj, request);
				res = resultVo.getRes();

				if (res == RESULT_SUCCESS) {

					// 更新问题回答数
					UserQuestionVo tmpVo = new UserQuestionVo();
					tmpVo.setId(obj.getQuestionId());
					tmpVo.setAnswerCount(1);
					userQuestionService.update(tmpVo);

					List<BaseAttaModel> attaModels = uploadService.upload(resultVo.getObj().getId(),
							AttaTypeConstant.ANSWER.getType(), AttaTypeConstant.ANSWER.getName(), request);
					if (CollectionUtils.isNotEmpty(attaModels)) {
						for (BaseAttaModel baseAttaModel : attaModels) {

							AttaVo attaVo = new AttaVo();
							BeanUtils.copyProperties(baseAttaModel, attaVo);

							// 保存附件
							ResultVo<AttaVo> resultVoTmp = attaService.save(attaVo, request);
							res = resultVoTmp.getRes();
							if (res != RESULT_SUCCESS) {
								break;
							}

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
