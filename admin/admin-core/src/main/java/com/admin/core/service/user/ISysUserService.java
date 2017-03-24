package com.admin.core.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.api.vo.user.SysUserVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperControllerService;

/**
 * 用户Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISysUserService extends IBasePageHelperControllerService<SysUserVo> {

    /**
     * 系统登录
     * 
     * @param sysUserVo
     * @param request
     * @return
     */
    public ResultVo<SysUserVo> login(SysUserVo sysUserVo, HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 系统退出
     * 
     * @param request
     * @return
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 根据部门ID查询用户列表
     * 
     * @param deptId
     * @param request
     * @return
     */
    public ResultVo<SysUserVo> deptList(String deptId, HttpServletRequest request) throws Exception;

}
