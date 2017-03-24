package com.mornsun.wechat.core.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.service.service.base.impl.BaseControllerServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.common.util.RandomUtil;
import com.mornsun.jsw.api.api.payscale.IPayScaleApi;
import com.mornsun.jsw.api.api.user.answer.IUserAnswerApi;
import com.mornsun.jsw.api.api.user.base.IUserApi;
import com.mornsun.jsw.api.api.user.course.IUserCourseApi;
import com.mornsun.jsw.api.api.user.footprint.IUserFootprintApi;
import com.mornsun.jsw.api.api.user.order.IUserOrderApi;
import com.mornsun.jsw.api.api.user.pay.IUserPayApi;
import com.mornsun.jsw.api.api.user.profit.IUserProfitApi;
import com.mornsun.jsw.api.api.user.question.IUserQuestionApi;
import com.mornsun.jsw.api.vo.payscale.PayScaleVo;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.footprint.UserFootprintVo;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;
import com.mornsun.jsw.api.vo.user.pay.UserPayVo;
import com.mornsun.jsw.api.vo.user.profit.UserProfitVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;
import com.mornsun.wechat.api.constant.OrderConstant;
import com.mornsun.wechat.api.constant.PayConstant;
import com.mornsun.wechat.api.constant.WechatConstant;
import com.mornsun.wechat.api.vo.order.OrderVo;
import com.mornsun.wechat.api.vo.wechat.OrderReqVo;
import com.mornsun.wechat.api.vo.wechat.PaySignVo;
import com.mornsun.wechat.core.service.order.IOrderService;
import com.mornsun.wechat.core.service.wechat.IWechatService;

