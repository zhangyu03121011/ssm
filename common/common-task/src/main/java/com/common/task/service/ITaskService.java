package com.common.task.service;

import com.common.base.constant.BaseConstant;

/**
 * Task Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ITaskService extends BaseConstant{

    /**
     * 多线程同步任务
     * 
     * @param obj
     * @throws Exception
     */
    public abstract void syncTask(Object... obj) throws Exception;

}
