package com.mornsun.app.core.service.product.base;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperApiService;
import com.mornsun.jsw.api.vo.product.base.ProductVo;

/**
 * 产品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IProductService extends IBasePageHelperApiService<ProductVo> {
    
    /**
     * 查询校长审核
     * 
     * @param obj
     * @param page
     * @param request
     * @return
     */
    public ResultVo<ProductVo> approvePage(ProductVo obj, PageListVo<ProductVo> pageListVo, HttpServletRequest request) throws Exception;
    
    /**
     * 校长审核
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<ProductVo> approveUpdate(ProductVo obj, HttpServletRequest request) throws Exception;
    
}
