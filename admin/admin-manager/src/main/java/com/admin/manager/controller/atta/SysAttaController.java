package com.admin.manager.controller.atta;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.api.vo.atta.SysAttaVo;
import com.admin.core.service.atta.ISysAttaService;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 系统附件Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/atta")
public class SysAttaController extends BasePageHelperServiceController<SysAttaVo,ISysAttaService> {

    @Autowired
    private ISysAttaService sysAttaService;

    /**
     * 文件上传
     * 
     * @param sourceType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ResultVo<SysAttaVo> upload(String sourceId, String sourceType, HttpServletRequest request) {
        ResultVo<SysAttaVo> resultVo = new ResultVo<SysAttaVo>();
        try {
            resultVo = sysAttaService.upload(sourceId, sourceType, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }
}
