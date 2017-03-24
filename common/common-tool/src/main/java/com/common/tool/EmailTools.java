package com.common.tool;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailTools {
    public static void main(String[] args) {
        
//        #邮件配置
//        mail.host=smtp.163.com
//        mail.port=25
//        mail.auth=true
//        mail.timeout=2000
//        mail.username=qa4862741@163.com
//        mail.password=qaz4862741
        
        String from = "qa4862741@163.com";
        String toes = "huijun.luo@ceacsz.com.cn;luohuijun619@163.com;359123279@qq.com";
        String password = "qaz4862741";
        String subject = "test";
        String content = "111";
        String host = null;
        String name = null;
        Session session = null;
        MimeMessage message = null;
        Transport transport = null;
        try {
            // 根据发件Email算出发件的邮件服务器
            host = "smtp." + from.substring(from.indexOf("@") + 1, from.length());
            // 根据发件Email算出登陆邮件服务器的用户名
            name = from.substring(0, from.indexOf("@"));
            // 分割出多个收件人
            String[] to = toes.split(";");
            // 初始化一个存放属性的工具类
            Properties props = new Properties();
            // 设置发送Email的服务器
            props.put("mail.smtp.host", "192.168.0.57");
            // 对发送Email进行身份认证
            props.put("mail.smpt.auth", "true");
            // 得到与服务器的一个会话
            session = Session.getInstance(props, null);
            // 定义一个邮件消息
            message = new MimeMessage(session);
            // 可以设置一系列邮件属性的类
            BodyPart bp = new MimeBodyPart();
            Multipart mp = null;
            // 设置能够解析html标签的邮件
            bp.setContent(content, "text/html;charset=utf-8");
            // 可以存放多个BodyPart的类
            mp = new MimeMultipart();
            // 添加BodyPart到Multipart类
            mp.addBodyPart(bp);
            // 设置收件人
            message.setFrom(new InternetAddress(from));
            // 设置邮件主题
            message.setSubject(subject);
            // 设置邮件内容
            message.setContent(mp);
            // 添加多个收件人
            for (int i = 0; i < to.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            }
            transport = session.getTransport("smtp");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 登陆发邮件服务器，发邮件
        try {
            transport.connect("smtp.163.com", from, password);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
