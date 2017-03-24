package com.admin.core.service.app.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.vo.app.SysAppVo;
import com.admin.core.dao.app.ISysAppDao;
import com.admin.core.service.app.ISysAppService;
import com.admin.core.util.LoginUtil;
import com.alibaba.fastjson.JSON;
import com.common.base.model.user.BaseUserModel;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;
import com.common.util.ExceptionUtil;

/**
 * 系统应用Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysAppServiceImpl extends BasePageHelperControllerServiceImpl<SysAppVo, ISysAppDao>
        implements ISysAppService {

    @Autowired
    private IMemcacheService memcacheService;

    /**
     * 查询所有数据
     * 
     * @param obj
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ResultVo getAppMenuList(SysAppVo obj, HttpServletRequest request) throws Exception {
        ResultVo<Object> resultVo = new ResultVo<Object>();
        int res = RESULT_FAILURE;
        try {

            // 获取登录信息
            BaseUserModel baseUserModel = LoginUtil.getInstance().getLoginUser(BaseUserModel.class, memcacheService,
                    obj.getSessionId(), request);
            if (baseUserModel != null) {
                obj.setUserId(baseUserModel.getUserId());
                obj.setAdminFlag(baseUserModel.isAdminFlag());

                // 查询信息
                List<Object> list = this.getAllObject(obj);
                res = RESULT_SUCCESS;
                resultVo.setList(list);
            }

            logger.info("[app][getAppMenuList][" + obj.getClass().getSimpleName() + "=" + JSON.toJSONString(obj)
                    + "][res=" + res + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        resultVo.setRes(res);
        return resultVo;
    }

}
