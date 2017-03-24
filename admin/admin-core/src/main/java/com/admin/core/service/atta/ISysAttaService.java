package com.admin.core.service.atta;

import javax.servlet.http.HttpServletRequest;

import com.admin.api.vo.atta.SysAttaVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperControllerService;

/**
 * 系统附件Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISysAttaService extends IBasePageHelperControllerService<SysAttaVo> {

    /**
     * 文件上传
     * 
     * @param sourceType
     * @param request
     * @return
     */
    public ResultVo<SysAttaVo> upload(String sourceId, String sourceType, HttpServletRequest request) throws Exception;

}
