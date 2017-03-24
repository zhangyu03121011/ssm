package com.mornsun.app.core.service.user.base.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.constant.PrivilegeConstant;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.service.service.atta.IUploadService;
import com.common.util.CodeUtil;
import com.common.util.CommUtil;
import com.common.util.ExceptionUtil;
import com.common.util.PatternUtil;
import com.common.util.PrimaryUtil;
import com.common.util.RandomUtil;
import com.common.util.SessionUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.api.constant.AttaTypeConstant;
import com.mornsun.app.core.service.catalog.ICatalogService;
import com.mornsun.app.core.service.coupon.ICouponService;
import com.mornsun.app.core.service.sms.ISmsService;
import com.mornsun.app.core.service.user.attention.IUserAttentionService;
import com.mornsun.app.core.service.user.base.IUserService;
import com.mornsun.app.core.service.user.catalog.IUserCatalogService;
import com.mornsun.app.core.service.user.coupon.IUserCouponService;
import com.mornsun.jsw.api.api.user.base.IUserApi;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;
import com.mornsun.jsw.api.vo.coupon.CouponVo;
import com.mornsun.jsw.api.vo.user.attention.UserAttentionVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;
import com.mornsun.jsw.api.vo.user.coupon.UserCouponVo;

@Service
public class UserServiceImpl extends BasePageHelperApiServiceImpl<UserVo, IUserApi> implements IUserService {

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private ISmsService smsService;

	@Value("${sms.code.template.id}")
	private String smsCodeTemplateId;

	@Autowired
	private IUploadService uploadService;

	@Autowired
	private IUserCatalogService userCatalogService;

	@Autowired
	private ICatalogService catalogService;

	@Autowired
	private ICouponService couponService;

	@Autowired
	private IUserCouponService userCouponService;

	@Autowired
	private IUserAttentionService userAttentionService;

