package com.mornsun.app.core.service.user.course.impl;

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
import com.common.util.SessionUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.api.constant.AttaTypeConstant;
import com.mornsun.app.core.service.atta.IAttaService;
import com.mornsun.app.core.service.user.attention.IUserAttentionService;
import com.mornsun.app.core.service.user.course.IUserCourseService;
import com.mornsun.app.core.service.user.order.IUserOrderService;
import com.mornsun.jsw.api.api.user.course.IUserCourseApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.user.attention.UserAttentionVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;

/**
 * 用户秒懂Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserCourseServiceImpl extends BasePageHelperApiServiceImpl<UserCourseVo, IUserCourseApi>
		implements IUserCourseService {

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IUploadService uploadService;

	@Autowired
	private IAttaService attaService;

	@Autowired
	private IUserOrderService userOrderService;

	@Autowired
	private IUserAttentionService userAttentionService;

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserCourseVo> list(UserCourseVo obj, PageListVo<UserCourseVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<UserCourseVo> resultVo = new ResultVo<UserCourseVo>();
		int res = RESULT_FAILURE;
		try {

			// 获取登录信息
			UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
			obj.setUserId(null);
			// 秒懂列表
			if (StringUtils.isNotEmpty(obj.getQueryType())) {
				if (AppConstant.COURSE_TYPE_MY_REQUEST.equals(obj.getQueryType()) && userVo != null) {
					obj.setUserId(userVo.getId());
				}
			}

			// 获取登录信息
			if (userVo != null) {
				obj.setCurrUserId(userVo.getId());
			}

			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();
			if (resultVo.getRes() == RESULT_SUCCESS) {

				List<UserCourseVo> userCourseVos = resultVo.getPageListVo().getRows();
				for (UserCourseVo userCourseVoTmp : userCourseVos) {

					// 判断是否已经支付
					userCourseVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_NO);
					if (userCourseVoTmp.getPayMoney() == 0) {
						userCourseVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_YES);
					} else if (userVo != null) {
						UserOrderVo userOrderVo = new UserOrderVo();
						userOrderVo.setUserId(userVo.getId());
						userOrderVo.setSourceId(userCourseVoTmp.getId());
						Object object = userOrderService.getOneObject(userOrderVo);
						if (object != null && Integer.parseInt(object.toString()) > 0) {
							userCourseVoTmp.setPayState(AppConstant.PAY_TYPE_STATE_YES);
						}
					}

					// 查询附件
					AttaVo attaVo = new AttaVo();
					attaVo.setSourceId(userCourseVoTmp.getId());
					userCourseVoTmp.setAttaVos(attaService.getAll(attaVo));
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
	 * 查询详情
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserCourseVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<UserCourseVo> resultVo = new ResultVo<UserCourseVo>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			resultVo = super.detail(id, request);
			res = resultVo.getRes();
			if (resultVo.getRes() == RESULT_SUCCESS) {
				UserCourseVo userCourseVo = resultVo.getObj();

				// 判断是否已经支付
				userCourseVo.setPayState(AppConstant.PAY_TYPE_STATE_NO);
				if (userCourseVo.getPayMoney() == 0) {
					userCourseVo.setPayState(AppConstant.PAY_TYPE_STATE_YES);
				} else {

					// 获取登录信息
					UserVo userVo = (UserVo) memcacheService.get(SessionUtil.getInstance().getSessionId(request));
					if (userVo != null) {
						UserOrderVo userOrderVo = new UserOrderVo();
						userOrderVo.setUserId(userVo.getId());
						userOrderVo.setSourceId(userCourseVo.getId());
						Object object = userOrderService.getOneObject(userOrderVo);
						if (object != null && Integer.parseInt(object.toString()) > 0) {
							userCourseVo.setPayState(AppConstant.PAY_TYPE_STATE_YES);
						}

						// 查询是否已经关注
						if (userCourseVo.getUserId().equals(userVo.getId())) {
							userCourseVo.setIsAttention(STATUS_YES);
						} else {
							UserAttentionVo userAttentionVo = new UserAttentionVo();
							userAttentionVo.setUserId(userVo.getId());
							userAttentionVo.setAttentionUserId(userCourseVo.getUserId());
							userAttentionVo.setFlag(true);
							userAttentionVo = userAttentionService.getOne(userAttentionVo);
							if (userAttentionVo != null) {
								userCourseVo.setIsAttention(STATUS_YES);
							} else {
								userCourseVo.setIsAttention(STATUS_NO);
							}
						}

					}

				}

				// 查询附件
				AttaVo attaVo = new AttaVo();
				attaVo.setSourceId(userCourseVo.getId());
				userCourseVo.setAttaVos(attaService.getAll(attaVo));

				resultVo.setObj(userCourseVo);
			}

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
	public ResultVo<UserCourseVo> save(UserCourseVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserCourseVo> resultVo = new ResultVo<UserCourseVo>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				// 保存秒懂信息
				resultVo = super.save(obj, request);
				res = resultVo.getRes();

				if (res == RESULT_SUCCESS) {
					List<BaseAttaModel> attaModels = uploadService.upload(resultVo.getObj().getId(),
							AttaTypeConstant.COURSE.getType(), AttaTypeConstant.COURSE.getName(), request);
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
