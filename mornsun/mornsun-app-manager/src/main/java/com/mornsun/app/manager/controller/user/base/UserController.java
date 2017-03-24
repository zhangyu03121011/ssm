package com.mornsun.app.manager.controller.user.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.app.core.service.user.base.IUserService;
import com.mornsun.jsw.api.vo.user.base.UserVo;

@Controller
@RequestMapping("/user")
public class UserController extends BasePageHelperApiServiceController<UserVo, IUserService> {

    @Autowired
    private IUserService userService;

    /**
     * 用户注册
     * 
     * @param userVo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public ResultVo<UserVo> register(UserVo userVo, HttpServletRequest request) {
        ResultVo<UserVo> resultVo = new ResultVo<>();
        try {
            resultVo = userService.register(userVo, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 用户登录
     * 
     * @param mobile
     * @param password
     * @param sourceType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResultVo<UserVo> login(UserVo userVo, HttpServletRequest request) {
        ResultVo<UserVo> resultVo = new ResultVo<>();
        try {
            resultVo = userService.login(userVo, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 用户登录
     * 
     * @param mobile
     * @param code
     * @param sourceType
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login/code")
    public ResultVo<UserVo> loginCode(UserVo userVo, HttpServletRequest request) {
        ResultVo<UserVo> resultVo = new ResultVo<>();
        try {
            resultVo = userService.loginCode(userVo, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 授权登录
     * 
     * @param userVo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/auth")
    public ResultVo<UserVo> auth(UserVo userVo, HttpServletRequest request) {
        ResultVo<UserVo> resultVo = new ResultVo<>();
        try {
            resultVo = userService.auth(userVo, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 短信验证码发送
     * 
     * @param mobile
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/code")
    public ResultVo<UserVo> code(String mobile, HttpServletRequest request) {
        ResultVo<UserVo> resultVo = new ResultVo<>();
        try {
            resultVo = userService.code(mobile, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

    /**
     * 重置密码
     * 
     * @param userVo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/password")
    public ResultVo<UserVo> password(UserVo userVo, HttpServletRequest request) {
        ResultVo<UserVo> resultVo = new ResultVo<>();
        try {
            resultVo = userService.password(userVo, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            logger.error(e.getMessage(), e);
        }
        return resultVo;
    }

}
