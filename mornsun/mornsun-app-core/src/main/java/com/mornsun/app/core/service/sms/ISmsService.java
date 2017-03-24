package com.mornsun.app.core.service.sms;

import java.util.List;

import com.common.base.constant.BaseConstant;

/**
 * 短信发送
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISmsService extends BaseConstant {

    /**
     * 发送短信
     * 
     * @param mobiles
     *            String 接收号码
     * @param contents
     *            String 短信内容
     * @throws Exception
     */
    public void sendSMS(String smsTemplateId,List<String> mobiles, String[] contents) throws Exception;
    
    /**
     * 发送短信
     * 
     * @param mobiles
     *            String 接收号码
     * @param contents
     *            String 短信内容
     * @throws Exception
     */
    public void sendSMS(String smsTemplateId,String mobiles, String[] contents) throws Exception;

}
