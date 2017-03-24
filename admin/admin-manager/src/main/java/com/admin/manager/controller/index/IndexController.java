package com.admin.manager.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.app.SysAppVo;
import com.admin.core.service.app.ISysAppService;
import com.admin.core.util.LoginUtil;
import com.common.base.common.BaseLogger;
import com.common.base.constant.PrivilegeConstant;
import com.common.base.model.user.BaseUserModel;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.util.ProjectUtil;
import com.common.util.SessionUtil;
import com.common.util.WebUtil;

/**
 * 首页Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping()
public class IndexController extends BaseLogger{

    @Autowired
    private ISysAppService sysAppService;

    @Autowired
    private IMemcacheService memcacheService;

    /**
     * 首页
     * 
     * @param sysUserModel
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        try {

            // 获取登录信息
            BaseUserModel baseUserModel = LoginUtil.getInstance().getLoginUser(BaseUserModel.class, memcacheService,
                    SessionUtil.getInstance().getSessionId(request), request);
            if (baseUserModel != null) {
                SysAppVo sysAppVo = new SysAppVo();
                if (!baseUserModel.isAdminFlag()) {
                    sysAppVo.setUserId(baseUserModel.getUserId());
                }
                model.addAttribute("appList", sysAppService.getAll(sysAppVo));
            }

            model.addAttribute(PrivilegeConstant.SESSION_LOGIN_USER, baseUserModel);
            model.addAttribute("sessionId", SessionUtil.getInstance().getSessionId(request));
            model.addAttribute("basePath", WebUtil.getInstance().getBasePath(request));
            model.addAttribute("jsVersion", ProjectUtil.getInstance().getJsVersion(request));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "index";
    }

    /**
     * 欢迎页
     * 
     * @return
     */
    @RequestMapping("/home")
    public String homePage() {
        return "/home";
    }

    /**
     * 首页登录页
     * 
     * @return
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "/login";
    }

}
