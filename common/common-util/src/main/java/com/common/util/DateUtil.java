package com.common.util;

import java.util.Calendar;
import java.util.Date;

import com.common.base.common.BaseLogger;

/**
 * 主键工具类
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class DateUtil extends BaseLogger {

    private static DateUtil dateUtil = null;

    public synchronized static DateUtil getInstance() {
        if (dateUtil == null) {
            dateUtil = new DateUtil();
        }
        return dateUtil;
    }

    private DateUtil() {
    }

    /**
     * 获取当前时间
     * 
     * @return
     * @throws Exception
     */
    public Date getCurrDate() {
        try {
            return Calendar.getInstance().getTime();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new Date();
    }

}