	/**
	 * 用户注册
	 * 
	 * @param userVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserVo> register(UserVo userVo, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (userVo == null || StringUtils.isEmpty(userVo.getCode()) || StringUtils.isEmpty(userVo.getPassWord())
					|| StringUtils.isEmpty(userVo.getMobile())) {

				res = RESULT_DATA_NULL;

			} else {

				if (!PatternUtil.getInstance().isChinaPhoneLegal(userVo.getMobile())) {
					res = RESULT_FORMAT_ERROR;
					resultVo.setMsg("手机号码格式不正确");
				} else if (StringUtils.isNotEmpty(userVo.getCode())) {
					// 验证码验证
					Object obj = memcacheService.get(userVo.getMobile());
					if (obj != null) {
						if (obj.toString().equalsIgnoreCase(userVo.getCode())) {
							res = RESULT_SUCCESS;
							memcacheService.delete(userVo.getMobile());
						} else {
							res = RESULT_CODE_ERROR;
							resultVo.setMsg("验证码错误，请重新输入");
						}

					} else {
						res = RESULT_CODE_ERROR;
						resultVo.setMsg("验证码已过期，请点击重发");
					}
				} else if (!PatternUtil.getInstance().matchNumber(userVo.getPassWord())) {
					res = RESULT_FORMAT_ERROR;
					resultVo.setMsg("密码必须为6-16为字母数字组合");
				}

				if (res == RESULT_SUCCESS) {

					// 验证用户名是否存在
					UserVo userVoTmp = new UserVo();
					userVoTmp.setMobile(userVo.getMobile());
					userVoTmp = super.getOne(userVoTmp);
					if (userVoTmp != null) {// 用户名已存在
						res = RESULT_HAS_EXISTS;
						resultVo.setMsg("该手机号码已被注册");
					} else {

						// 随机码
						String randomCode = CodeUtil.getInstance().getSecurityCode(10);
						userVo.setRandomCode(randomCode);

						// 密码
						userVo.setPassWord(CommUtil.getInstance().getPassword(userVo.getMobile(), userVo.getPassWord(),
								randomCode));

						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"MMdd" + RandomUtil.getInstance().createRandom(true, 4));
						String numCode = dateFormat.format(Calendar.getInstance().getTime());

						// 注册用户
						userVo.setCreateBy("sys");
						userVo.setIsPushMessage(STATUS_NO);
						userVo.setState(STATUS_YES);
						userVo.setUserName(numCode);

						if (StringUtils.isEmpty(userVo.getUserAlias())) {
							userVo.setUserAlias(numCode);
						}

						ResultVo<UserVo> vo = super.save(userVo, request);
						res = vo.getRes();

						// 添加成功
						if (vo.getRes() == RESULT_SUCCESS) {

							// 赠送优惠卷
							CouponVo couponVo = new CouponVo();
							couponVo.setCouponType(AppConstant.COUPON_TYPE_REG);
							couponVo.setIsavailable(STATUS_YES);
							couponVo = couponService.getOne(couponVo);
							if (couponVo != null) {
								UserCouponVo userCouponVo = new UserCouponVo();
								userCouponVo.setCouponId(couponVo.getId());
								userCouponVo.setUserId(vo.getObj().getId());
								userCouponVo.setState(STATUS_NO);
								userCouponVo.setIsavailable(STATUS_YES);
								userCouponVo.setCreateBy("sys");
								userCouponVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
								ResultVo<UserCouponVo> resultVoTmp = userCouponService.save(userCouponVo, request);
								res = resultVoTmp.getRes();
								if (res == RESULT_SUCCESS) {
									// 更新用户总金额
									UserVo voTmp = new UserVo();
									voTmp.setId(vo.getObj().getId());
									voTmp.setUserCoupon(couponVo.getCouponMoney());
									ResultVo<UserVo> resultTmp = super.update(voTmp, request);
									res = resultTmp.getRes();

									// 设置用户优惠券金额
									userVo.setUserCoupon(couponVo.getCouponMoney());
								}
							}

							// 生成token信息
							String token = CommUtil.getInstance().getToken(request.getSession().getId());
							memcacheService.set(request.getSession().getId(), userVo, 0);

							resultVo.setToken(token);
							resultVo.setObj(userVo);
						}
					}

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][register][" + userVo.getClass().getSimpleName() + "="
					+ JSON.toJSONString(userVo) + "][res=" + res + "]");

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
	 * 用户登录
	 * 
	 * @param mobile
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserVo> login(UserVo userVo, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (userVo == null || StringUtils.isEmpty(userVo.getMobile())
					|| StringUtils.isEmpty(userVo.getPassWord())) {

				res = RESULT_DATA_NULL;

			} else {

				// 查询用户信息
				UserVo userVoTmp = new UserVo();
				userVoTmp.setMobile(userVo.getMobile());
				userVoTmp = super.getOne(userVoTmp);

				if (userVoTmp != null) {

					// 密码加密
					String passWord = CommUtil.getInstance().getPassword(userVo.getMobile(), userVo.getPassWord(),
							userVoTmp.getRandomCode());

					// 验证用户密码是否正确
					userVoTmp = new UserVo();
					userVoTmp.setMobile(userVo.getMobile());
					userVoTmp.setPassWord(passWord);
					userVoTmp = super.getOne(userVoTmp);

					if (userVoTmp != null) {

						if (!STATUS_YES.equals(userVoTmp.getIsavailable())) {// 已经失效

							res = RESULT_DATA_AVAILABLE;

						} else if (!STATUS_YES.equals(userVoTmp.getState())) {// 未激活

							res = RESULT_CODE_ERROR;

						} else {// 已经激活

							res = RESULT_SUCCESS;
						}

					} else {

						// 用户名密码错误
						res = RESULT_FAILURE;

					}

					// 登录成功
					if (res == RESULT_SUCCESS) {

						// 查询用户收益
						UserVo userVoNew = new UserVo();
						userVoNew.setId(userVoTmp.getId());
						userVoNew = (UserVo) super.getOneObject(userVoNew);
						if (userVoNew != null) {
							userVoTmp.setTotalMoney(userVoNew.getTotalMoney());
							userVoTmp.setNewProfitMoney(userVoNew.getNewProfitMoney());
						}

						// 生成token信息
						String token = CommUtil.getInstance().getToken(request.getSession().getId());
						memcacheService.set(request.getSession().getId(), userVoTmp, 0);

						resultVo.setToken(token);
						resultVo.setObj(userVoTmp);

					}

				} else {
					res = RESULT_NO_EXISTS;
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][login][" + userVo.getClass().getSimpleName() + "="
					+ JSON.toJSONString(userVo) + "][res=" + res + "]");

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
	 * 用户登录
	 * 
	 * @param mobile
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserVo> loginCode(UserVo userVo, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (userVo == null || StringUtils.isEmpty(userVo.getMobile()) || StringUtils.isEmpty(userVo.getCode())) {

				res = RESULT_DATA_NULL;

			} else {

				// 查询用户信息
				UserVo userVoTmp = new UserVo();
				userVoTmp.setMobile(userVo.getMobile());
				userVoTmp = super.getOne(userVoTmp);

				if (userVoTmp != null) {

					// 验证码验证
					Object obj = memcacheService.get(userVo.getMobile());
					if (obj != null) {
						if (obj.toString().equalsIgnoreCase(userVo.getCode())) {
							res = RESULT_SUCCESS;
							memcacheService.delete(userVo.getMobile());

							if (userVoTmp != null) {

								if (!STATUS_YES.equals(userVoTmp.getIsavailable())) {// 已经失效

									res = RESULT_DATA_AVAILABLE;

								} else if (!STATUS_YES.equals(userVoTmp.getState())) {// 未激活

									res = RESULT_CODE_ERROR;

								} else {// 已经激活

									res = RESULT_SUCCESS;
								}

							}

							// 登录成功
							if (res == RESULT_SUCCESS) {

								// 查询用户收益
								UserVo userVoNew = new UserVo();
								userVoNew.setId(userVoTmp.getId());
								userVoNew = (UserVo) super.getOneObject(userVoNew);
								if (userVoNew != null) {
									userVoTmp.setTotalMoney(userVoNew.getTotalMoney());
									userVoTmp.setNewProfitMoney(userVoNew.getNewProfitMoney());
								}

								// 生成token信息
								String token = CommUtil.getInstance().getToken(request.getSession().getId());
								memcacheService.set(request.getSession().getId(), userVoTmp, 0);

								resultVo.setToken(token);
								resultVo.setObj(userVoTmp);

							}

						} else {
							res = RESULT_CODE_ERROR;
							resultVo.setMsg("验证码错误，请重新输入");
						}

					} else {
						res = RESULT_CODE_ERROR;
						resultVo.setMsg("验证码已过期，请点击重发");
					}

				} else {
					res = RESULT_NO_EXISTS;
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][loginCode][" + userVo.getClass().getSimpleName()
					+ "=" + JSON.toJSONString(userVo) + "][res=" + res + "]");

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
	 * 授权登录
	 * 
	 * @param userVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserVo> auth(UserVo userVo, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (userVo == null || StringUtils.isEmpty(userVo.getAuthId())
					|| StringUtils.isEmpty(userVo.getAuthType())) {

				res = RESULT_DATA_NULL;

			} else {

				// 查询是否已经存在
				UserVo tmpUserVo = new UserVo();
				tmpUserVo.setAuthId(userVo.getAuthId());
				tmpUserVo.setAuthType(userVo.getAuthType());
				tmpUserVo = super.getOne(tmpUserVo);
				if (tmpUserVo != null) {// 授权数据已经存在，直接登录

					logger.error("[auth][tmpUserVo=" + tmpUserVo + "]授权数据已经存在，直接登录");

					// 查询用户收益
					UserVo userVoTmp = new UserVo();
					userVoTmp.setId(tmpUserVo.getId());
					userVoTmp = (UserVo) super.getOneObject(userVoTmp);
					if (userVoTmp != null) {
						tmpUserVo.setTotalMoney(userVoTmp.getTotalMoney());
						tmpUserVo.setNewProfitMoney(userVoTmp.getNewProfitMoney());
					}

					res = RESULT_SUCCESS;
					resultVo.setObj(tmpUserVo);

				} else {// 授权数据不存在，直接注册

					logger.error("[auth][tmpUserVo=" + tmpUserVo + "]授权数据不存在，插入数据");

					// 随机码
					String randomCode = CodeUtil.getInstance().getSecurityCode(10);
					userVo.setRandomCode(randomCode);

					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"MMdd" + RandomUtil.getInstance().createRandom(true, 4));
					String numCode = dateFormat.format(Calendar.getInstance().getTime());

					// 注册用户
					userVo.setCreateBy("sys");
					userVo.setIsPushMessage(STATUS_NO);
					userVo.setState(STATUS_YES);
					userVo.setUserName(numCode);

					resultVo = super.save(userVo, request);
					res = resultVo.getRes();
					if (res == RESULT_SUCCESS) {

						// 赠送优惠卷
						CouponVo couponVo = new CouponVo();
						couponVo.setCouponType(AppConstant.COUPON_TYPE_REG);
						couponVo.setIsavailable(STATUS_YES);
						couponVo = couponService.getOne(couponVo);
						if (couponVo != null) {
							UserCouponVo userCouponVo = new UserCouponVo();
							userCouponVo.setCouponId(couponVo.getId());
							userCouponVo.setUserId(resultVo.getObj().getId());
							userCouponVo.setState(STATUS_NO);
							userCouponVo.setIsavailable(STATUS_YES);
							userCouponVo.setCreateBy("sys");
							userCouponVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							ResultVo<UserCouponVo> resultVoTmp = userCouponService.save(userCouponVo, request);
							res = resultVoTmp.getRes();
							if (res == RESULT_SUCCESS) {
								// 更新用户总金额
								UserVo voTmp = new UserVo();
								voTmp.setId(resultVo.getObj().getId());
								voTmp.setUserCoupon(couponVo.getCouponMoney());
								ResultVo<UserVo> resultTmp = super.update(voTmp, request);
								res = resultTmp.getRes();

								// 设置优惠券金额
								userVo.setUserCoupon(couponVo.getCouponMoney());
							}
						}

						// 设置用户信息
						resultVo.setObj(userVo);
					}
				}

				// 授权成功
				if (res == RESULT_SUCCESS) {

					// 生成token信息
					String token = CommUtil.getInstance().getToken(request.getSession().getId());

					logger.info("[auth][token=" + token + "]");
					memcacheService.set(request.getSession().getId(), resultVo.getObj(), 0);

					resultVo.setToken(token);
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][auth][" + userVo.getClass().getSimpleName() + "="
					+ JSON.toJSONString(userVo) + "][res=" + res + "]");

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
	 * 短信验证码发送
	 * 
	 * @param mobile
	 * @param request
	 * @return
	 */
	public ResultVo<UserVo> code(String mobile, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (StringUtils.isEmpty(mobile)) {

				res = RESULT_DATA_NULL;

			} else {

				// 验证码
				String code = RandomUtil.getInstance().createRandom(true, 4);

				memcacheService.set(mobile, code, PrivilegeConstant.TIMEOUT_CODE);

				// 发送短信
				smsService.sendSMS(smsCodeTemplateId, mobile, new String[] { code, "10分钟" });

				res = RESULT_SUCCESS;

			}

			logger.info("[" + this.getClass().getSimpleName() + "][code][mobile=" + mobile + "][res=" + res + "]");

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
	 * 重置密码
	 * 
	 * @param userVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserVo> password(UserVo userVo, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (userVo == null || StringUtils.isEmpty(userVo.getMobile()) || StringUtils.isEmpty(userVo.getCode())
					|| StringUtils.isEmpty(userVo.getPassWord())) {

				res = RESULT_DATA_NULL;

			} else {

				if (!PatternUtil.getInstance().isChinaPhoneLegal(userVo.getMobile())) {
					res = RESULT_FORMAT_ERROR;
					resultVo.setMsg("手机号码格式不正确");
				} else if (StringUtils.isNotEmpty(userVo.getCode())) {
					// 验证码验证
					Object obj = memcacheService.get(userVo.getMobile());
					if (obj != null) {
						if (obj.toString().equalsIgnoreCase(userVo.getCode())) {
							res = RESULT_SUCCESS;
							memcacheService.delete(userVo.getMobile());
						} else {
							res = RESULT_CODE_ERROR;
							resultVo.setMsg("验证码错误，请重新输入");
						}

					} else {
						res = RESULT_CODE_ERROR;
						resultVo.setMsg("验证码已过期，请点击重发");
					}
				} else if (!PatternUtil.getInstance().matchNumber(userVo.getPassWord())) {
					res = RESULT_FORMAT_ERROR;
					resultVo.setMsg("密码必须为6-16为字母数字组合");
				}

				if (res == RESULT_SUCCESS) {

					// 验证用户名是否存在
					UserVo userVoTmp = new UserVo();
					userVoTmp.setMobile(userVo.getMobile());
					userVoTmp = super.getOne(userVoTmp);
					if (userVoTmp != null) {// 用户名已存在

						String id = userVoTmp.getId();

						// 密码
						String passWord = CommUtil.getInstance().getPassword(userVo.getMobile(), userVo.getPassWord(),
								userVoTmp.getRandomCode());
						userVoTmp = new UserVo();
						userVoTmp.setPassWord(passWord);
						userVoTmp.setId(id);

						// 注册用户
						ResultVo<UserVo> vo = super.update(userVoTmp, request);
						res = vo.getRes();

						// 修改成功
						if (vo.getRes() == RESULT_SUCCESS) {

							// 生成token信息
							String token = CommUtil.getInstance().getToken(request.getSession().getId());
							memcacheService.set(request.getSession().getId(), userVoTmp, 0);

							resultVo.setToken(token);
							resultVo.setObj(userVoTmp);
						}
					} else {

						res = RESULT_DATA_NULL;

					}

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][password][" + userVo.getClass().getSimpleName() + "="
					+ JSON.toJSONString(userVo) + "][res=" + res + "]");

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
	 * 更新用户信息
	 * 
	 * @param userVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserVo> update(UserVo userVo, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<UserVo>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (userVo == null || StringUtils.isEmpty(userVo.getSessionId())) {

				res = RESULT_DATA_NULL;

			} else {

				// 获取用户信息
				UserVo userVoTmp = (UserVo) memcacheService.get(userVo.getSessionId());
				if (userVoTmp == null) {
					res = RESULT_NO_EXISTS;
				} else {
					res = RESULT_SUCCESS;
				}

				// 修改用户信息
				UserVo userVoNew = new UserVo();
				userVoNew.setId(userVoTmp.getId());
				userVoNew.setUpdateBy(userVoTmp.getId());

				// 修改手机号码
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(userVo.getMobile())
						&& StringUtils.isNotEmpty(userVo.getCode())) {

					if (!PatternUtil.getInstance().isChinaPhoneLegal(userVo.getMobile())) {
						res = RESULT_FORMAT_ERROR;
						resultVo.setMsg("手机号码格式不正确");
					} else if (userVo.getMobile().equals(userVoTmp.getMobile())) {
						res = RESULT_HAS_EXISTS;
						resultVo.setMsg("手机号码不能与当前手机号码一致");
					} else if (StringUtils.isNotEmpty(userVo.getCode())) {
						// 验证码验证
						Object obj = memcacheService.get(userVo.getMobile());
						if (obj != null) {
							if (obj.toString().equalsIgnoreCase(userVo.getCode())) {
								userVoNew.setMobile(userVo.getMobile());
								memcacheService.delete(userVo.getMobile());
							} else {
								res = RESULT_CODE_ERROR;
								resultVo.setMsg("验证码错误，请重新输入");
							}

						} else {
							res = RESULT_CODE_ERROR;
							resultVo.setMsg("验证码已过期，请点击重发");
						}
					}

				}

				// 修改密码
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(userVo.getPassWord())
						&& StringUtils.isNotEmpty(userVo.getNewPassWord())) {

					// 密码
					String passWord = CommUtil.getInstance().getPassword(userVoTmp.getMobile(), userVo.getNewPassWord(),
							userVoTmp.getRandomCode());
					if (passWord.equals(userVoTmp.getPassWord())) {
						res = RESULT_HAS_EXISTS;
					} else {
						userVoNew.setPassWord(passWord);
					}

				}

				// 修改消息推送
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(userVo.getIsPushMessage())) {
					userVoNew.setIsPushMessage(userVo.getIsPushMessage());
				}

				// 头像修改
				if (res == RESULT_SUCCESS) {
					List<BaseAttaModel> attaModels = uploadService.upload(userVo.getId(),
							AttaTypeConstant.HEAD.getType(), AttaTypeConstant.HEAD.getName(), request);
					for (BaseAttaModel tmpImageVo : attaModels) {
						if (StringUtils.isNotEmpty(tmpImageVo.getFilePath())) {
							userVoNew.setHeadImage(tmpImageVo.getFilePath());
							break;
						}
					}
				}

				// 修改昵称
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(userVo.getUserAlias())) {

					// 修改昵称（不能重复）
					UserVo vo = new UserVo();
					vo.setUserAlias(userVo.getUserAlias());
					vo = super.getOne(vo);
					if (vo != null) {
						res = RESULT_HAS_EXISTS;
						resultVo.setMsg("该昵称已被注册");
					} else {
						userVoNew.setUserAlias(userVo.getUserAlias());
					}

				}

				// 修改用户介绍
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(userVo.getIntro())) {
					userVoNew.setIntro(userVo.getIntro());
				}

