package com.mornsun.crm.manager.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;
import com.mornsun.crm.core.service.member.IMemberService;
import com.mornsun.crm.core.service.member.impl.MemberServiceImpl;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

/**
 * 会员Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BasePageHelperApiServiceController<UserVo, IMemberService> {
    
    @Autowired
    private IMemberService memberService;
    
    /**
     * 更新内部员工信息
     * @param obj
     * @param request
     * @return  
     * @throws
     */
    @ResponseBody
    @RequestMapping("/employe/update")
    public ResultVo<UserEmployeeVo> updateEmploye(UserEmployeeVo obj, HttpServletRequest request) {
        ResultVo<UserEmployeeVo> resultVo = new ResultVo<>();
        try {
            resultVo = memberService.updateEmployeeInfo(obj, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
        }
        return resultVo;
    }

}
