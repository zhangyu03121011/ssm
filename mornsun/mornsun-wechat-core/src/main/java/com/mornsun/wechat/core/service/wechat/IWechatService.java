package com.mornsun.wechat.core.service.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.base.vo.base.ResultVo;
import com.mornsun.wechat.api.vo.wechat.UserAccessUserVo;
import com.mornsun.wechat.api.vo.wechat.OrderReqVo;
import com.mornsun.wechat.api.vo.wechat.QryOrderResVo;
import com.mornsun.wechat.api.vo.wechat.PayOrderResVo;
import com.mornsun.wechat.api.vo.wechat.PaySignVo;
import com.mornsun.wechat.api.vo.wechat.UserAccessTokenVo;

/**
 * 微信Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IWechatService {

	/**
	 * 支付订单
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	public ResultVo<PaySignVo> order(OrderReqVo vo, HttpServletRequest request) throws Exception;

	/**
	 * 支付签名
	 * 
	 * @param prepayId
	 * @param request
	 * @return
	 */
	public ResultVo<PaySignVo> paySign(String prepayId, String appKey, String payType,HttpServletRequest request) throws Exception;

	/**
	 * 支付订单
	 * 
	 * @param openid
	 * @param orderNo
	 * @param productDesc
	 * @param attach
	 * @param totalAmount
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<PayOrderResVo> payOrder(String openid, String orderNo, String appKey, String productDesc,
			String attach, int totalAmount,String payType, HttpServletRequest request) throws Exception;
	
	/**
	 * 订单查询
	 * @param transactionId
	 * @param orderNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<QryOrderResVo> orderQuery(String transactionId, String orderNo, HttpServletRequest request) throws Exception;

	/**
	 * 获取用户AccessToken
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public ResultVo<UserAccessTokenVo> payAccess(String code, HttpServletRequest request) throws Exception;

	/**
	 * 获取授权用户
	 * 
	 * @param access_token
	 * @param openid
	 * @param request
	 * @return
	 */
	public ResultVo<UserAccessUserVo> accessUser(String access_token, String openid, HttpServletRequest request)
			throws Exception;

	/**
	 * 支付回调
	 * 
	 * @param request
	 * @return
	 */
	public String notify(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
