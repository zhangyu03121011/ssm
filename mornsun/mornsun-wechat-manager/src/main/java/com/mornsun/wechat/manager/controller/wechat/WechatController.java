package com.mornsun.wechat.manager.controller.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.common.base.vo.base.ResultVo;
import com.common.util.HttpClientUtil;
import com.mornsun.wechat.api.vo.wechat.QryOrderResVo;
import com.mornsun.wechat.core.service.wechat.IWechatService;

/**
 * 微信Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年4月2日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/pay")
public class WechatController {

	private final static Logger logger = Logger.getLogger(WechatController.class);

	@Autowired
	private IWechatService wechatService;

	/**
	 * 支付回调
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/notify")
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		try {
			result = wechatService.notify(request, response);
			response.getWriter().write(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询订单状态
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/order")
	public ResultVo<QryOrderResVo> orderQuery(String orderNo, String transactionId, HttpServletRequest request,
			HttpServletResponse response) {
		ResultVo<QryOrderResVo> resultVo = new ResultVo<>();
		try {
			resultVo = wechatService.orderQuery(transactionId, orderNo, request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resultVo;
	}

	/**
	 * 长链接转成短链接 提高扫码速度和成功率
	 *
	 * @param accessToken
	 * @param URL
	 * @return
	 */
	public static void main(String[] args) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx01d932c110af2031&redirect_uri=http%3A%2F%2Fwww.mornsunxiu.com%2Fapi%2Fuser%2Fauth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN",
				"Kjyu8Rw4gUaI29rrQGKuBQ9tqzBEKjC8sA5smeoXAlPtdRNCgA5Eq20jgh0XPu2YAKOfgeos4kH0GW40WnqGacBqBmRQV079GEnJ0leWulkZPtT9Jz2YJzsT50GckXVhXLXhAJAPDP");
		String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
		String result = HttpClientUtil.getInstance().post(requestUrl, String.format(jsonMsg, url));
		if (null != result) {
			JSONObject jsonObject = JSONObject.parseObject(result);
			System.out.println(jsonObject.getString("short_url"));
		}
	}

	/**
	 * 长链接转成短链接 提高扫码速度和成功率
	 *
	 * @param accessToken
	 * @param URL
	 * @return
	 */
	public static String shortURL(String accessToken, String url) {
		String shortURL = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
		String result = HttpClientUtil.getInstance().post(requestUrl, String.format(jsonMsg, url));
		if (StringUtils.isNotEmpty(result)) {
			JSONObject jsonObject = JSONObject.parseObject(result);
			try {
				shortURL = jsonObject.getString("short_url");
				logger.info("生成短链接成功 shortURL:{" + shortURL + "}");
			} catch (Exception e) {
				int errorCode = jsonObject.getIntValue("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("创建永久带参二维码失败 errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
			}
		}
		return shortURL;
	}

}
