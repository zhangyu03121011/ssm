package com.common.tool;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64加密解密
 * 
 * @author: huijunluo
 * @date:Sep 19, 2015
 * @Copyright:Copyright (c) 深圳世纪博仕网络信息技术有限公司 2014 ~ 2015 版权所有
 */
public class Base64Util {

    /**
     * 加密
     * 
     * @param str
     * @return
     * @throws
     */
    public static String encodeStr(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            BASE64Encoder base64Encoder=new BASE64Encoder();
            s = base64Encoder.encode(b);
        }
        return s;
    }

    /**
     * 解密
     * 
     * @param str
     * @return
     * @throws
     */
    public static String decodeStr(String str) {
        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "GB2312");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
