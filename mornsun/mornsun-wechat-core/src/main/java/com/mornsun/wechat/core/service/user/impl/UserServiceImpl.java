package com.mornsun.wechat.core.service.user.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constant.BaseConstant;
import com.common.base.constant.ResultConstant;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.service.service.base.impl.BaseControllerServiceImpl;
import com.common.util.CodeUtil;
import com.common.util.CommUtil;
import com.common.util.ExceptionUtil;
import com.common.util.Md5Util;
import com.common.util.PrimaryUtil;
import com.common.util.RandomUtil;
import com.mornsun.jsw.api.api.user.base.IUserApi;
import com.mornsun.jsw.api.api.user.share.IUserShareApi;
import com.mornsun.jsw.api.vo.coupon.CouponVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.coupon.UserCouponVo;
import com.mornsun.jsw.api.vo.user.share.UserShareVo;
import com.mornsun.wechat.api.constant.WechatConstant;
import com.mornsun.wechat.api.vo.wechat.ShareUserVo;
import com.mornsun.wechat.api.vo.wechat.UserAccessTokenVo;
import com.mornsun.wechat.api.vo.wechat.UserAccessUserVo;
import com.mornsun.wechat.core.service.user.IUserService;
import com.mornsun.wechat.core.service.wechat.IWechatService;

/**
 * 用户Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserServiceImpl implements IUserService, BaseConstant {

	private final static Logger logger = Logger.getLogger(BaseControllerServiceImpl.class);

	@Autowired
	private IUserApi userApi;

	@Autowired
	private IUserShareApi userShareApi;

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IWechatService wechatService;

	/**
	 * 获取分享用户信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserVo> share(String id, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			if (StringUtils.isEmpty(id)) {
				res = RESULT_DATA_NULL;
			}

			// 获取用户分享信息
			UserShareVo userShareVo = userShareApi.getOneById(id);
			if (userShareVo != null) {
				UserVo userVo = userApi.getOneById(userShareVo.getUserId());
				if (userVo != null) {
					res = RESULT_SUCCESS;
					resultVo.setObj(userVo);
				}
			}

			logger.info("[UserServiceImpl][share][id=" + id + "][res=" + res + "]");
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
	 * 获取用户AccessToken
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public ResultVo<UserVo> auth(String code, String source, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			ResultVo<UserAccessTokenVo> vo = wechatService.payAccess(code, request);
			res = vo.getRes();
			if (vo.getRes() == ResultConstant.RESULT_SUCCESS) {

				// 获取用户授权信息
				UserAccessTokenVo userAccessTokenVo = vo.getObj();

//				// 判断用户授权信息是否存在
//				UserVo userVo = new UserVo();
//				userVo.setOpenId(userAccessTokenVo.getOpenid());
//				userVo = super.getOne(userVo);
//				if (userVo == null) {
//
//					// 查询用户是否存在，不存在则插入数据
//					UserVo userVoTmp = new UserVo();
//					userVoTmp.setOpenId(userAccessTokenVo.getOpenid());
//
//					// 获取微信用户信息
//					ResultVo<UserAccessUserVo> resultVoUser = wechatService
//							.accessUser(userAccessTokenVo.getAccess_token(), userAccessTokenVo.getOpenid(), request);
//					if (resultVoUser.getRes() == RESULT_SUCCESS) {
//						userVoTmp.setHeadPhotoUrl(resultVoUser.getObj().getHeadimgurl());
//						userVoTmp.setUserName(resultVoUser.getObj().getNickname());
//					}
//
//					userVoTmp.setBaseCreateBy(null);
//					userVoTmp.setUserId(userVoTmp.getId());
//					userVoTmp.setRandomCode(CodeUtil.getInstance().getSecurityCode(10));
//
//					resultVo = super.save(userVoTmp, request);
//					res = resultVo.getRes();
//					if (res == RESULT_SUCCESS) {
//
//						memcacheService.set(request.getSession().getId(), userVoTmp, SESSION_OUT_TIME);
//
//						// 查询所有注册优惠券
//						CouponVo couponVo = new CouponVo();
//						couponVo.setType(CouponConstant.COUPON_TYPE_REG);
//						List<CouponVo> couponVos = couponService.getAll(couponVo);
//
//						// 赠送注册优惠券
//						if (CollectionUtils.isNotEmpty(couponVos)) {
//							int count = 0;
//							for (CouponVo couponTmp : couponVos) {
//								UserCouponVo userCouponVo = new UserCouponVo();
//								userCouponVo.setUserId(userVoTmp.getId());
//								userCouponVo.setCouponId(couponTmp.getId());
//								userCouponVo.setBaseCreateBy(userVoTmp.getId());
//								userCouponVo.setCouponCode(RandomUtil.getInstance().createRandom(true, 6));
//								userCouponVo.setSource(WechatConstant.USER_COUPON_SOURCE_REG);
//								userCouponVo.setSourceId(userVoTmp.getId());
//								int result = userCouponService.insert(userCouponVo);
//								if (result > 0) {
//									count++;
//								}
//							}
//							if (count != couponVos.size()) {
//								logger.error("[auth][id =" + userVoTmp.getId() + "]insert reg coupon failure...");
//							}
//						}
//					}

//				} else {
//					// 系统自动登录
//					// 生成token信息
//					String token = CommUtil.getInstance().getToken(request.getSession().getId());
//					memcacheService.set(request.getSession().getId(), userVo, SESSION_OUT_TIME);
//					resultVo.setToken(token);
//					resultVo.setObj(userVo);
//					res = RESULT_SUCCESS;
//					logger.info("[auth][token =" + token + "]auth succ...");
//				}
			}
			logger.info("[UserServiceImpl][auth][code =" + code + "][res=" + res + "]");
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
