package com.mornsun.app.manager.controller.product.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.product.base.IProductService;
import com.mornsun.jsw.api.vo.product.base.ProductVo;

/**
 * 产品Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BasePageHelperApiServiceController<ProductVo, IProductService> {

    @Autowired
    private IProductService productService;

    /**
     * 查询校长审核
     * 
     * @param obj
     * @param page
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/approve/page")
    public ResultVo<ProductVo> approvePage(ProductVo obj, PageListVo<ProductVo> page, HttpServletRequest request) {
        ResultVo<ProductVo> resultVo = new ResultVo<>();
        try {
            resultVo = productService.approvePage(obj, page, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
        }
        return resultVo;
    }
    
    /**
     * 校长审核
     * 
     * @param obj
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/approve/update")
    public ResultVo<ProductVo> approveUpdate(ProductVo obj, HttpServletRequest request) {
        ResultVo<ProductVo> resultVo = new ResultVo<>();
        try {
            resultVo = productService.approveUpdate(obj, request);
        } catch (Exception e) {
            resultVo.setRes(RESULT_EXCEPTION);
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
        }
        return resultVo;
    }

}
