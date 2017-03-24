package com.admin.api.model.notice;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系统通知Model
 * 
 * @author: HuiJunLuo
 * @date:2016年2月1日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysNoticeModel extends BaseModel {

    private static final long serialVersionUID = -9161068257394590470L;

    /**
     * 通知ID
     */
    @Primary
    private String noticeId;

    /**
     * 附件ID
     */
    private String attaId;

    /**
     * 标题
     */
    @Validate
    private String title;

    /**
     * 内容
     */
    @Validate
    private String content;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getAttaId() {
        return attaId;
    }

    public void setAttaId(String attaId) {
        this.attaId = attaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}