				// 修改用户头衔
				if (res == RESULT_SUCCESS && StringUtils.isNotEmpty(userVo.getPositionId())) {
					userVoNew.setPositionId(userVo.getPositionId());
				}

				List<UserCatalogVo> userCatalogVos = new ArrayList<>();

				// 保存专家擅长领域
				if (StringUtils.isNotEmpty(userVo.getCatalogId())) {

					String catalogArray[] = userVo.getCatalogId().split(",");
					// 先删除用户分类关系
					UserCatalogVo userCatalogVo = new UserCatalogVo();
					userCatalogVo.setUserId(userVo.getId());
					userCatalogVo.setType(AppConstant.AREA_TYPE_INTEREST);
					userCatalogService.delete(userCatalogVo);

					for (String catalogId : catalogArray) {
						if (StringUtils.isEmpty(catalogId)) {
							continue;
						}

						UserCatalogVo userCatalogVoTmp = new UserCatalogVo();
						userCatalogVoTmp.setUserId(userVo.getId());
						userCatalogVoTmp.setType(AppConstant.AREA_TYPE_INTEREST);
						userCatalogVoTmp.setCatalogId(catalogId);

						// 添加产品应用领域
						ResultVo<UserCatalogVo> resultVoTmp = userCatalogService.save(userCatalogVoTmp, request);
						res = resultVoTmp.getRes();
						if (res != RESULT_SUCCESS) {
							break;
						} else {

							CatalogVo catalogVo = catalogService.getOneById(catalogId);
							UserCatalogVo userCatalogVoListTmp = new UserCatalogVo();
							userCatalogVoListTmp.setCatalogVo(catalogVo);
							userCatalogVos.add(userCatalogVoListTmp);

						}
					}
				}

