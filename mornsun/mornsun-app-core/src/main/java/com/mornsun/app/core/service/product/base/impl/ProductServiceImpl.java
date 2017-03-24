package com.mornsun.app.core.service.product.base.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.product.base.IProductService;
import com.mornsun.app.core.service.user.error.IUserErrorService;
import com.mornsun.jsw.api.api.product.base.IProductApi;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.user.error.UserErrorVo;

/**
 * 产品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductServiceImpl extends BasePageHelperApiServiceImpl<ProductVo, IProductApi>
        implements IProductService {

	@Autowired
    private IUserErrorService userErrorService;

    /**
     * 查询校长审核
     * 
     * @param obj
     * @param page
     * @param request
     * @return
     */
    public ResultVo<ProductVo> approvePage(ProductVo obj, PageListVo<ProductVo> pageListVo, HttpServletRequest request)
            throws Exception {
        ResultVo<ProductVo> resultVo = new ResultVo<ProductVo>();
        int res = RESULT_FAILURE;
        try {

            // 查询信息
            obj.setState(AppConstant.STATE_WAIT);
            pageListVo = this.getPage(obj, pageListVo);
            res = RESULT_SUCCESS;
            resultVo.setObj(obj);
            resultVo.setPageListVo(pageListVo);

            logger.debug("[" + this.getClass().getSimpleName() + "][approvePage][" + obj.getClass().getSimpleName()
                    + "=" + JSON.toJSONString(obj) + "][res=" + res + "]");
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
     */
    public ResultVo<ProductVo> approveUpdate(ProductVo obj, HttpServletRequest request) throws Exception {
        ResultVo<ProductVo> resultVo = new ResultVo<ProductVo>();
        int res = RESULT_FAILURE;
        try {

            // 验证对象必填项
            if (StringUtils.isEmpty(obj.getId()) || StringUtils.isEmpty(obj.getState())) {

                res = RESULT_DATA_NULL;// 数据错误

            } else {

                // 修改产品审批状态
                ProductVo productVo = new ProductVo();
                productVo.setId(obj.getId());
                productVo.setState(obj.getState());
                resultVo = super.update(productVo, request);
                res = resultVo.getRes();
                if (res == RESULT_SUCCESS) {
                    // 审批不通过
                    if (AppConstant.STATUS_FAIL.equals(obj.getState())) {

                        // 保存审批失败记录
                        UserErrorVo userErrorVo = new UserErrorVo();
                        userErrorVo.setProductId(obj.getId());
                        userErrorVo.setSourceType(AppConstant.ERROR_TYPE_APPROVE);
                        userErrorVo.setErrorType(obj.getErrorType());
                        userErrorVo.setDescr(obj.getDescr());
                        ResultVo<UserErrorVo> tmpResultVo = userErrorService.save(userErrorVo, request);
                        res = tmpResultVo.getRes();
                    }
                }

            }

            logger.info("[" + this.getClass().getSimpleName() + "][approveUpdate][" + obj.getClass().getSimpleName()
                    + "=" + JSON.toJSONString(obj) + "][res=" + res + "]");
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
