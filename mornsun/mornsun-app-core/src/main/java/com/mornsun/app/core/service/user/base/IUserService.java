package com.mornsun.app.core.service.user.base;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperApiService;
import com.mornsun.jsw.api.vo.user.base.UserVo;

public interface IUserService extends IBasePageHelperApiService<UserVo> {

    /**
     * 用户注册
     * 
     * @param userVo
     * @param request
     * @return
     */
    public ResultVo<UserVo> register(UserVo userVo, HttpServletRequest request) throws Exception;
    
    /**
     * 用户登录
     * 
     * @param userVo
     * @param request
     * @return
     */
    public ResultVo<UserVo> login(UserVo userVo, HttpServletRequest request)throws Exception;
    
    /**
     * 用户登录
     * 
     * @param userVo
     * @param request
     * @return
     */
    public ResultVo<UserVo> loginCode(UserVo userVo, HttpServletRequest request)throws Exception;
    
    /**
     * 授权登录
     * 
     * @param userVo
     * @param request
     * @return
     */
    public ResultVo<UserVo> auth(UserVo userVo, HttpServletRequest request) throws Exception;
    
    /**
     * 短信验证码发送
     * 
     * @param mobile
     * @param request
     * @return
     */
    public ResultVo<UserVo> code(String mobile, HttpServletRequest request) throws Exception;
    
    /**
     * 重置密码
     * 
     * @param userVo
     * @param request
     * @return
     */
    public ResultVo<UserVo> password(UserVo userVo, HttpServletRequest request) throws Exception;

}
