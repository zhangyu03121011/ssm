package com.common.mail.service.impl;

import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.common.base.common.BaseLogger;
import com.common.mail.service.IMailService;
import com.common.util.ExceptionUtil;

import freemarker.template.Template;

/**
 * 邮件发送Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class MailServiceImpl extends BaseLogger implements IMailService {

	@Value("${mail.username}")
	private String sendForm;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * 发送邮件
	 * 
	 * @param receivers
	 * @param ccAddress
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	@Async
	public void sendMail(List<String> receivers, List<String> ccAddress, String subject, String content)
			throws Exception {

		logger.info("---------------------------[begin send async email]-----------------------------");

		// 去除空列表
		if (CollectionUtils.isNotEmpty(receivers)) {
			for (int i = 0; i < receivers.size(); i++) {
				if (StringUtils.isEmpty(receivers.get(i))) {
					receivers.remove(i);
				}
			}
		}
		if (CollectionUtils.isNotEmpty(ccAddress)) {
			for (int i = 0; i < ccAddress.size(); i++) {
				if (StringUtils.isEmpty(ccAddress.get(i))) {
					ccAddress.remove(i);
				}
			}
		}

		// 接收人为空
		if (receivers == null || receivers.isEmpty()) {
			throw new IllegalArgumentException("receivers is null");
		}

		try {

			// 建立邮件消息,发送简单邮件和html邮件的区别
			MimeMessage mailMessage = mailSender.createMimeMessage();

			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");

			// 设置收件人，寄件人 发送给多个账号
			Address[] addressArray = new Address[receivers.size()];
			int i = 0;
			for (String str : receivers) {
				addressArray[i] = new InternetAddress(str);
				i++;
			}
			mailMessage.setRecipients(Message.RecipientType.TO, addressArray);

			// CC给多个账号
			if (ccAddress != null && !ccAddress.isEmpty()) {
				Address[] ccAddressArray = new Address[ccAddress.size()];
				i = 0;
				for (String str : ccAddress) {
					ccAddressArray[i] = new InternetAddress(str);
					i++;
				}
				mailMessage.setRecipients(RecipientType.CC, ccAddressArray);
			}

			messageHelper.setFrom(sendForm); // 发件人
			messageHelper.setSubject(subject); // 主题

			// true 表示启动HTML格式的邮件
			messageHelper.setText(content, true); // 邮件内容，注意加参数true，表示启用html格式

			// 发送邮件
			mailSender.send(mailMessage);

			logger.info("[" + subject + "][" + receivers + "]mail send success");
			logger.info("---------------------------[end send async email]-----------------------------");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

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
			String templateName) throws Exception {
		try {

			// 从FreeMarker模板生成邮件内容
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);

			// 模板中用${XXX}站位，map中key为XXX的value会替换占位符内容。
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, t);

			// 发送邮件
			this.sendMail(receivers, ccAddress, subject, content);

		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

}