				if (res == RESULT_SUCCESS) {
					// 更新数据
					ResultVo<UserVo> vo = super.update(userVoNew, request);
					res = vo.getRes();
					if (res == RESULT_SUCCESS) {

						UserVo userVoQry = super.getOneById(userVoTmp.getId());
						memcacheService.set(request.getSession().getId(), userVoQry, 0);
						resultVo.setObj(userVoQry);

					}
					resultVo.getObj().setUserCatalogVos(userCatalogVos);

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][update][" + userVo.getClass().getSimpleName() + "="
					+ JSON.toJSONString(userVo) + "][res=" + res + "]");

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
	 * 用户明细
	 */
	public ResultVo<UserVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<UserVo> resultVo = new ResultVo<UserVo>();
		int res = RESULT_FAILURE;
		try {

			resultVo = super.detail(id, request);
			res = resultVo.getRes();
			if (res == RESULT_SUCCESS) {

				// 查询用户收益
				UserVo userVoTmp = new UserVo();
				userVoTmp.setId(id);
				userVoTmp = (UserVo) super.getOneObject(userVoTmp);
				if (userVoTmp != null) {
					resultVo.getObj().setTotalMoney(userVoTmp.getTotalMoney());
					resultVo.getObj().setNewProfitMoney(userVoTmp.getNewProfitMoney());
				}

				// 获取登录信息
				UserVo userVo = (UserVo) memcacheService.get(SessionUtil.getInstance().getSessionId(request));
				if (userVo != null) {

					// 查询是否已经关注
					if (id.equals(userVo.getId())) {
						resultVo.getObj().setIsAttention(STATUS_YES);
					} else {
						UserAttentionVo userAttentionVo = new UserAttentionVo();
						userAttentionVo.setUserId(userVo.getId());
						userAttentionVo.setAttentionUserId(id);
						userAttentionVo.setFlag(true);
						userAttentionVo = userAttentionService.getOne(userAttentionVo);
						if (userAttentionVo != null) {
							resultVo.getObj().setIsAttention(STATUS_YES);
						} else {
							resultVo.getObj().setIsAttention(STATUS_NO);
						}
					}

				}
			}

			logger.info("[" + this.getClass().getSimpleName() + "][detail][id=" + id + "][res=" + res + "]");
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
