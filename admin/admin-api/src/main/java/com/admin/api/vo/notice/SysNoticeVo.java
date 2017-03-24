package com.admin.api.vo.notice;

import java.util.Date;

import com.admin.api.model.notice.SysNoticeModel;

/**
 * 系统通知VO
 * 
 * @author: HuiJunLuo
 * @date:2016年2月1日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysNoticeVo extends SysNoticeModel {

    private static final long serialVersionUID = 5333230490379072698L;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
