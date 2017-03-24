package com.mornsun.app.core.service.user.error.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.user.error.IUserErrorService;
import com.mornsun.jsw.api.api.user.error.IUserErrorApi;
import com.mornsun.jsw.api.vo.user.error.UserErrorVo;

/**
 * 用户纠错Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserErrorServiceImpl extends BasePageHelperApiServiceImpl<UserErrorVo, IUserErrorApi>
        implements IUserErrorService {

    /**
     * 保存分享信息
     * 
     * @param obj
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<UserErrorVo> save(UserErrorVo obj, HttpServletRequest request) throws Exception {
        ResultVo<UserErrorVo> resultVo = new ResultVo<UserErrorVo>();
        int res = RESULT_FAILURE;
        try {

            // 验证对象必填项
            String msg = ReflectUtil.getInstance().validObjField(obj);
            if (StringUtils.isNotEmpty(msg)) {

                res = RESULT_DATA_NULL;// 数据错误
                logger.error(msg);

            } else {

                boolean flag = true;

                // 其他错误类别
                if (StringUtils.isNotEmpty(obj.getDescr())) {
                    obj.setErrorType(AppConstant.ERROR_TYPE_OTHER);
                    resultVo = super.save(obj, request);
                    res = resultVo.getRes();
                    if (res != RESULT_SUCCESS) {
                        flag = false;
                    }
                }

                // 选择错误类别
                if (flag && StringUtils.isNotEmpty(obj.getErrorType())) {
                    String[] errorTypeArray = obj.getErrorType().split(",");
                    for (String errorType : errorTypeArray) {
                        obj.setErrorType(errorType);
                        obj.setDescr(null);
                        obj.setId(null);//自动生成主键
                        resultVo = super.save(obj, request);
                        res = resultVo.getRes();
                        if (res != RESULT_SUCCESS) {
                            break;
                        }
                    }
                }

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
