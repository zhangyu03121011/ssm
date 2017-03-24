package com.mornsun.wechat.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mornsun.wechat.api.vo.wechat.AccessTokenVo;
import com.mornsun.wechat.api.vo.wechat.JsapiTicketVo;
import com.mornsun.wechat.core.util.WechatUtil;

/**
 * 定时获取微信access_token的线程
 * 
 * @author: HuiJunLuo
 * @date:2016年2月27日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class TokenThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(TokenThread.class);

    // 第三方用户唯一凭证
    public static String appId = "";

    // 第三方用户唯一凭证密钥
    public static String appSecret = "";

    public static AccessTokenVo accessToken = null;

    public static JsapiTicketVo jsapiTicketVo = null;

    public void run() {
        while (true) {
            try {
                accessToken = WechatUtil.getAccessToken(appId, appSecret);
                if (null != accessToken) {

                    log.info("[TokenThread]获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(),
                            accessToken.getToken());

                    jsapiTicketVo = WechatUtil.getJsapiTicket(accessToken.getToken());
                    if (null != jsapiTicketVo) {

                        log.info("[TokenThread]获取jsapiTicket成功，有效时长{}秒 token:{}", jsapiTicketVo.getExpiresIn(),
                                jsapiTicketVo.getTicket());

                        // 休眠7000秒
                        Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
                    } else {
                        // 如果access_token为null，60秒后再获取
                        Thread.sleep(60 * 1000);
                    }

                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("[TokenThread]{}", e1);
                }
                log.error("[TokenThread]{}", e);
            }
        }
    }
}
