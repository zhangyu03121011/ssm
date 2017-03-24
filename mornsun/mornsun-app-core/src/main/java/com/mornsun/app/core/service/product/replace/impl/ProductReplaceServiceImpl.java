package com.mornsun.app.core.service.product.replace.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.product.base.IProductBaseService;
import com.mornsun.app.core.service.product.base.IProductService;
import com.mornsun.app.core.service.product.replace.IProductReplaceService;
import com.mornsun.jsw.api.api.product.replace.IProductReplaceApi;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 产品替换Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductReplaceServiceImpl extends BasePageHelperApiServiceImpl<ProductReplaceVo, IProductReplaceApi>
        implements IProductReplaceService {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductBaseService productBaseService;

    @Autowired
    private IMemcacheService memcacheService;

    /**
     * 保存搜索信息
     * 
     * @param obj
     * @param type
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<ProductReplaceVo> save(ProductReplaceVo obj, HttpServletRequest request) throws Exception {
        ResultVo<ProductReplaceVo> resultVo = new ResultVo<ProductReplaceVo>();
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

                String replaceProductId = null;

                // 添加替换产品
                productVo = new ProductVo();
                productVo.setProductNo(obj.getReplaceProductNo());
                productVo = productService.getOne(productVo);
                if (productVo != null) {
                    replaceProductId = productVo.getId();
                    productVo = new ProductVo();
                    productVo.setId(replaceProductId);
                    productVo.setBrandId(obj.getReplaceBrandId());
                    productVo.setCatalogId(obj.getReplaceCatalogId());
                    productVo.setProductNo(obj.getReplaceProductNo());
                    ResultVo<ProductVo> resultVoTmp = productService.update(productVo, request);
                    res = resultVoTmp.getRes();
                } else {

                    productVo = new ProductVo();
                    productVo.setBrandId(obj.getReplaceBrandId());
                    productVo.setCatalogId(obj.getReplaceCatalogId());
                    productVo.setProductNo(obj.getReplaceProductNo());
                    productVo.setState(AppConstant.STATE_WAIT);
                    ResultVo<ProductVo> resultVoTmp = productService.save(productVo, request);
                    res = resultVoTmp.getRes();
                    if (res == RESULT_SUCCESS) {
                        replaceProductId = resultVoTmp.getObj().getId();
                    }

                }

                // 获取用户登录信息
                UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());

                // 保存产品基本信息
                ProductBaseVo productBaseVo = new ProductBaseVo();
                productBaseVo.setProductId(replaceProductId);
                productBaseVo = productBaseService.getOne(productBaseVo);
                if (productBaseVo != null) {
                    productBaseVo.setProductDesc(obj.getReplaceProductDesc());
                    ResultVo<ProductBaseVo> resultVoTmp = productBaseService.update(productBaseVo, request);
                    res = resultVoTmp.getRes();
                } else {
                    productBaseVo = new ProductBaseVo();
                    productBaseVo.setId(PrimaryUtil.getInstance().getPrimaryValue());
                    productBaseVo.setProductDesc(obj.getReplaceProductDesc());
                    productBaseVo.setProductId(replaceProductId);
                    productBaseVo.setState(AppConstant.STATE_WAIT);
                    productBaseVo.setCreateBy(userVo.getId());
                    int result = productBaseService.insert(productBaseVo);
                    if (result > 0) {
                        res = RESULT_SUCCESS;
                    } else {
                        res = RESULT_FAILURE;
                    }
                }

                // 保存替换产品关系
                if (res == RESULT_SUCCESS) {
                    // 先刪除再添加
                    ProductReplaceVo productReplaceVo = new ProductReplaceVo();
                    productReplaceVo.setProductId(productId);
                    productReplaceVo.setReplaceProductId(replaceProductId);
                    super.delete(productReplaceVo);

                    obj.setReplaceProductId(replaceProductId);
                    obj.setProductId(productId);
                    obj.setState(AppConstant.STATE_WAIT);
                    resultVo = super.save(obj, request);
                    res = resultVo.getRes();
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
