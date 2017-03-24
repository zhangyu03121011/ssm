package com.admin.core.service.permission.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.vo.role.SysRoleMenuFunctionVo;
import com.admin.core.service.permission.ISysPermissionService;
import com.admin.core.service.role.ISysRoleMenuFunctionService;
import com.common.base.common.BaseLogger;
import com.common.base.model.user.BaseUserModel;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.util.ExceptionUtil;

/**
 * 系统权限控制Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysPermissionServiceImpl extends BaseLogger implements ISysPermissionService {

    @Autowired
    private ISysRoleMenuFunctionService sysRoleMenuFunctionService;

    @Autowired
    private IMemcacheService memcacheService;

    /**
     * 判断是否有权限
     * 
     * @throws Exception
     */
    @Override
    public boolean hasPermission(String sessionId, String systemSn, String moduleSn, String permission)
            throws Exception {
        boolean flag = false;
        try {

            if (StringUtils.isEmpty(sessionId)) {
                logger.info("[SysPermissionServiceImpl][hasPermission]sessionId is null...");
                return (flag == false);
            }

            // 获取用户信息
            Object obj = memcacheService.get(sessionId);
            BaseUserModel baseUserModel = null;
            if (obj == null) {
                logger.info("[SysPermissionServiceImpl][hasPermission]user session is null...");
                return (flag == false);
            } else {
                baseUserModel = (BaseUserModel) obj;
            }

            // 判断用户是否是管理员
            boolean adminFlag = baseUserModel.isAdminFlag();
            if (adminFlag) {
                logger.info("[SysPermissionServiceImpl][hasPermission]user is admin role...");
                return (flag == adminFlag);
            }

            // 获取用户功能信息
            SysRoleMenuFunctionVo sysRoleMenuFunctionVo = new SysRoleMenuFunctionVo();
            sysRoleMenuFunctionVo.setAppKey(systemSn);
            sysRoleMenuFunctionVo.setMenuKey(moduleSn);
            sysRoleMenuFunctionVo.setUserId(baseUserModel.getUserId());
            List<Object> sysRoleMenuFunctionVos = sysRoleMenuFunctionService.getAllObject(sysRoleMenuFunctionVo);
            for (Object object : sysRoleMenuFunctionVos) {
                SysRoleMenuFunctionVo vo = (SysRoleMenuFunctionVo) object;
                if (vo != null && vo.getSysFunctionModel() != null
                        && StringUtils.isNotEmpty(vo.getSysFunctionModel().getControlKey())
                        && StringUtils.isNotEmpty(permission)
                        && permission.indexOf(vo.getSysFunctionModel().getControlKey()) != -1) {
                    flag = true;
                }
            }

            logger.info("[SysPermissionServiceImpl][hasPermission][sessionId=" + sessionId + "][systemSn=" + systemSn
                    + "][moduleSn=" + moduleSn + "][permission=" + permission + "][flag=" + flag + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return flag;
    }

}
