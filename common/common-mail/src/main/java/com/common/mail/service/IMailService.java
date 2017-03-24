package com.common.mail.service;

import java.util.List;

import com.common.base.constant.BaseConstant;

/**
 * 邮件发送
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IMailService extends BaseConstant {

	/**
	 * 邮件发送
	 * 
	 * @param receivers
	 * @param ccAddress
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	public void sendMail(List<String> receivers, List<String> ccAddress, String subject, String content)
			throws Exception;

	/**
	 * 发送邮件
	 * 
	 * @param receivers
	 * @param ccAddress
	 * @param t
	 * @param subject
	 * @param templateName
	 * @throws Exception
	 */
	public <T> void sendMailByTemplate(List<String> receivers, List<String> ccAddress, T t, String subject,
			String templateName) throws Exception;
}
