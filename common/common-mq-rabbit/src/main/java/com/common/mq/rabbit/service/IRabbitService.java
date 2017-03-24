package com.common.mq.rabbit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.common.base.constant.BaseConstant;

/**
 * Rabbit Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月20日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IRabbitService extends BaseConstant {

	/**
	 * 发送消息
	 * 
	 * @param entity
	 * @return
	 */
	public boolean sendMessage(Object entity) throws Exception;

	/**
	 * 通过key发送消息
	 * 
	 * @param key
	 */
	public boolean sendMessage(String key, Object entity) throws Exception;

	/**
	 * 获取mq模板
	 */
	public RabbitTemplate getRabbitTemplate() throws Exception;

}
