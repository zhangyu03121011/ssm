package com.mornsun.wechat.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.Md5Util;
import com.mornsun.wechat.api.vo.wechat.SignVo;
import com.mornsun.wechat.core.task.TokenThread;

/**
 * 获取微信签名工具类
 * 
 * @author: HuiJunLuo
 * @date:2016年2月27日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    public static void main(String[] args) {
        String jsapi_ticket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        SignVo signVo = sign(jsapi_ticket, url);
        System.out.println(signVo);
    };

    /**
     * 生成签名算法
     * 
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static SignVo sign(String jsapiTicket, String url) {
        SignVo signVo = new SignVo();
        String nonceStr = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        logger.info(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error("[SignUtil]" + e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error("[SignUtil]" + e.getMessage(), e);
        }

        signVo.setUrl(url);
        signVo.setJsapiTicket(jsapiTicket);
        signVo.setNonceStr(nonceStr);
        signVo.setTimestamp(timestamp);
        signVo.setSignature(signature);
        signVo.setAppId(TokenThread.appId);

        return signVo;
    }

    /**
     * 生成微信支付签名
     * 
     * @param parameters
     * @param key
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String createSign(SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = Md5Util.getInstance().MD5(sb.toString()).toUpperCase();
        return sign;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
