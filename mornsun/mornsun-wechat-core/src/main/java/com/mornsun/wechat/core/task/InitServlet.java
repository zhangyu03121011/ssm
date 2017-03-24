package com.mornsun.wechat.core.task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化servlet
 * 
 * @author: HuiJunLuo
 * @date:2016年2月27日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(InitServlet.class);

    public void init() throws ServletException {

        // 获取web.xml中配置的参数
        TokenThread.appId = getInitParameter("appId");
        TokenThread.appSecret = getInitParameter("appSecret");

        logger.info("[InitServlet]weixin api appid:{}", TokenThread.appId);
        logger.info("[InitServlet]weixin api appsecret:{}", TokenThread.appSecret);

        // 未配置appId、appSecret时给出提示
        if ("".equals(TokenThread.appId) || "".equals(TokenThread.appSecret)) {
            String msg = "[InitServlet]appid and appsecret configuration error, please check carefully.";
            logger.error(msg);
            throw new ServletException(msg);
        } else {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}