/**
 * 订单Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class OrderServiceImpl implements IOrderService, BaseConstant {

	private final static Logger logger = Logger.getLogger(BaseControllerServiceImpl.class);

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IWechatService wechatService;

	@Autowired
	private IUserOrderApi userOrderApi;

	@Autowired
	private IUserProfitApi userProfitApi;

	@Autowired
	private IUserPayApi userPayApi;

	@Autowired
	private IUserApi userApi;

	@Autowired
	private IPayScaleApi payScaleApi;

	@Autowired
	private IUserCourseApi userCourseApi;

	@Autowired
	private IUserAnswerApi userAnswerApi;

	@Autowired
	private IUserQuestionApi userQuestionApi;

	@Autowired
	private IUserFootprintApi userFootprintApi;

	/**
	 * 提交订单
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<OrderVo> save(OrderVo vo, HttpServletRequest request) throws Exception {
		ResultVo<OrderVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 判断支付金额
			if (vo.getTotalMoney() <= 0) {

				res = RESULT_DATA_NULL;
				resultVo.setMsg("订单金额为空");
				logger.error("[OrderServiceImpl][save]订单金额为空");

			} else if (StringUtils.isEmpty(vo.getPayType())) {

				res = RESULT_DATA_NULL;
				resultVo.setMsg("订单支付类别为空");
				logger.error("[OrderServiceImpl][save]订单支付类别为空");

			} else {

				// 判断用户是否登录，未登录直接返回
				UserVo userVo = (UserVo) memcacheService.get(vo.getSessionId());
				if (userVo != null) {

					// 获取订单编号
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
					// 订单编号
					String orderNo = simpleDateFormat.format(Calendar.getInstance().getTime())
							+ RandomUtil.getInstance().createRandom(true, 2);

					String sourceId = PrimaryUtil.getInstance().getPrimaryValue();

					boolean wechatPayFlag = false;

					double answerPayScale = 1;
					double framePayScale = 0;
					double questionPayScale = 0;
					double sharePayScale = 0;

					// 判断支付类别
					// 收益数据
					if (WechatConstant.WECHAT_PAYTYPE_MONEY.equals(vo.getPayType())) {

						// 查询分成比例
						// 判断是否是问答
						if (WechatConstant.WECHAT_SOURCETYPE_ANSWER.equals(vo.getSourceType())) {
							PayScaleVo payScaleVo = new PayScaleVo();
							payScaleVo.setPayType(WechatConstant.WECHAT_SOURCETYPE_ANSWER);
							payScaleVo.setIsavailable(STATUS_YES);
							payScaleVo.setState(STATUS_YES);
							List<PayScaleVo> payScaleVos = payScaleApi.getAll(payScaleVo);
							if (CollectionUtils.isNotEmpty(payScaleVos)) {
								for (PayScaleVo payScaleVoTmp : payScaleVos) {
									// 分成用户：1-平台，2-发布方，3-提问方，4-分享方，5-回答方
									if (WechatConstant.WECHAT_SCALE_TYPE_ANSWER.equals(payScaleVoTmp.getScaleType())) {
										answerPayScale = payScaleVoTmp.getPayScale() / 100;
									} else if (WechatConstant.WECHAT_SCALE_TYPE_FRAME
											.equals(payScaleVoTmp.getScaleType())) {
										framePayScale = payScaleVoTmp.getPayScale() / 100;
									} else if (WechatConstant.WECHAT_SCALE_TYPE_QUESTION
											.equals(payScaleVoTmp.getScaleType())) {
										questionPayScale = payScaleVoTmp.getPayScale() / 100;
									} else if (WechatConstant.WECHAT_SCALE_TYPE_SHARE
											.equals(payScaleVoTmp.getScaleType())) {
										sharePayScale = payScaleVoTmp.getPayScale() / 100;
									}
								}

							}
						}

						wechatPayFlag = true;

						// 源交易信息
						UserProfitVo sourceVo = new UserProfitVo();
						sourceVo.setId(sourceId);
						sourceVo.setUserId(userVo.getId());
						sourceVo.setOrderNo(orderNo);
						sourceVo.setSourceId(vo.getSourceId());
						sourceVo.setSourceType(vo.getSourceType());
						sourceVo.setPayMoney(-vo.getTotalMoney());
						sourceVo.setProfitMoney(-vo.getTotalMoney());
						sourceVo.setMoneyType(WechatConstant.WECHAT_MONEYTYPE_MONEY);
						sourceVo.setScaleType(WechatConstant.WECHAT_SCALE_TYPE_PAY);
						sourceVo.setPayType(WechatConstant.WECHAT_PAYTYPE_OUT);
						sourceVo.setShareScale(0);
						sourceVo.setCreateBy(userVo.getId());
						sourceVo.setState(OrderConstant.ORDER_STATE_WAIT);
						int result = userProfitApi.insert(sourceVo);
						if (result > 0) {
							res = RESULT_SUCCESS;
						}

						// 目标交易信息
						UserProfitVo targetVo = new UserProfitVo();
						targetVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
						targetVo.setUserId(vo.getTargetUserId());
						targetVo.setOrderNo(orderNo);
						targetVo.setSourceId(vo.getSourceId());
						targetVo.setSourceType(vo.getSourceType());
						targetVo.setPayMoney(vo.getTotalMoney());
						targetVo.setProfitMoney(vo.getTotalMoney() * answerPayScale);
						targetVo.setShareScale(answerPayScale);
						targetVo.setScaleType(WechatConstant.WECHAT_SCALE_TYPE_ANSWER);
						targetVo.setMoneyType(WechatConstant.WECHAT_MONEYTYPE_MONEY);
						targetVo.setPayType(WechatConstant.WECHAT_PAYTYPE_IN);
						targetVo.setCreateBy(userVo.getId());
						targetVo.setState(OrderConstant.ORDER_STATE_WAIT);
						result = userProfitApi.insert(targetVo);
						if (result > 0) {
							res = RESULT_SUCCESS;
						}

						if (questionPayScale > 0
								&& WechatConstant.WECHAT_SOURCETYPE_ANSWER.equals(vo.getSourceType())) {

							// 查询回答信息
							UserAnswerVo answerVo = userAnswerApi.getOneById(vo.getSourceId());
							if (answerVo != null) {
								UserQuestionVo userQuestionVo = userQuestionApi.getOneById(answerVo.getQuestionId());
								if (userQuestionVo != null) {
									// 提问方目标交易信息
									UserProfitVo questionScaleVo = new UserProfitVo();
									questionScaleVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
									questionScaleVo.setUserId(userQuestionVo.getUserId());
									questionScaleVo.setOrderNo(orderNo);
									questionScaleVo.setSourceId(vo.getSourceId());
									questionScaleVo.setSourceType(vo.getSourceType());
									questionScaleVo.setPayMoney(vo.getTotalMoney());
									questionScaleVo.setProfitMoney(vo.getTotalMoney() * questionPayScale);
									questionScaleVo.setShareScale(questionPayScale);
									questionScaleVo.setScaleType(WechatConstant.WECHAT_SCALE_TYPE_QUESTION);
									questionScaleVo.setMoneyType(WechatConstant.WECHAT_MONEYTYPE_MONEY);
									questionScaleVo.setPayType(WechatConstant.WECHAT_PAYTYPE_IN);
									questionScaleVo.setCreateBy(userVo.getId());
									questionScaleVo.setState(OrderConstant.ORDER_STATE_WAIT);
									result = userProfitApi.insert(questionScaleVo);
									if (result > 0) {
										res = RESULT_SUCCESS;
									}
								}
							}

						}

						if (sharePayScale > 0) {

							// 分享交易信息
							// UserProfitVo shareScaleVo = new UserProfitVo();
							// shareScaleVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
							// shareScaleVo.setUserId(vo.getTargetUserId());
							// shareScaleVo.setOrderNo(orderNo);
							// shareScaleVo.setSourceId(vo.getSourceId());
							// shareScaleVo.setSourceType(vo.getSourceType());
							// shareScaleVo.setPayMoney(vo.getTotalMoney());
							// shareScaleVo.setProfitMoney(vo.getTotalMoney() *
							// sharePayScale);
							// shareScaleVo.setShareScale(sharePayScale);
							// shareScaleVo.setScaleType(WechatConstant.WECHAT_SCALE_TYPE_SHARE);
							// shareScaleVo.setMoneyType(WechatConstant.WECHAT_MONEYTYPE_MONEY);
							// shareScaleVo.setPayType(WechatConstant.WECHAT_PAYTYPE_IN);
							// shareScaleVo.setCreateBy(userVo.getId());
							// shareScaleVo.setState(OrderConstant.ORDER_STATE_WAIT);
							// result = userProfitApi.insert(targetVo);
							// if (result > 0) {
							// res = RESULT_SUCCESS;
							// }
						}

					} else if (WechatConstant.WECHAT_PAYTYPE_VIRTUAL.equals(vo.getPayType())) {// 交易数据

						// 源交易信息
						UserPayVo sourceVo = new UserPayVo();
						sourceVo.setId(sourceId);
						sourceVo.setUserId(userVo.getId());
						sourceVo.setOrderNo(orderNo);
						sourceVo.setSourceId(vo.getSourceId());
						sourceVo.setSourceType(vo.getSourceType());
						sourceVo.setPayMoney(-vo.getTotalMoney());
						sourceVo.setMoneyType(WechatConstant.WECHAT_MONEYTYPE_VIRTUAL);
						sourceVo.setPayType(WechatConstant.WECHAT_PAYTYPE_OUT);
						sourceVo.setCreateBy(userVo.getId());
						sourceVo.setState(OrderConstant.ORDER_STATE_PAY);
						int result = userPayApi.insert(sourceVo);
						if (result > 0) {
							res = RESULT_SUCCESS;

							// 更新用户总金额
							UserVo voTmp = new UserVo();
							voTmp.setId(userVo.getId());
							voTmp.setUserCoupon(-vo.getTotalMoney());
							result = userApi.update(voTmp);
							if (result > 0) {
								res = RESULT_SUCCESS;
							}
						}

						// 目标交易信息
						UserPayVo targetVo = new UserPayVo();
						targetVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
						targetVo.setUserId(vo.getTargetUserId());
						targetVo.setOrderNo(orderNo);
						targetVo.setSourceId(vo.getSourceId());
						targetVo.setSourceType(vo.getSourceType());
						targetVo.setPayMoney(vo.getTotalMoney());
						targetVo.setMoneyType(WechatConstant.WECHAT_MONEYTYPE_VIRTUAL);
						targetVo.setPayType(WechatConstant.WECHAT_PAYTYPE_IN);
						targetVo.setCreateBy(userVo.getId());
						targetVo.setState(OrderConstant.ORDER_STATE_PAY);
						result = userPayApi.insert(targetVo);
						if (result > 0) {
							res = RESULT_SUCCESS;

							// 更新用户总金额
							UserVo voTmp = new UserVo();
							voTmp.setId(vo.getTargetUserId());
							voTmp.setUserCoupon(vo.getTotalMoney());
							result = userApi.update(voTmp);
							if (result > 0) {
								res = RESULT_SUCCESS;
							}
						}

					} else {
						res = RESULT_DATA_ERROR;
					}

					// 保存订单信息
					if (res == RESULT_SUCCESS) {
						UserOrderVo userOrderVo = new UserOrderVo();
						userOrderVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
						userOrderVo.setCreateBy(userVo.getId());
						userOrderVo.setSourceId(sourceId);
						userOrderVo.setSourceType(vo.getPayType());

						if (wechatPayFlag) {
							userOrderVo.setState(OrderConstant.ORDER_STATE_WAIT);
						} else {
							userOrderVo.setState(OrderConstant.ORDER_STATE_PAY);
						}

						userOrderVo.setCouponId(null);
						userOrderVo.setUserId(userVo.getId());
						userOrderVo.setOrderNo(orderNo);
						userOrderVo.setShareMoney(vo.getTotalMoney() * framePayScale);
						userOrderVo.setShareScale(framePayScale);

						// 没有使用优惠券
						// 计算优惠金额
						userOrderVo.setCouponMoney(0);
						// 计算实际支付金额
						userOrderVo.setPayMoney(vo.getTotalMoney());
						int result = userOrderApi.insert(userOrderVo);
						if (result > 0) {
							res = RESULT_SUCCESS;

							// 发起微信支付
							if (WechatConstant.WECHAT_PAYTYPE_MONEY.equals(vo.getPayType())) {
								// 订单编号
								String orderId = vo.getId();

								// 生成微信订单
								OrderReqVo orderReqVo = new OrderReqVo();
								orderReqVo.setAttach(WechatConstant.WECHAT_BUSINESS_PAY_ATTACH);
								orderReqVo.setOrderNo(userOrderVo.getOrderNo());
								orderReqVo.setProductDesc(vo.getOrderDesc());
								orderReqVo.setTotalAmount(
										Double.valueOf(Math.ceil(userOrderVo.getPayMoney() * 100)).intValue());
								// orderReqVo.setTotalAmount(1);// 测试数据，支付1分
								orderReqVo.setSessionId(vo.getSessionId());
								orderReqVo.setAppKey(vo.getAppKey());
								orderReqVo.setPayType(PayConstant.PAY_TYPE_APP);
								ResultVo<PaySignVo> voSign = wechatService.order(orderReqVo, request);
								res = voSign.getRes();
								if (voSign.getRes() == RESULT_SUCCESS) {
									vo.setPaySignVo(voSign.getObj());
									logger.error("[OrderServiceImpl][orderId=" + orderId + "][save]生成签名信息成功...");
								} else {
									resultVo.setMsg("生成签名信息失败");
									logger.error("[OrderServiceImpl][orderId=" + orderId + "][save]生成签名信息失败...");
								}
							} else {

								// 更新浏览量数据
								// 秒懂
								if (WechatConstant.WECHAT_SOURCETYPE_COURSE.equals(vo.getSourceType())) {

									UserCourseVo tmpVo = new UserCourseVo();
									tmpVo.setId(vo.getSourceId());
									tmpVo.setReadCount(1);
									userCourseApi.update(tmpVo);

								} else if (WechatConstant.WECHAT_SOURCETYPE_ANSWER.equals(vo.getSourceType())) {// 问答

									UserAnswerVo tmpVo = new UserAnswerVo();
									tmpVo.setId(vo.getSourceId());
									tmpVo.setReadCount(1);
									userAnswerApi.update(tmpVo);

									// 查询回答信息
									tmpVo = userAnswerApi.getOneById(vo.getSourceId());
									if (tmpVo != null) {// 更新问题浏览量
										UserQuestionVo tmpQVo = new UserQuestionVo();
										tmpQVo.setId(tmpVo.getQuestionId());
										tmpQVo.setReadCount(1);
										userQuestionApi.update(tmpQVo);
									}
								}

								// 插入用户足迹
								UserFootprintVo userFootprintVo = new UserFootprintVo();
								userFootprintVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
								userFootprintVo.setUserId(userVo.getId());
								userFootprintVo.setCreateBy("sys");
								userFootprintVo.setIsavailable(BaseConstant.STATUS_YES);
								userFootprintVo.setSourceId(vo.getSourceId());
								userFootprintVo.setSourceType(vo.getSourceType());
								userFootprintApi.insert(userFootprintVo);

								// 扣减用户芯币总额
								// 查询用户优惠券总额
								UserVo userVoTmp = userApi.getOneById(userVo.getId());
								if (userVoTmp != null) {
									vo.setTotalMoney(userVoTmp.getUserCoupon() - vo.getTotalMoney());
								}
							}

						} else {
							logger.error("[OrderServiceImpl][save]保存订单信息失败");
							resultVo.setMsg("保存订单信息失败");
						}

						resultVo.setObj(vo);
					}

				} else {
					res = RESULT_NO_LOGIN;
					resultVo.setMsg("用户未登录，请先登录");
					logger.error("[OrderServiceImpl][save]用户未登录，请先登录...");
				}

			}
			logger.info("[OrderServiceImpl][save][OrderVo =" + vo.getOrderNo() + "][res=" + res + "]");
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
