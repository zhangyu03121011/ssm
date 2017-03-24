package com.admin.core.service.dept.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.admin.api.vo.dept.SysDeptRoleVo;
import com.admin.core.dao.dept.ISysDeptRoleDao;
import com.admin.core.service.dept.ISysDeptRoleService;
import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;
import com.common.util.ExceptionUtil;

/**
 * 部门角色Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysDeptRoleServiceImpl extends BasePageHelperControllerServiceImpl<SysDeptRoleVo, ISysDeptRoleDao>
        implements ISysDeptRoleService {

    /**
     * 批量更新数据
     * 
     * @param objList
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysDeptRoleVo> batch(List<SysDeptRoleVo> objList, HttpServletRequest request) throws Exception {
        ResultVo<SysDeptRoleVo> resultVo = new ResultVo<SysDeptRoleVo>();
        int res = RESULT_FAILURE;
        try {

            // 是否是删除，单独删除时，只删除选中的数据
            boolean deleteFlag = false;

            // 先删除数据
            if (CollectionUtils.isNotEmpty(objList)) {
                for (SysDeptRoleVo sysDeptRoleVo : objList) {

                    // 删除数据标识
                    deleteFlag = sysDeptRoleVo.isDeleteFlag();
                    if (deleteFlag) {

                        // 只删除当前数据
                        int result = super.delete(sysDeptRoleVo);
                        if (result > 0) {
                            res = RESULT_SUCCESS;
                        } else {
                            res = RESULT_FAILURE;
                        }

                    } else {
                        // 删除所有数据，重新插入
                        SysDeptRoleVo deptRoleVo = new SysDeptRoleVo();
                        if (sysDeptRoleVo.getSysDeptModel() != null) {
                            deptRoleVo.setDeptId(sysDeptRoleVo.getSysDeptModel().getDeptId());
                        } else {
                            deptRoleVo.setRoleId(sysDeptRoleVo.getSysRoleModel().getRoleId());
                        }
                        super.delete(deptRoleVo);
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
