package com.admin.core.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.vo.role.SysRoleMenuFunctionVo;
import com.admin.api.vo.role.SysRoleMenuVo;
import com.admin.core.dao.role.ISysRoleMenuFunctionDao;
import com.admin.core.service.role.ISysRoleMenuFunctionService;
import com.admin.core.service.role.ISysRoleMenuService;
import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;

/**
 * 角色菜单功能Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysRoleMenuFunctionServiceImpl
        extends BasePageHelperControllerServiceImpl<SysRoleMenuFunctionVo, ISysRoleMenuFunctionDao>
        implements ISysRoleMenuFunctionService {

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 批量更新数据
     * 
     * @param objList
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysRoleMenuFunctionVo> batch(List<SysRoleMenuFunctionVo> objList, HttpServletRequest request)
            throws Exception {
        ResultVo<SysRoleMenuFunctionVo> resultVo = new ResultVo<SysRoleMenuFunctionVo>();
        int res = RESULT_FAILURE;
        try {

            // 先删除数据
            if (CollectionUtils.isNotEmpty(objList)) {

                // 排除重复菜单
                Map<String, String> map = new HashMap<>();

                // 删除数据
                for (SysRoleMenuFunctionVo sysRoleMenuFunctionVo : objList) {

                    // 删除所有数据，重新插入
                    super.delete(sysRoleMenuFunctionVo);

                    // 删除角色菜单关系数据
                    SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
                    sysRoleMenuVo.setRoleId(sysRoleMenuFunctionVo.getSysRoleMenuModel().getRoleId());
                    sysRoleMenuService.delete(sysRoleMenuVo);

                    res = RESULT_SUCCESS;

                    break;

                }

                // 角色菜单功能关系列表
                List<SysRoleMenuFunctionVo> sysRoleMenuFunctionVos = new ArrayList<>();

                // 角色菜单关系列表
                List<SysRoleMenuVo> sysRoleMenuVos = new ArrayList<>();

                // 组装批量插入数据
                for (SysRoleMenuFunctionVo sysRoleMenuFunctionVo : objList) {

                    if (sysRoleMenuFunctionVo.getSysRoleMenuModel() != null
                            && StringUtils.isNotEmpty(sysRoleMenuFunctionVo.getSysRoleMenuModel().getMenuId())) {

                        String roleMenuId = null;

                        // 判断是否存在重复，存在则跳过
                        if (MapUtils.isEmpty(map)
                                || !map.containsKey(sysRoleMenuFunctionVo.getSysRoleMenuModel().getMenuId())) {

                            roleMenuId = PrimaryUtil.getInstance().getPrimaryValue();

                            // 角色菜单关系
                            SysRoleMenuVo sysRoleMenuVo = new SysRoleMenuVo();
                            BeanUtils.copyProperties(sysRoleMenuFunctionVo.getSysRoleMenuModel(), sysRoleMenuVo);
                            sysRoleMenuVo.setRoleMenuId(roleMenuId);
                            sysRoleMenuVos.add(sysRoleMenuVo);

                            // 排除重复菜单
                            map.put(sysRoleMenuVo.getMenuId(), sysRoleMenuVo.getRoleMenuId());
                        } else {
                            roleMenuId = map.get(sysRoleMenuFunctionVo.getSysRoleMenuModel().getMenuId());
                        }

                        // 角色菜单功能关系
                        if (StringUtils.isNotEmpty(sysRoleMenuFunctionVo.getFunctionId())) {
                            sysRoleMenuFunctionVo.setRoleMenuId(roleMenuId);
                            sysRoleMenuFunctionVos.add(sysRoleMenuFunctionVo);
                        }
                    }

                }

                if (CollectionUtils.isNotEmpty(sysRoleMenuFunctionVos)) {
                    // 批量插入数据
                    resultVo = super.batch(sysRoleMenuFunctionVos, request);
                    res = resultVo.getRes();
                }

                if (CollectionUtils.isNotEmpty(sysRoleMenuVos)) {
                    // 批量插入数据
                    ResultVo<SysRoleMenuVo> resultTmpVo = sysRoleMenuService.batch(sysRoleMenuVos, request);
                    if (res == RESULT_SUCCESS) {
                        res = resultTmpVo.getRes();
                    }
                }

            }

            logger.info("[base][batch][objList=" + JSON.toJSONString(objList) + "][res=" + res + "]");
        } catch (Exception e) {
            res = RESULT_EXCEPTION;
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        resultVo.setRes(res);
        return resultVo;
    }

}
