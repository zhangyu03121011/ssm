package com.common.task.service.impl;

import org.springframework.scheduling.annotation.Async;

import com.common.task.service.ITaskService;

/**
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public abstract class TaskServiceImpl implements ITaskService {

    /**
     * 多线程同步任务
     * 
     * @param obj
     * @throws Exception
     */
	 @Async
    public abstract void syncTask(Object... obj) throws Exception;

}
