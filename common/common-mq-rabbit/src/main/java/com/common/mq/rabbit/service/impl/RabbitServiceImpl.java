package com.common.mq.rabbit.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.mq.rabbit.service.IRabbitService;
import com.common.util.ExceptionUtil;

/**
 * Redis Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月7日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
@Service
public class RabbitServiceImpl extends BaseLogger implements IRabbitService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public boolean sendMessage(Object entity) throws Exception {
		boolean flag = false;
		try {
			rabbitTemplate.convertAndSend(entity);
			flag = true;
			logger.info("[RabbitServiceImpl][sendMessage][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	@Override
	public boolean sendMessage(String key, Object entity) throws Exception {
		boolean flag = false;
		try {
			rabbitTemplate.convertAndSend(key, entity);
			flag = true;
			logger.info("[RabbitServiceImpl][sendMessage][key=" + key + "][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	@Override
	public RabbitTemplate getRabbitTemplate() throws Exception {
		try {
			return rabbitTemplate;
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

}
