package com.common.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.common.base.common.BaseLogger;
import com.common.task.service.ITaskService;

/**
 * 多线程任务
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SyncThreadTask extends BaseLogger implements Runnable {

    // 任务Service
    private ITaskService taskService;

    // 任务参数
    private Object[] obj;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss:SSS");

    public SyncThreadTask(ITaskService taskService, Object... obj) {
        this.taskService = taskService;
        this.obj = obj;
    }

    @Override
    public synchronized void run() {
        try {
            logger.info("---------------------------[sync task begin]-----------------------------");
            logger.info("开始task任务信息[run][beginTime=" + simpleDateFormat.format(Calendar.getInstance().getTime()) + "]");
            long beginTime = System.currentTimeMillis();

            taskService.syncTask(obj);

            long endTime = System.currentTimeMillis();
            logger.info("结束task任务信息[endTime=" + simpleDateFormat.format(Calendar.getInstance().getTime()) + "]");
            logger.info("同步task完成[useTime=" + (endTime - beginTime) / 1000 + "s]");

        } catch (Exception e) {
            logger.error("[SyncThreadTask][run]" + e.getMessage(), e);
        }
        logger.info("----------------------------[end]------------------------------");
    }

}
