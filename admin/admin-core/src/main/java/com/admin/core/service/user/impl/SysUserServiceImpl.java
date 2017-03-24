package com.admin.core.service.user.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.constant.LoginConstant;
import com.admin.api.model.user.SysUserModel;
import com.admin.api.vo.log.SysLoginLogVo;
import com.admin.api.vo.user.SysUserRoleVo;
import com.admin.api.vo.user.SysUserVo;
import com.admin.core.dao.user.ISysUserDao;
import com.admin.core.service.log.ISysLoginLogService;
import com.admin.core.service.user.ISysUserRoleService;
import com.admin.core.service.user.ISysUserService;
import com.admin.core.util.LoginUtil;
import com.alibaba.fastjson.JSON;
import com.common.base.constant.PrivilegeConstant;
import com.common.base.constant.RoleTypeConstant;
import com.common.base.model.user.BaseUserModel;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;
import com.common.util.CodeUtil;
import com.common.util.CommUtil;
import com.common.util.ExceptionUtil;
import com.common.util.IpUtil;
import com.common.util.Md5Util;
import com.common.util.PrimaryUtil;
import com.common.util.SessionUtil;
import com.common.util.WebUtil;

/**
 * 用户Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysUserServiceImpl extends BasePageHelperControllerServiceImpl<SysUserVo, ISysUserDao>
        implements ISysUserService {

    @Autowired
    private ISysUserDao sysUserDao;

    @Autowired
    private ISysLoginLogService sysLoginLogService;

    @Autowired
    private IMemcacheService memcacheService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 系统登录
     * 
     * @param sysUserModel
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysUserVo> login(SysUserVo sysUserModel, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultVo<SysUserVo> resultVo = new ResultVo<SysUserVo>();
        int res = RESULT_FAILURE;
        try {
            // 验证请求数据是否正确
            if (sysUserModel == null || StringUtils.isEmpty(sysUserModel.getUserName())
                    || StringUtils.isEmpty(sysUserModel.getPassword())) {

                res = RESULT_DATA_NULL;

            } else {
                // 登录IP
                String ip = IpUtil.getInstance().getIpAddr(request);

                // 查询是否用户已经存在
                SysUserVo model = new SysUserVo();
                model.setUserName(sysUserModel.getUserName());
                model = sysUserDao.getOne(model);
                if (model == null) {

                    logger.error("login:[failure][ip=" + ip + "]");
                    res = RESULT_NO_EXISTS;

                } else {

                    // 密码加密
                    String password = CommUtil.getInstance().getCommPassword(model.getUserId(),
                            sysUserModel.getPassword(), model.getRandomCode());
                    sysUserModel.setPassword(password);
                    model = sysUserDao.getOne(sysUserModel);

                    // 登录成功
                    if (model != null) {
                        logger.info("login:[success][ip=" + ip + "]");
                        res = RESULT_SUCCESS;// 成功
                        model.setSessionId(request.getSession().getId());
                        model.setPassword(null);
                        resultVo.setObj(model);

                        // 用户基本信息
                        BaseUserModel baseUserModel = new BaseUserModel();
                        BeanUtils.copyProperties(model, baseUserModel);
                        baseUserModel.setSessionId(model.getSessionId());

                        // 判断用户是否是管理员
                        boolean adminFlag = false;
                        SysUserRoleVo sysUserRoleVo = new SysUserRoleVo();
                        sysUserRoleVo.setUserId(baseUserModel.getUserId());
                        List<SysUserRoleVo> userRoleVos = sysUserRoleService.getAll(sysUserRoleVo);
                        for (SysUserRoleVo sysUserRoleVoTmp : userRoleVos) {
                            if (sysUserRoleVoTmp != null && sysUserRoleVoTmp.getSysRoleModel() != null
                                    && RoleTypeConstant.ADMIN.getKey()
                                            .equalsIgnoreCase(sysUserRoleVoTmp.getSysRoleModel().getRoleKey())) {
                                adminFlag = true;
                                break;
                            }
                        }
                        baseUserModel.setAdminFlag(adminFlag);

                        // 保存session
                        HttpSession session = WebUtil.getInstance().getSession(request);
                        session.setMaxInactiveInterval(PrivilegeConstant.TIMEOUT_SESSION);
                        request.getSession().setAttribute(PrivilegeConstant.SESSION_LOGIN_USER, model);

                        memcacheService.set(model.getSessionId(), baseUserModel, TIMEOUT_SESSION);
                        WebUtil.getInstance().addCookie(request, response,
                                Md5Util.getInstance().md5Encode(SESSION_LOGIN_USER_SESSION_ID), model.getSessionId(),
                                TIMEOUT_SESSION);

                        // 添加登录记录
                        SysLoginLogVo sysLoginLogVo = new SysLoginLogVo();
                        sysLoginLogVo.setIpAddress(ip);
                        sysLoginLogVo.setUserId(model.getUserId());
                        sysLoginLogVo.setOperationName(LoginConstant.LOGIN_OPERATION_NAME_IN);
                        sysLoginLogVo.setOperationType(LoginConstant.LOGIN_OPERATION_TYPE_IN);
                        sysLoginLogVo.setUserName(model.getUserName());
                        sysLoginLogVo.setCreateBy(model.getUserId());
                        sysLoginLogService.save(sysLoginLogVo, request);
                        logger.info("插入登录记录:" + sysLoginLogVo);
                    } else {
                        logger.info("login:[failure][ip=" + ip + "]");
                        res = RESULT_FAILURE;
                    }

                }

            }

            logger.info("login：[sysUserModel=" + JSON.toJSONString(sysUserModel) + "][res=" + res + "]");
        } catch (Exception e) {
            res = RESULT_EXCEPTION;
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        resultVo.setRes(res);
        return resultVo;
    }

    /**
     * 保存数据
     * 
     * @param obj
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysUserVo> save(SysUserVo sysUserModel, HttpServletRequest request) throws Exception {
        ResultVo<SysUserVo> resultVo = new ResultVo<SysUserVo>();
        int res = RESULT_FAILURE;
        try {
            // 验证请求数据是否正确
            if (sysUserModel == null || StringUtils.isEmpty(sysUserModel.getUserName())
                    || StringUtils.isEmpty(sysUserModel.getPassword())) {

                res = RESULT_DATA_NULL;

            } else {

                String randomCode = CodeUtil.getInstance().getSecurityCode(10);
                sysUserModel.setRandomCode(randomCode);
                sysUserModel.setId(PrimaryUtil.getInstance().getPrimaryValue());

                sysUserModel.setValidFlag(true);

                // 密码加密
                String password = CommUtil.getInstance().getCommPassword(sysUserModel.getId(),
                        sysUserModel.getPassword(), sysUserModel.getRandomCode());
                sysUserModel.setPassword(password);
                resultVo = super.save(sysUserModel, request);
                res = resultVo.getRes();

            }

            logger.info("save：[sysUserModel=" + JSON.toJSONString(sysUserModel) + "][res=" + res + "]");
        } catch (Exception e) {
            res = RESULT_EXCEPTION;
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        resultVo.setRes(res);
        return resultVo;
    }

    /**
     * 更新数据
     * 
     * @param obj
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysUserVo> update(SysUserVo sysUserModel, HttpServletRequest request) throws Exception {
        ResultVo<SysUserVo> resultVo = new ResultVo<SysUserVo>();
        int res = RESULT_FAILURE;
        try {

            // 验证请求数据是否正确
            if (sysUserModel == null) {

                res = RESULT_DATA_NULL;

            } else {

                // 获取用户登录信息
                BaseUserModel baseUserModel = LoginUtil.getInstance().getLoginUser(memcacheService,
                        sysUserModel.getSessionId(), request);
                if (baseUserModel == null) {
                    res = RESULT_NO_LOGIN;
                } else {

                    // 验证原密码是否正确
                    if (StringUtils.isNotEmpty(sysUserModel.getOldPassword())) {

                        SysUserVo model = super.getOneById(sysUserModel.getUserId());
                        if (model == null) {
                            res = RESULT_NO_EXISTS;
                        } else {
                            // 密码加密
                            String password = CommUtil.getInstance().getCommPassword(model.getId(),
                                    sysUserModel.getOldPassword(), model.getRandomCode());
                            model.setPassword(password);
                            model = sysUserDao.getOne(model);
                            if (model == null) {
                                res = RESULT_DATA_ERROR;
                                resultVo.setRes(res);
                                return resultVo;
                            }
                        }

                    }

                    // 修改密码
                    if (StringUtils.isNotEmpty(sysUserModel.getPassword())) {

                        SysUserModel model = super.getOneById(sysUserModel.getUserId());
                        if (model == null) {
                            res = RESULT_NO_EXISTS;
                        } else {
                            // 密码加密
                            String password = CommUtil.getInstance().getCommPassword(model.getId(),
                                    sysUserModel.getPassword(), model.getRandomCode());
                            sysUserModel.setPassword(password);
                        }

                    }

                    resultVo = super.update(sysUserModel, request);
                    res = resultVo.getRes();
                }

            }

            logger.info("[base][update][sysUserModel=" + JSON.toJSONString(sysUserModel) + "][res=" + res + "]");
        } catch (Exception e) {
            res = RESULT_EXCEPTION;
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        resultVo.setRes(res);
        return resultVo;
    }

    /**
     * 系统退出
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 登录IP
            String ip = IpUtil.getInstance().getIpAddr(request);

            // 获取登录session信息
            Object object = WebUtil.getInstance().getSession(request)
                    .getAttribute(PrivilegeConstant.SESSION_LOGIN_USER);
            if (object != null) {

                BaseUserModel baseUserModel = (BaseUserModel) object;

                // 添加退出记录
                SysLoginLogVo sysLoginLogVo = new SysLoginLogVo();
                sysLoginLogVo.setIpAddress(ip);
                sysLoginLogVo.setUserId(baseUserModel.getUserId());
                sysLoginLogVo.setOperationName(LoginConstant.LOGIN_OPERATION_NAME_OUT);
                sysLoginLogVo.setOperationType(LoginConstant.LOGIN_OPERATION_TYPE_OUT);
                sysLoginLogVo.setUserName(baseUserModel.getUserName());
                sysLoginLogService.save(sysLoginLogVo, request);
                logger.info("插入系统退出记录:" + sysLoginLogVo);

            } else {
                logger.error("[logout]session is null...");
            }

            // 退出系统
            // 清除Session
            // WebUtil.getInstance().getSession(request).removeAttribute(PrivilegeConstant.SESSION_LOGIN_USER);
            memcacheService.delete(SessionUtil.getInstance().getSessionId(request));
            WebUtil.getInstance().failureCookie(request, response,
                    Md5Util.getInstance().md5Encode(SESSION_LOGIN_USER_SESSION_ID));

            logger.info("系统退出，清除session信息!");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据部门ID查询用户列表
     * 
     * @param deptId
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysUserVo> deptList(String deptId, HttpServletRequest request) throws Exception {
        ResultVo<SysUserVo> resultVo = new ResultVo<>();
        int res = RESULT_FAILURE;
        try {

            if (StringUtils.isNotEmpty(deptId)) {

                SysUserVo sysUserVo = new SysUserVo();
                sysUserVo.setDeptId(deptId);

                // 查询信息
                List<SysUserVo> list = this.getAll(sysUserVo);
                res = RESULT_SUCCESS;
                resultVo.setList(list);
            }

            logger.info("[base][list][deptId=" + deptId + "][res=" + res + "]");
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
