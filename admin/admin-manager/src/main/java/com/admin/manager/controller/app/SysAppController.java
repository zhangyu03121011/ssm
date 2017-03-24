package com.admin.manager.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.api.vo.app.SysAppVo;
import com.admin.core.service.app.ISysAppService;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 系统应用Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/app")
public class SysAppController extends BasePageHelperServiceController<SysAppVo, ISysAppService> {

    @Autowired
    private ISysAppService sysAppService;

    /**
     * 查询分页
     * 
     * @param obj
     * @param page
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu")
    public ResultVo<SysAppVo> menu(SysAppVo obj, HttpServletRequest request) {
        ResultVo<SysAppVo> resultVo = new ResultVo<>();
        try {
            resultVo = sysAppService.getAppMenuList(obj, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error("[controller][list]" + e.getMessage(), e);
        }
        return resultVo;
    }

}
