package com.mornsun.wechat.core.service.wechat.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.constant.BaseConstant;
import com.common.base.constant.ResultConstant;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.db.mongo.service.IMongoService;
import com.common.util.CodeUtil;
import com.common.util.ExceptionUtil;
import com.common.util.HttpClientUtil;
import com.common.util.IpUtil;
import com.common.util.Md5Util;
import com.common.util.PrimaryUtil;
import com.mornsun.jsw.api.api.user.answer.IUserAnswerApi;
import com.mornsun.jsw.api.api.user.base.IUserApi;
import com.mornsun.jsw.api.api.user.course.IUserCourseApi;
import com.mornsun.jsw.api.api.user.footprint.IUserFootprintApi;
import com.mornsun.jsw.api.api.user.order.IUserOrderApi;
import com.mornsun.jsw.api.api.user.profit.IUserProfitApi;
import com.mornsun.jsw.api.api.user.question.IUserQuestionApi;
import com.mornsun.jsw.api.vo.user.answer.UserAnswerVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.footprint.UserFootprintVo;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;
import com.mornsun.jsw.api.vo.user.profit.UserProfitVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;
import com.mornsun.wechat.api.constant.OrderConstant;
import com.mornsun.wechat.api.constant.PayConstant;
import com.mornsun.wechat.api.constant.WechatConstant;
import com.mornsun.wechat.api.vo.wechat.OrderReqVo;
import com.mornsun.wechat.api.vo.wechat.PayOrderNotifyVo;
import com.mornsun.wechat.api.vo.wechat.PayOrderReqVo;
import com.mornsun.wechat.api.vo.wechat.PayOrderResVo;
import com.mornsun.wechat.api.vo.wechat.PaySignVo;
import com.mornsun.wechat.api.vo.wechat.QryOrderReqVo;
import com.mornsun.wechat.api.vo.wechat.QryOrderResVo;
import com.mornsun.wechat.api.vo.wechat.UserAccessTokenVo;
import com.mornsun.wechat.api.vo.wechat.UserAccessUserVo;
import com.mornsun.wechat.core.service.wechat.IWechatService;
import com.mornsun.wechat.core.task.TokenThread;
import com.mornsun.wechat.core.util.SignUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 微信Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class WechatServiceImpl implements IWechatService, ResultConstant {

	private final static Logger logger = Logger.getLogger(WechatServiceImpl.class);

	@Autowired
	private IUserOrderApi userOrderApi;

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IMongoService mongoService;

	@Autowired
	private IUserProfitApi userProfitApi;

	@Autowired
	private IUserApi userApi;

	@Autowired
	private IUserCourseApi userCourseApi;

	@Autowired
	private IUserAnswerApi userAnswerApi;

	@Autowired
	private IUserQuestionApi userQuestionApi;

	@Autowired
	private IUserFootprintApi userFootprintApi;

	@Value("${wechat.pay_notify_url}")
	private String wechatPayNotifyUrl;

	/**
	 * 获取用户AccessToken
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public ResultVo<UserAccessTokenVo> payAccess(String code, HttpServletRequest request) throws Exception {
		ResultVo<UserAccessTokenVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			if (StringUtils.isEmpty(code)) {
				res = RESULT_DATA_NULL;
			} else {

				/** 获取用户openid start */
				Map<String, String> param = new HashMap<String, String>();
				param.put("appid", TokenThread.appId);
				param.put("secret", TokenThread.appSecret);
				param.put("code", code);
				param.put("grant_type", "authorization_code");

				// 获取微信openid
				String returnStr = HttpClientUtil.getInstance()
						.post("https://api.weixin.qq.com/sns/oauth2/access_token", param);
				logger.info("[WechatServiceImpl][payAccess]returnStr:[" + returnStr + "]");
				UserAccessTokenVo userAccessTokenVo = JSON.parseObject(returnStr, UserAccessTokenVo.class);
				resultVo.setObj(userAccessTokenVo);
				logger.info("[WechatServiceImpl][payAccess][userAccessTokenVo=" + JSON.toJSONString(userAccessTokenVo)
						+ "]");

				/** 获取用户openid end */
				res = RESULT_SUCCESS;
			}

			logger.info("[WechatServiceImpl][payAccess][code=" + code + "][res=" + res + "]");
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
	 * 获取授权用户
	 * 
	 * @param access_token
	 * @param openid
	 * @param request
	 * @return
	 */
	public ResultVo<UserAccessUserVo> accessUser(String access_token, String openid, HttpServletRequest request)
			throws Exception {
		ResultVo<UserAccessUserVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			if (StringUtils.isEmpty(access_token) || StringUtils.isEmpty(openid)) {
				res = RESULT_DATA_NULL;
			} else {

				/** 获取用户信息 start */
				Map<String, String> param = new HashMap<String, String>();
				param.put("access_token", access_token);
				param.put("openid", openid);
				param.put("lang", "zh_CN");

				// 获取微信openid
				String returnStr = HttpClientUtil.getInstance().post("https://api.weixin.qq.com/sns/userinfo", param);
				logger.info("[WechatServiceImpl][accessUser]returnStr:[" + returnStr + "]");
				UserAccessUserVo accessUserVo = JSON.parseObject(returnStr, UserAccessUserVo.class);
				accessUserVo.setXmlStr(returnStr);
				resultVo.setObj(accessUserVo);
				logger.info("[WechatServiceImpl][accessUser][accessUserVo=" + JSON.toJSONString(accessUserVo) + "]");
				mongoService.insert(accessUserVo);

				/** 获取用户信息 end */
				res = RESULT_SUCCESS;
			}

			logger.info("[WechatServiceImpl][accessUser][access_token=" + access_token + "][openid=" + openid + "][res="
					+ res + "]");
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
	 * 支付签名
	 * 
	 * @param prepayId
	 * @param request
	 * @return
	 */
	public ResultVo<PaySignVo> paySign(String prepayId, String appKey, String payType, HttpServletRequest request)
			throws Exception {
		ResultVo<PaySignVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {
			// 组装微信授权参数
			PaySignVo paySignVo = new PaySignVo();
			paySignVo.setAppId(TokenThread.appId);
			paySignVo.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
			paySignVo.setSignType("MD5");

			// 随机字符串
			Random random = new Random();
			String nonceStr = Md5Util.getInstance()
					.md5Encode(String.valueOf(random.nextInt(10000)) + CodeUtil.getInstance().getSecurityCode(8));
			paySignVo.setNonceStr(nonceStr);

			// 生成签名算法
			SortedMap<Object, Object> signMap = new TreeMap<Object, Object>();
			if (PayConstant.PAY_TYPE_APP.equals(payType)) {
				paySignVo.setPackageStr("Sign=WXPay");
				paySignVo.setPartnerid(WechatConstant.WECHAT_BUSINESS_PAY_ACCOUNT_NO);
				paySignVo.setPrepayid(prepayId);
				signMap.put("appid", paySignVo.getAppId());
				signMap.put("timestamp", paySignVo.getTimeStamp());
				signMap.put("noncestr", paySignVo.getNonceStr());
				signMap.put("package", paySignVo.getPackageStr());
				signMap.put("partnerid", paySignVo.getPartnerid());
				signMap.put("prepayid", paySignVo.getPrepayid());
			} else if (PayConstant.PAY_TYPE_WECHAT.equals(payType)) {
				paySignVo.setPackageStr("prepay_id=" + prepayId);
				signMap.put("appId", paySignVo.getAppId());
				signMap.put("timeStamp", paySignVo.getTimeStamp());
				signMap.put("nonceStr", paySignVo.getNonceStr());
				signMap.put("package", paySignVo.getPackageStr());
				signMap.put("signType", paySignVo.getSignType());
			}

			String signStr = SignUtil.createSign(signMap, WechatConstant.WECHAT_BUSINESS_PAY_KEY);
			logger.info("[WechatServiceImpl][paySign]PaySIGN:" + signStr);
			paySignVo.setPaySign(signStr);
			paySignVo.setCreateTime(Calendar.getInstance().getTime());
			mongoService.insert(paySignVo);

			logger.info("[WechatServiceImpl][payAccess][paySignVo=" + JSON.toJSONString(paySignVo) + "]");
			resultVo.setObj(paySignVo);
			res = RESULT_SUCCESS;

			logger.info("[WechatServiceImpl][paySign][prepayId=" + JSON.toJSONString(prepayId) + "][res=" + res + "]");
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
	 * 订单查询
	 * 
	 * @param transactionId
	 * @param orderNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<QryOrderResVo> orderQuery(String transactionId, String orderNo, HttpServletRequest request)
			throws Exception {
		ResultVo<QryOrderResVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 查询订单
			QryOrderReqVo qryOrderReqVo = new QryOrderReqVo();
			qryOrderReqVo.setAppid(TokenThread.appId);
			// 附加数据,原样返回
			qryOrderReqVo.setMch_id(WechatConstant.WECHAT_BUSINESS_PAY_ACCOUNT_NO);// 商户编号
			// 随机字符串
			Random random = new Random();
			String nonceStr = Md5Util.getInstance()
					.md5Encode(String.valueOf(random.nextInt(10000)) + CodeUtil.getInstance().getSecurityCode(8));
			qryOrderReqVo.setNonce_str(nonceStr);
			// 商户订单号
			if (StringUtils.isNotEmpty(transactionId)) {
				qryOrderReqVo.setTransaction_id(transactionId);
			} else {
				qryOrderReqVo.setOut_trade_no(orderNo);
			}
			// 生成sign签名
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", qryOrderReqVo.getAppid());
			parameters.put("mch_id", qryOrderReqVo.getMch_id());
			if (StringUtils.isNotEmpty(qryOrderReqVo.getTransaction_id())) {
				parameters.put("transaction_id", qryOrderReqVo.getTransaction_id());
			} else {
				parameters.put("out_trade_no", qryOrderReqVo.getOut_trade_no());
			}
			parameters.put("nonce_str", qryOrderReqVo.getNonce_str());

			// 生成签名
			String signStr = SignUtil.createSign(parameters, WechatConstant.WECHAT_BUSINESS_PAY_KEY);
			logger.info("[WechatServiceImpl][orderQuery]SIGN:" + signStr);
			qryOrderReqVo.setSign(signStr);

			// 查询订单状态信息
			XStream xs = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
			xs.alias("xml", QryOrderReqVo.class);
			String xml = xs.toXML(qryOrderReqVo);
			logger.info("查询订单xml为:\n" + xml);

			String returnXml = HttpClientUtil.getInstance().post("https://api.mch.weixin.qq.com/pay/orderquery", xml);
			logger.info("返回结果:" + returnXml);

			// 返回结果
			QryOrderResVo qryOrderResVo = new QryOrderResVo();
			XStream xs1 = new XStream(new StaxDriver());
			xs1.alias("xml", QryOrderResVo.class);
			qryOrderResVo = (QryOrderResVo) xs1.fromXML(returnXml);
			qryOrderResVo.setXmlStr(returnXml);

			// 下单成功
			if (qryOrderResVo != null && "SUCCESS".equals(qryOrderResVo.getResult_code())) {

				// // 验证支付签名
				// // 生成sign签名
				// parameters = new TreeMap<Object, Object>();
				// parameters.put("appid", qryOrderResVo.getAppid());
				// parameters.put("attach", qryOrderResVo.getAttach());
				// parameters.put("bank_type", qryOrderResVo.getBank_type());
				// parameters.put("cash_fee", qryOrderResVo.getCash_fee());
				// parameters.put("fee_type", qryOrderResVo.getFee_type());
				// parameters.put("is_subscribe",
				// qryOrderResVo.getIs_subscribe());
				// parameters.put("mch_id", qryOrderResVo.getMch_id());
				// parameters.put("nonce_str", qryOrderResVo.getNonce_str());
				// parameters.put("openid", qryOrderResVo.getOpenid());
				// parameters.put("out_trade_no",
				// qryOrderResVo.getOut_trade_no());
				// parameters.put("result_code",
				// qryOrderResVo.getResult_code());
				// parameters.put("return_code",
				// qryOrderResVo.getReturn_code());
				// parameters.put("time_end", qryOrderResVo.getTime_end());
				// parameters.put("total_fee", qryOrderResVo.getTotal_fee());
				// parameters.put("trade_type", qryOrderResVo.getTrade_type());
				// parameters.put("transaction_id",
				// qryOrderResVo.getTransaction_id());
				//
				// // 生成签名
				// signStr = SignUtil.createSign(parameters,
				// WechatConstant.WECHAT_BUSINESS_PAY_KEY);
				// logger.info("[WechatServiceImpl][orderQuery]VALID SIGN:" +
				// signStr);
				// if (signStr.equals(qryOrderResVo.getSign())) {
				res = RESULT_SUCCESS;
				logger.info("[WechatServiceImpl][orderQuery]查询订单成功...");
				// } else {
				// res = RESULT_SIGN_ERROR;
				// logger.error("[WechatServiceImpl][orderQuery][orderNo=" +
				// qryOrderResVo.getOut_trade_no()
				// + "]查询订单回调签名错误...");
				// }

			} else {
				res = RESULT_FAILURE;
				logger.info("[WechatServiceImpl][orderQuery][payOrderResVo=" + JSON.toJSONString(qryOrderResVo)
						+ "]查询订单失败...");
			}

			logger.info("[WechatServiceImpl][orderQuery][orderNo=" + orderNo + "][transactionId=" + transactionId
					+ "][res=" + res + "]");
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
	 * 支付订单
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	public ResultVo<PayOrderResVo> payOrder(String openid, String orderNo, String appKey, String productDesc,
			String attach, int totalAmount, String payType, HttpServletRequest request) throws Exception {
		ResultVo<PayOrderResVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 统一下单支付
			PayOrderReqVo payOrderVo = new PayOrderReqVo();
			payOrderVo.setAppid(TokenThread.appId);
			// 附加数据,原样返回
			payOrderVo.setAttach(attach);
			payOrderVo.setBody(productDesc);// 商品描述
			payOrderVo.setMch_id(WechatConstant.WECHAT_BUSINESS_PAY_ACCOUNT_NO);// 商户编号
			payOrderVo.setNotify_url(wechatPayNotifyUrl);

			// 随机字符串
			Random random = new Random();
			String nonceStr = Md5Util.getInstance()
					.md5Encode(String.valueOf(random.nextInt(10000)) + CodeUtil.getInstance().getSecurityCode(8));
			payOrderVo.setNonce_str(nonceStr);

			// 商户订单号
			payOrderVo.setOut_trade_no(orderNo);
			// 商品金额
			payOrderVo.setTotal_fee((int) (totalAmount));// 单位：分
			if (PayConstant.PAY_TYPE_APP.equals(payType)) {
				payOrderVo.setTrade_type("APP");
			} else if (PayConstant.PAY_TYPE_WECHAT.equals(payType)) {
				payOrderVo.setTrade_type("JSAPI");
			}
			payOrderVo.setSpbill_create_ip(IpUtil.getInstance().getIpAddr(request));
			if (PayConstant.PAY_TYPE_WECHAT.equals(payType)) {
				payOrderVo.setOpenid(openid);
			}

			// 生成sign签名
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", payOrderVo.getAppid());
			parameters.put("mch_id", payOrderVo.getMch_id());
			parameters.put("device_info", payOrderVo.getDevice_info());
			parameters.put("nonce_str", payOrderVo.getNonce_str());
			parameters.put("body", payOrderVo.getBody());
			parameters.put("attach", payOrderVo.getAttach());
			parameters.put("out_trade_no", payOrderVo.getOut_trade_no());
			parameters.put("total_fee", payOrderVo.getTotal_fee());
			parameters.put("spbill_create_ip", payOrderVo.getSpbill_create_ip());
			parameters.put("notify_url", payOrderVo.getNotify_url());
			parameters.put("trade_type", payOrderVo.getTrade_type());
			if (PayConstant.PAY_TYPE_WECHAT.equals(payType)) {
				parameters.put("openid", payOrderVo.getOpenid());
			}

			// 生成签名
			String signStr = SignUtil.createSign(parameters, WechatConstant.WECHAT_BUSINESS_PAY_KEY);
			logger.info("[WechatServiceImpl][payOrder]SIGN:" + signStr);
			payOrderVo.setSign(signStr);

			XStream xs = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
			xs.alias("xml", PayOrderReqVo.class);
			String xml = xs.toXML(payOrderVo);
			logger.info("统一下单xml为:\n" + xml);
			payOrderVo.setXmlStr(xml);
			payOrderVo.setCreateTime(Calendar.getInstance().getTime());
			mongoService.insert(payOrderVo);

			String returnXml = HttpClientUtil.getInstance().post("https://api.mch.weixin.qq.com/pay/unifiedorder", xml);
			logger.info("返回结果:" + returnXml);

			// 返回结果
			PayOrderResVo payOrderResVo = new PayOrderResVo();
			XStream xs1 = new XStream(new StaxDriver());
			xs1.alias("xml", PayOrderResVo.class);
			payOrderResVo = (PayOrderResVo) xs1.fromXML(returnXml);
			payOrderResVo.setXmlStr(returnXml);

			// 用于储存
			payOrderResVo.setOrderNo(orderNo);
			payOrderResVo.setCreateTime(Calendar.getInstance().getTime());
			resultVo.setObj(payOrderResVo);
			mongoService.insert(payOrderResVo);

			// 下单成功
			if (payOrderResVo != null && "SUCCESS".equals(payOrderResVo.getResult_code())) {
				res = RESULT_SUCCESS;
				logger.info("[WechatServiceImpl][payOrder]下单成功...");
			} else {
				res = RESULT_FAILURE;
				logger.info(
						"[WechatServiceImpl][payOrder][payOrderResVo=" + JSON.toJSONString(payOrderResVo) + "]下单失败...");
			}

			logger.info("[WechatServiceImpl][payOrder][orderNo=" + orderNo + "][productDesc=" + productDesc
					+ "][attach=" + attach + "][totalAmount=" + totalAmount + "][res=" + res + "]");
		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// String str =
		// URLEncoder.encode("http://www.mornsunxiu.com/api/user/auth?id=&source=",
		// "utf-8");
		// System.out.println(str);

		// 生成签名算法
		SortedMap<Object, Object> signMap = new TreeMap<Object, Object>();
		signMap.put("appid", "wxe98932a68e99467c");
		signMap.put("timestamp", "1487835725");
		signMap.put("noncestr", "a8a0655c2c244efa7c6118fb3e1fe708");
		signMap.put("package", "Sign=WXPay");
		signMap.put("partnerid", "1440113002");
		signMap.put("prepayid", "wx201702231542059807ad30bb0750365869");

		// "appId": "wxe98932a68e99467c",
		// "partnerid": "1440113002",
		// "prepayid": "wx201702231542059807ad30bb0750365869",
		// "timeStamp": "1487835725",
		// "nonceStr": "a8a0655c2c244efa7c6118fb3e1fe708",
		// "packageStr": "Sign=WXPay",
		// "signType": "MD5",
		// "paySign": "058F458EFB221CE57216BC83583C6F31",
		// "createTime": "2017-02-23 15:42"

		// <xml>
		// <appid>wxe98932a68e99467c</appid>
		// <noncestr>a8a0655c2c244efa7c6118fb3e1fe708</noncestr>
		// <package>Sign=WXPay</package>
		// <partnerid>1440113002</partnerid>
		// <prepayid>wx201702231542059807ad30bb0750365869</prepayid>
		// <timestamp>1487835725</timestamp>
		// <sign>BEE052563A2E41018942FE609840774A</sign>
		// </xml>
		String signStr = SignUtil.createSign(signMap, WechatConstant.WECHAT_BUSINESS_PAY_KEY);
		System.out.println(signStr);
	}

	@Override
	public ResultVo<PaySignVo> order(OrderReqVo vo, HttpServletRequest request) throws Exception {
		ResultVo<PaySignVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			if (vo == null) {
				res = RESULT_DATA_NULL;
			} else {

				// 判断用户是否登录，未登录直接返回
				UserVo obj = (UserVo) memcacheService.get(vo.getSessionId());
				if (obj != null) {

					String openid = null;
					if (PayConstant.PAY_TYPE_WECHAT.equals(vo.getPayType())) {
						UserVo userVo = (UserVo) obj;
						// 获取openid
						openid = userVo.getOpenId();
					}

					// 生成订单
					ResultVo<PayOrderResVo> resultVoTmp = this.payOrder(openid, vo.getOrderNo(), vo.getAppKey(),
							vo.getProductDesc(), vo.getAttach(), vo.getTotalAmount(), vo.getPayType(), request);
					res = resultVoTmp.getRes();
					if (resultVoTmp.getRes() == RESULT_SUCCESS) {

						// 生成签名
						resultVo = this.paySign(resultVoTmp.getObj().getPrepay_id(), vo.getAppKey(), vo.getPayType(),
								request);
						res = resultVo.getRes();
						resultVo.getObj().setOrderNo(vo.getOrderNo());
						if (resultVo.getRes() != RESULT_SUCCESS) {
							logger.error("[WechatServiceImpl][order]生成微信签名失败...");
						}

					} else {
						logger.error("[WechatServiceImpl][order]生成微信订单信息失败...");
					}

				} else {
					res = RESULT_NO_LOGIN;
				}

			}

			logger.info("[WechatServiceImpl][accessUser][OrderVo=" + JSON.toJSONString(vo) + "][res=" + res + "]");
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
	 * 支付回调
	 * 
	 * @param request
	 * @return
	 */
	public String notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int res = RESULT_FAILURE;
		try {
			String returnXml = IOUtils.toString(request.getInputStream());
			logger.info("[WechatServiceImpl][notify]返回结果:" + returnXml);

			// 返回结果
			PayOrderNotifyVo payOrderNotifyVo = new PayOrderNotifyVo();
			XStream xs1 = new XStream(new StaxDriver());
			xs1.alias("xml", PayOrderNotifyVo.class);
			payOrderNotifyVo = (PayOrderNotifyVo) xs1.fromXML(returnXml);
			if (payOrderNotifyVo != null) {
				payOrderNotifyVo.setXmlStr(returnXml);
			}
			logger.info("[WechatServiceImpl][notify][payOrderNotifyVo=" + JSON.toJSONString(payOrderNotifyVo) + "]");

			// SAXReader reader = new SAXReader();
			// Document document = reader.read(request.getInputStream());
			// Element root = document.getRootElement();
			// List<Element> elementList = root.elements();
			// for (Element e : elementList) {
			// logger.info("[callback][" + e.getName() + " =" + e.getText() +
			// "]");
			// }

			// 更新订单状态
			if (payOrderNotifyVo != null) {

				// 支付成功
				if ("SUCCESS".equals(payOrderNotifyVo.getResult_code())) {
					UserOrderVo userOrderVo = new UserOrderVo();
					userOrderVo.setOrderNo(payOrderNotifyVo.getOut_trade_no());
					userOrderVo = userOrderApi.getOne(userOrderVo);
					if (userOrderVo != null) {

						// 状态为待支付
						if (OrderConstant.ORDER_STATE_WAIT.equals(userOrderVo.getState())) {

							// 验证支付签名
							// 生成sign签名
							SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
							parameters.put("appid", payOrderNotifyVo.getAppid());
							parameters.put("attach", payOrderNotifyVo.getAttach());
							parameters.put("bank_type", payOrderNotifyVo.getBank_type());
							parameters.put("cash_fee", payOrderNotifyVo.getCash_fee());
							parameters.put("fee_type", payOrderNotifyVo.getFee_type());
							parameters.put("is_subscribe", payOrderNotifyVo.getIs_subscribe());
							parameters.put("mch_id", payOrderNotifyVo.getMch_id());
							parameters.put("nonce_str", payOrderNotifyVo.getNonce_str());
							parameters.put("openid", payOrderNotifyVo.getOpenid());
							parameters.put("out_trade_no", payOrderNotifyVo.getOut_trade_no());
							parameters.put("result_code", payOrderNotifyVo.getResult_code());
							parameters.put("return_code", payOrderNotifyVo.getReturn_code());
							parameters.put("time_end", payOrderNotifyVo.getTime_end());
							parameters.put("total_fee", payOrderNotifyVo.getTotal_fee());
							parameters.put("trade_type", payOrderNotifyVo.getTrade_type());
							parameters.put("transaction_id", payOrderNotifyVo.getTransaction_id());

							// 生成签名
							String signStr = SignUtil.createSign(parameters, WechatConstant.WECHAT_BUSINESS_PAY_KEY);
							logger.info("[WechatServiceImpl][notify]SIGN:" + signStr);
							if (signStr.equals(payOrderNotifyVo.getSign())) {

								// 更新订单状态
								UserOrderVo orderVoTmp = new UserOrderVo();
								orderVoTmp.setId(userOrderVo.getId());
								orderVoTmp.setState(OrderConstant.ORDER_STATE_PAY);
								orderVoTmp.setUpdateBy("sys_notify");
								int count = userOrderApi.update(orderVoTmp);
								if (count > 0) {

									// 更新交易状态
									UserProfitVo userProfitVo = new UserProfitVo();
									userProfitVo.setOrderNo(payOrderNotifyVo.getOut_trade_no());
									userProfitVo.setState(OrderConstant.ORDER_STATE_PAY);
									userProfitApi.update(userProfitVo);

									// 查询收益信息
									UserProfitVo userProfitVoTmp = new UserProfitVo();
									userProfitVoTmp.setOrderNo(payOrderNotifyVo.getOut_trade_no());
									List<UserProfitVo> userProfitVos = userProfitApi.getAll(userProfitVoTmp);
									if (CollectionUtils.isNotEmpty(userProfitVos)) {

										for (UserProfitVo userProfitVoNew : userProfitVos) {
											// 提问方
											if (WechatConstant.WECHAT_SCALE_TYPE_QUESTION
													.equals(userProfitVoNew.getScaleType())
													|| WechatConstant.WECHAT_SCALE_TYPE_SHARE
															.equals(userProfitVoNew.getScaleType())
													|| WechatConstant.WECHAT_SCALE_TYPE_ANSWER
															.equals(userProfitVoNew.getScaleType())) {

												// 更新用户总金额
												UserVo voTmp = new UserVo();
												voTmp.setId(userProfitVoNew.getUserId());
												voTmp.setUserMoney(userProfitVoNew.getProfitMoney());
												userApi.update(voTmp);

											}
											// 回答方
											if (WechatConstant.WECHAT_SCALE_TYPE_ANSWER
													.equals(userProfitVoNew.getScaleType())) {
												// 秒懂
												if (WechatConstant.WECHAT_SOURCETYPE_COURSE
														.equals(userProfitVoNew.getSourceType())) {

													UserCourseVo tmpVo = new UserCourseVo();
													tmpVo.setId(userProfitVoNew.getSourceId());
													tmpVo.setReadCount(1);
													userCourseApi.update(tmpVo);

												} else if (WechatConstant.WECHAT_SOURCETYPE_ANSWER
														.equals(userProfitVoNew.getSourceType())) {// 问答

													UserAnswerVo tmpVo = new UserAnswerVo();
													tmpVo.setId(userProfitVoNew.getSourceId());
													tmpVo.setReadCount(1);
													userAnswerApi.update(tmpVo);

													// 查询回答信息
													tmpVo = userAnswerApi.getOneById(userProfitVoNew.getSourceId());
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
												userFootprintVo.setUserId(userOrderVo.getUserId());
												userFootprintVo.setCreateBy("sys");
												userFootprintVo.setIsavailable(BaseConstant.STATUS_YES);
												userFootprintVo.setSourceId(userProfitVoNew.getSourceId());
												userFootprintVo.setSourceType(userProfitVoNew.getSourceType());
												userFootprintApi.insert(userFootprintVo);
											}
										}

									}

									res = RESULT_SUCCESS;
									payOrderNotifyVo.setCreateBy("wechat");
									mongoService.insert(payOrderNotifyVo);
									logger.info("[WechatServiceImpl][notify][orderNo="
											+ payOrderNotifyVo.getOut_trade_no() + "]order succ...");

								} else {
									res = RESULT_FAILURE;
									logger.error("[WechatServiceImpl][notify][orderNo="
											+ payOrderNotifyVo.getOut_trade_no() + "]修改订单状态失败...");
								}
							} else {
								res = RESULT_SIGN_ERROR;
								logger.error("[WechatServiceImpl][notify][orderNo=" + payOrderNotifyVo.getOut_trade_no()
										+ "]订单回调签名错误...");
							}

						} else {
							res = RESULT_DATA_ERROR;
							logger.error("[WechatServiceImpl][notify][orderNo=" + payOrderNotifyVo.getOut_trade_no()
									+ "][state=" + userOrderVo.getState() + "]订单状态异常...");
						}

					} else {
						res = RESULT_DATA_NULL;
						logger.error("[WechatServiceImpl][notify][orderNo=" + payOrderNotifyVo.getOut_trade_no()
								+ "]订单数据不存在...");
					}
				} else {
					res = RESULT_FAILURE;
					logger.error("[WechatServiceImpl][notify][payOrderNotifyVo=" + JSON.toJSONString(payOrderNotifyVo)
							+ "]微信通知失败...");
				}

			} else {
				res = RESULT_DATA_NULL;
				logger.error("[WechatServiceImpl][notify]支付回调信息为空...");
			}
			logger.info("[WechatServiceImpl][notify][res=" + res + "]");
		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		if (res == RESULT_SUCCESS) {
			// return "SUCCESS";
			return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		} else {
			// return "FAIL";
			return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
		}
	}
}
