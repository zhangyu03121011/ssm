package com.mornsun.app.core.service.product.atta.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.service.service.atta.IUploadService;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.api.constant.AttaTypeConstant;
import com.mornsun.app.core.service.atta.IAttaService;
import com.mornsun.app.core.service.product.atta.IProductAttaService;
import com.mornsun.app.core.service.product.base.IProductService;
import com.mornsun.jsw.api.api.product.atta.IProductAttaApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.product.atta.ProductAttaVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;

@Service
public class ProductAttaServiceImpl extends BasePageHelperApiServiceImpl<ProductAttaVo, IProductAttaApi>
        implements IProductAttaService {

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IAttaService attaService;

    /**
     * 保存产品附件信息
     * 
     * @param obj
     * @param type
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<ProductAttaVo> save(ProductAttaVo obj, HttpServletRequest request) throws Exception {
        ResultVo<ProductAttaVo> resultVo = new ResultVo<ProductAttaVo>();
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

                if (res == RESULT_SUCCESS) {
                    List<BaseAttaModel> attaModels = uploadService.upload(productId, AttaTypeConstant.PRODUCT.getType(),
                            AttaTypeConstant.PRODUCT.getName(), request);
                    if (CollectionUtils.isNotEmpty(attaModels)) {
                        for (BaseAttaModel baseAttaModel : attaModels) {

                            AttaVo attaVo = new AttaVo();
                            BeanUtils.copyProperties(baseAttaModel, attaVo);

                            // 保存附件
                            ResultVo<AttaVo> resultVoTmp = attaService.save(attaVo, request);
                            res = resultVoTmp.getRes();
                            if (res != RESULT_SUCCESS) {
                                break;
                            }

                            // 先删除后添加
                            ProductAttaVo productAttaVo = new ProductAttaVo();
                            productAttaVo.setAttaId(baseAttaModel.getId());
                            productAttaVo.setProductId(productId);
                            super.delete(productAttaVo);

                            obj.setAttaId(baseAttaModel.getId());
                            obj.setState(AppConstant.STATE_WAIT);
                            obj.setProductId(productId);
                            resultVo = super.save(obj, request);
                            res = resultVo.getRes();
                            if (res != RESULT_SUCCESS) {
                                break;
                            }
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
