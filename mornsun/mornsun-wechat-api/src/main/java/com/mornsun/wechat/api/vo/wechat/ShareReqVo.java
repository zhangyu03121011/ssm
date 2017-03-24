package com.mornsun.wechat.api.vo.wechat;

import java.io.Serializable;

/**
 * 分享VO
 * 
 * @author: HuiJunLuo
 * @date:2016年5月24日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ShareReqVo implements Serializable {

    private static final long serialVersionUID = -9035366118268773723L;

    /**
     * 用户授权code
     */
    private String code;

    /**
     * 主键id
     */
    private String id;

    /**
     * 分享来源
     */
    private String source;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
