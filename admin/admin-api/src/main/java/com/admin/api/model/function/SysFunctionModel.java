package com.admin.api.model.function;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 系统功能Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysFunctionModel extends BaseModel {
    
    private static final long serialVersionUID = 7767072686507078475L;

    /**
     * 功能ID
     */
    @Primary
    private String functionId;

    /**
     * 父级ID
     */
    @Validate
    private String parentId;

    /**
     * 功能名称
     */
    @Validate
    private String functionName;

    /**
     * 控件ID
     */
    @Validate
    private String controlKey;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getControlKey() {
        return controlKey;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey == null ? null : controlKey.trim();
    }

}