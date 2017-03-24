package com.mornsun.app.core.service.sms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.common.base.common.BaseLogger;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.sms.ISmsService;

/**
 * 短信发送Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SmsServiceImpl extends BaseLogger implements ISmsService {

    @Value("${sms.host}")
    private String smsHost;

    @Value("${sms.port}")
    private String smsPort;

    @Value("${sms.account.sid}")
    private String smsAccountSid;

    @Value("${sms.account.token}")
    private String smsAccountToken;

    @Value("${sms.app.id}")
    private String smsAppId;

    /**
     * 发送短信
     * 
     * @param mobiles
     *            String 接收号码
     * @param contents
     *            String 短信内容
     * @throws Exception
     */
    public void sendSMS(String smsTemplateId, List<String> mobiles, String[] contents) throws Exception {
        try {
            if (CollectionUtils.isNotEmpty(mobiles)) {
                StringBuffer buffer = new StringBuffer();
                for (String str : mobiles) {
                    buffer.append(str).append(",");
                }
                this.sendSMS(smsTemplateId,
                        buffer.toString().trim().substring(0, buffer.toString().trim().length() - 1), contents);
            }
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 发送短信
     * 
     * @param mobiles
     *            String 接收号码
     * @param contents
     *            String 短信内容
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Async
    public void sendSMS(String smsTemplateId, String mobiles, String[] contents) throws Exception {
        try {
            // 初始化SDK
            CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
            restAPI.init(smsHost, smsPort);
            restAPI.setAccount(smsAccountSid, smsAccountToken);
            restAPI.setAppId(smsAppId);
            HashMap<String, Object> result = restAPI.sendTemplateSMS(mobiles, smsTemplateId, contents);

            logger.info("[sendSMS][mobiles=" + mobiles + "]SDKTestGetSubAccounts result=" + result);
            if ("000000".equals(result.get("statusCode"))) {
                // 正常返回输出data包体信息（map）
                HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for (String key : keySet) {
                    Object object = data.get(key);
                    logger.info("[sendSMS]" + key + " = " + object);
                }
            } else {
                // 异常返回输出错误码和错误信息
                logger.error("[sendSMS][mobiles=" + mobiles + "]错误码=" + result.get("statusCode") + " 错误信息= "
                        + result.get("statusMsg"));
            }
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        HashMap<String, Object> result = null;

        // 初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        // ******************************注释*********************************************
        // *初始化服务器地址和端口 *
        // *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        // *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
        // *******************************************************************************
        restAPI.init("app.cloopen.com", "8883");

        // ******************************注释*********************************************
        // *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
        // *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        // *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
        // *******************************************************************************
        restAPI.setAccount("8a48b5514efd1c3a014f01e13bee06f4", "0d16983276874ae888f8c530d6701da4");

        // ******************************注释*********************************************
        // *初始化应用ID *
        // *测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID *
        // *应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        // *******************************************************************************
        restAPI.setAppId("aaf98f894efcded4014f01fa2097083d");

        // ******************************注释****************************************************************
        // *调用发送模板短信的接口发送短信 *
        // *参数顺序说明： *
        // *第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号 *
        // *第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。 *
        // *系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        // *第三个参数是要替换的内容数组。 *
        // **************************************************************************************************

        // **************************************举例说明***********************************************************************
        // *假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为
        // *
        // *result = restAPI.sendTemplateSMS("13800000000","1" ,new
        // String[]{"6532","5"}); *
        // *则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入 *
        // *********************************************************************************************************************
        result = restAPI.sendTemplateSMS("18665947828", "32197", new String[] { "", "" });

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            // 正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            // 异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }
    }

}
