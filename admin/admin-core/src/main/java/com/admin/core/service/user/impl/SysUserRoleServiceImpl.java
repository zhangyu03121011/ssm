package com.admin.core.service.user.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.admin.api.vo.user.SysUserRoleVo;
import com.admin.core.dao.user.ISysUserRoleDao;
import com.admin.core.service.user.ISysUserRoleService;
import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;
import com.common.util.ExceptionUtil;

/**
 * 用户角色Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysUserRoleServiceImpl extends BasePageHelperControllerServiceImpl<SysUserRoleVo, ISysUserRoleDao>
        implements ISysUserRoleService {

    /**
     * 批量更新数据
     * 
     * @param objList
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysUserRoleVo> batch(List<SysUserRoleVo> objList, HttpServletRequest request) throws Exception {
        ResultVo<SysUserRoleVo> resultVo = new ResultVo<SysUserRoleVo>();
        int res = RESULT_FAILURE;
        try {

            // 是否是删除，单独删除时，只删除选中的数据
            boolean deleteFlag = false;

            // 先删除数据
            if (CollectionUtils.isNotEmpty(objList)) {
                for (SysUserRoleVo sysUserRoleVo : objList) {

                    // 删除数据标识
                    deleteFlag = sysUserRoleVo.isDeleteFlag();
                    if (deleteFlag) {

                        // 只删除当前数据
                        int result = super.delete(sysUserRoleVo);
                        if (result > 0) {
                            res = RESULT_SUCCESS;
                        } else {
                            res = RESULT_FAILURE;
                        }

                    } else {
                        // 删除所有数据，重新插入
                        SysUserRoleVo userRoleVo = new SysUserRoleVo();
                        if (sysUserRoleVo.getSysRoleModel() != null) {
                            userRoleVo.setRoleId(sysUserRoleVo.getSysRoleModel().getRoleId());
                        } else {
                            userRoleVo.setUserId(sysUserRoleVo.getSysUserModel().getUserId());
                        }
                        super.delete(userRoleVo);
                        break;
                    }
                }
            }

            // 删除数据时只删除数据，不插入。保存数据时，先删除数据，再插入数据
            if (!deleteFlag) {
                // 批量插入数据
                resultVo = super.batch(objList, request);
                res = resultVo.getRes();
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
