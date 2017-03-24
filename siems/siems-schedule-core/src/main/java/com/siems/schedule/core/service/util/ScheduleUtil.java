package com.siems.schedule.core.service.util;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * 调度工具
 * 
 * @author: HuiJunLuo
 * @date:2016年4月18日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ScheduleUtil {

    private static Logger logger = Logger.getLogger(ScheduleUtil.class);

    private static ScheduleUtil scheduleUtil = null;

    public synchronized static ScheduleUtil getInstance() {
        if (scheduleUtil == null) {
            scheduleUtil = new ScheduleUtil();
        }
        return scheduleUtil;
    }

    private ScheduleUtil() {
    }

    /**
     * 获取配置文件
     * 
     * @return
     */
    public String getScheduleConfig() {

        // 判断是否是Linux,否则取window路径
        String configFile = null;
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            configFile = System.getProperty("user.dir") + File.separator + "pamirsScheduleConfig.properties";
        } else {
            configFile = "/etc/icconfig/siems/schedule/pamirsScheduleConfig.properties";
        }
        logger.info("[ScheduleUtil][getScheduleConfig]configFile=" + configFile);
        return configFile;
    }
}
