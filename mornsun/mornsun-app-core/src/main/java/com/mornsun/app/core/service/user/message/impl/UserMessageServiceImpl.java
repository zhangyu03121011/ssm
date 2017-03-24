package com.mornsun.app.core.service.user.message.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.core.service.user.message.IUserMessageService;
import com.mornsun.jsw.api.api.user.message.IUserMessageApi;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.message.UserMessageVo;

/**
 * 用户消息Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserMessageServiceImpl extends BasePageHelperApiServiceImpl<UserMessageVo, IUserMessageApi>
        implements IUserMessageService {

    @Autowired
    private IMemcacheService memcacheService;

    /**
     * 查询列表
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<UserMessageVo> list(UserMessageVo obj, PageListVo<UserMessageVo> pageListVo,
            HttpServletRequest request) throws Exception {
        ResultVo<UserMessageVo> resultVo = new ResultVo<UserMessageVo>();
        int res = RESULT_FAILURE;
        try {
            // 获取登录信息
            UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());

            // 查询信息
            resultVo = super.list(obj, pageListVo, request);
            res = resultVo.getRes();
            if (res == RESULT_SUCCESS) {
                // 当前用户信息
                UserMessageVo userMessageVo = new UserMessageVo();
                userMessageVo.setUserVo(userVo);
                resultVo.setObj(userMessageVo);
            }

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
     */
    public ResultVo<UserMessageVo> save(UserMessageVo obj, HttpServletRequest request) throws Exception {
        ResultVo<UserMessageVo> resultVo = new ResultVo<UserMessageVo>();
        int res = RESULT_FAILURE;
        try {

            // 验证对象必填项
            String msg = ReflectUtil.getInstance().validObjField(obj);
            if (StringUtils.isNotEmpty(msg)) {

                res = RESULT_DATA_NULL;// 数据错误
                logger.error(msg);

            } else {

                // 获取登录信息
                UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
                // 当前用户id为来源用户
                obj.setSourceUserId(userVo.getId());
                resultVo = super.save(obj, request);
            }

            logger.info("[" + this.getClass().getSimpleName() + "][save][" + obj.getClass().getSimpleName() + "="
                    + JSON.toJSONString(obj) + "][res=" + res + "]");
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
