package com.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.common.base.common.BaseLogger;
import com.common.base.constant.CommonConstant;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64加密解密
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@SuppressWarnings("restriction")
public class Base64Util extends BaseLogger {

    private static Base64Util base64Util = null;

    public synchronized static Base64Util getInstance() {
        if (base64Util == null) {
            base64Util = new Base64Util();
        }
        return base64Util;
    }

    private Base64Util() {

    }

    /**
     * 加密
     * 
     * @param str
     * @return
     */
    public String encodeStr(String str) {
        String s = null;
        byte[] b = null;
        try {
            b = str.getBytes(CommonConstant.CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        try {
            if (b != null) {
                BASE64Encoder base64Encoder = new BASE64Encoder();
                s = base64Encoder.encode(b);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return s;
    }

    /**
     * 加密
     * 
     * @param str
     * @return
     */
    public String encodeByte(byte[] b) {
        String str = null;
        try {
            if (b != null) {
                BASE64Encoder base64Encoder = new BASE64Encoder();
                str = base64Encoder.encode(b);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return str;
    }

    /**
     * 解密
     * 
     * @param str
     * @return
     */
    public String decodeStr(String str) {
        String result = null;
        byte[] b = null;
        try {
            if (str != null) {
                BASE64Decoder decoder = new BASE64Decoder();
                b = decoder.decodeBuffer(str);
                result = new String(b, CommonConstant.CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    public byte[] decodeByte(String str) {
        byte[] b = null;
        try {
            if (str != null) {
                BASE64Decoder decoder = new BASE64Decoder();
                b = decoder.decodeBuffer(str);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return b;
    }
}
