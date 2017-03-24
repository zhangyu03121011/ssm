package com.admin.manager.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.api.vo.user.SysUserVo;
import com.admin.core.service.user.ISysUserService;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 用户Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BasePageHelperServiceController<SysUserVo, ISysUserService> {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 系统登录
     * 
     * @param sysUserModel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResultVo<SysUserVo> login(SysUserVo sysUserModel, HttpServletRequest request, HttpServletResponse response) {
        ResultVo<SysUserVo> resultVo = new ResultVo<SysUserVo>();
        try {
            resultVo = sysUserService.login(sysUserModel, request, response);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 根据部门ID查询用户列表
     * 
     * @param deptId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/dept")
    public ResultVo<SysUserVo> dept(String deptId, HttpServletRequest request) {
        ResultVo<SysUserVo> resultVo = new ResultVo<>();
        try {
            resultVo = sysUserService.deptList(deptId, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 用户退出
     * 
     * @param request
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            sysUserService.logout(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:../login";
    }

}
