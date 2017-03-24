package com.mornsun.app.core.service.product.base.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.product.applyarea.IProductApplyAreaService;
import com.mornsun.app.core.service.product.base.IProductBaseService;
import com.mornsun.app.core.service.product.base.IProductService;
import com.mornsun.app.core.service.product.tag.IProductTagService;
import com.mornsun.jsw.api.api.product.base.IProductBaseApi;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;

/**
 * 产品基础Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductBaseServiceImpl extends BasePageHelperApiServiceImpl<ProductBaseVo, IProductBaseApi>
        implements IProductBaseService {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductTagService productTagService;

    @Autowired
    private IProductApplyAreaService productApplyAreaService;

    /**
     * 保存数据
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<ProductBaseVo> save(ProductBaseVo obj, HttpServletRequest request) throws Exception {
        ResultVo<ProductBaseVo> resultVo = new ResultVo<ProductBaseVo>();
        int res = RESULT_FAILURE;
        try {

            // 验证对象必填项
            String msg = ReflectUtil.getInstance().validObjField(obj);
            if (StringUtils.isNotEmpty(msg)) {

                res = RESULT_DATA_NULL;// 数据错误
                logger.error(msg);

            } else {

                String productId = null;

                // 判断产品基本信息
                ProductVo productVo = new ProductVo();
                productVo.setProductNo(obj.getProductNo());
                productVo = productService.getOne(productVo);
                if (productVo != null) {
                    productId = productVo.getId();
                    productVo = new ProductVo();
                    productVo.setId(productId);
                    productVo.setBrandId(obj.getBrandId());
                    productVo.setCatalogId(obj.getCatalogId());
                    productVo.setProductNo(obj.getProductNo());
                    ResultVo<ProductVo> resultVoTmp = productService.update(productVo, request);
                    res = resultVoTmp.getRes();
                } else {

                    // 保存产品信息
                    productVo = new ProductVo();
                    productVo.setBrandId(obj.getBrandId());
                    productVo.setCatalogId(obj.getCatalogId());
                    productVo.setProductNo(obj.getProductNo());
                    productVo.setState(AppConstant.STATE_WAIT);
                    ResultVo<ProductVo> resultVoTmp = productService.save(productVo, request);
                    res = resultVoTmp.getRes();
                    if (res == RESULT_SUCCESS) {
                        productId = resultVoTmp.getObj().getId();
                    }

                }

                // 保存产品标签关系
                if (res == RESULT_SUCCESS) {

                    String tagIdArray[] = obj.getTagId().split(",");
                    for (String tagId : tagIdArray) {
                        if (StringUtils.isEmpty(tagId)) {
                            continue;
                        }
                        // 先删除标签关系
                        ProductTagVo productTagVo = new ProductTagVo();
                        productTagVo.setProductId(productId);
                        productTagVo.setTagId(tagId);
                        productTagService.delete(productTagVo);

                        // 添加产品标签关系
                        ResultVo<ProductTagVo> resultVoTmp = productTagService.save(productTagVo, request);
                        res = resultVoTmp.getRes();
                        if (res != RESULT_SUCCESS) {
                            break;
                        }
                    }

                }

                // 保存产品应用领域
                if (res == RESULT_SUCCESS) {

                    String applyAreaIdArray[] = obj.getApplyAreaId().split(",");
                    for (String applyAreaId : applyAreaIdArray) {
                        if (StringUtils.isEmpty(applyAreaId)) {
                            continue;
                        }

                        // 先删除产品应用领域
                        ProductApplyAreaVo productApplyAreaVo = new ProductApplyAreaVo();
                        productApplyAreaVo.setProductId(productId);
                        productApplyAreaVo.setApplyAreaId(applyAreaId);
                        productApplyAreaService.delete(productApplyAreaVo);

                        // 添加产品应用领域
                        ResultVo<ProductApplyAreaVo> resultVoTmp = productApplyAreaService.save(productApplyAreaVo,
                                request);
                        res = resultVoTmp.getRes();
                        if (res != RESULT_SUCCESS) {
                            break;
                        }
                    }

                }

                // 保存产品基本信息
                if (res == RESULT_SUCCESS) {
                    ProductBaseVo productBaseVo = new ProductBaseVo();
                    productBaseVo.setProductId(productId);
                    productBaseVo = super.getOne(productBaseVo);
                    if (productBaseVo != null) {
                        resultVo = super.update(obj, request);
                        res = resultVo.getRes();
                    } else {
                        obj.setProductId(productId);
                        obj.setState(AppConstant.STATE_WAIT);
                        resultVo = super.save(obj, request);
                        res = resultVo.getRes();
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
