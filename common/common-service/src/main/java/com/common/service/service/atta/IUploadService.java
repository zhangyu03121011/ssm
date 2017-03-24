package com.common.service.service.atta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.common.base.constant.BaseConstant;
import com.common.base.model.atta.BaseAttaModel;

/**
 * 文件上传Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUploadService extends BaseConstant {

    /**
     * 文件上传
     * 
     * @param sourceType
     * @param request
     * @return
     * @throws Exception
     */
    public List<BaseAttaModel> upload(String sourceId, String sourceType, String sourceName, HttpServletRequest request)
            throws Exception;

}
