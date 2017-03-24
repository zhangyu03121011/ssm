package com.mornsun.crm.core.service.member;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperApiService;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

/**
 * 会员Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IMemberService extends IBasePageHelperApiService<UserVo> {

    /**
     * 更新会员内部员工数据
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<UserEmployeeVo> updateEmployeeInfo(UserEmployeeVo userEmployeeVo, HttpServletRequest request) throws Exception;
}
