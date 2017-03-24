package com.mornsun.crm.core.service.member.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.mornsun.crm.core.service.member.IMemberService;
import com.mornsun.jsw.api.api.user.base.IUserApi;
import com.mornsun.jsw.api.api.user.employee.IUserEmployeeApi;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

/**
 * 会员Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class MemberServiceImpl extends BasePageHelperApiServiceImpl<UserVo, IUserApi> implements IMemberService {
    
    @Autowired
    private IUserEmployeeApi userEmployeeApi;
    
    /**
     * 更新会员内部员工数据
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<UserEmployeeVo> updateEmployeeInfo(UserEmployeeVo userEmployeeVo, HttpServletRequest request) throws Exception {
        ResultVo<UserEmployeeVo> resultVo = new ResultVo<UserEmployeeVo>();
        int res = RESULT_SUCCESS;
        try {
            // 更新数据(验证更新为内部员工时姓名编码不能为空)
            if(null != userEmployeeVo) {
                if (null != userEmployeeVo && (StringUtils.isEmpty(userEmployeeVo.getEmployeeName()) || StringUtils.isEmpty(userEmployeeVo.getEmployeeNo()))) {
                    res=RESULT_DATA_NULL;
                }else{
                    UserEmployeeVo oneById = userEmployeeApi.getOneById(userEmployeeVo.getId());
                    //如果已存在内部员工信息
                    if(null != oneById) {
                        userEmployeeApi.update(userEmployeeVo);
                    } else {
                        userEmployeeVo.setUserId(userEmployeeVo.getId());
                        userEmployeeVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
                        userEmployeeVo.setCreateBy("sys");
                        userEmployeeApi.insert(userEmployeeVo);
                    }
                }
            }
            logger.info("[" + this.getClass().getSimpleName() + "][update][" + userEmployeeVo.getClass().getSimpleName() + "="
                    + JSON.toJSONString(userEmployeeVo) + "][res=" + res + "]");
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
