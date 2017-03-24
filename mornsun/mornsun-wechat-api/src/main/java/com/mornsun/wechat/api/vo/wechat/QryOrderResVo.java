package com.mornsun.wechat.api.vo.wechat;

/**
 * 订单查询结果VO
 * 
 * @author: HuiJunLuo
 * @date:2016年5月17日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class QryOrderResVo extends PayOrderNotifyVo {

	private static final long serialVersionUID = 4154743719677918216L;

	/**
	 * 交易状态描述
	 */
	private String trade_state_desc;

	/**
	 * 交易状态
	 */
	private String trade_state;

	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
}
