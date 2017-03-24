package com.admin.core.service.app;

import javax.servlet.http.HttpServletRequest;

import com.admin.api.vo.app.SysAppVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperControllerService;

/**
 * 系统应用Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISysAppService extends IBasePageHelperControllerService<SysAppVo> {

    /**
     * 查询所有数据
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<SysAppVo> getAppMenuList(SysAppVo obj, HttpServletRequest request) throws Exception;

}
