package com.common.controller.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * String属性处理
 * 
 * @author: HuiJunLuo
 * @date:2016年1月29日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class StringEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            text = null;
        }
        setValue(text);
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }

}
