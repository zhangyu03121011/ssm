package com.mornsun.jsw.core.api.product.applyarea.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.product.applyarea.IProductApplyAreaApi;
import com.mornsun.jsw.api.model.product.applyarea.ProductApplyAreaModelCriteria;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;
import com.mornsun.jsw.core.service.product.applyarea.IProductApplyAreaService;

/**
 * 产品应用领域Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductApplyAreaApiImpl extends BasePageHelperProviderApiImpl<ProductApplyAreaVo, IProductApplyAreaService>
        implements IProductApplyAreaApi {

    @Autowired
    private IProductApplyAreaService productapplyareaService;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(ProductApplyAreaModelCriteria example) throws Exception {
        try {
            return productapplyareaService.countByExample(example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件删除数据
     *
     * @param example
     * @return
     * @throws Exception
     */
    public int deleteByExample(ProductApplyAreaModelCriteria example) throws Exception {
        try {
            return productapplyareaService.deleteByExample(example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件插入数据
     *
     * @param record
     * @return
     * @throws Exception
     */
    public int insertSelective(ProductApplyAreaVo record) throws Exception {
        try {
            return productapplyareaService.insertSelective(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件查询数据
     *
     * @param example
     * @return
     * @throws Exception
     */
    public List<ProductApplyAreaVo> selectByExample(ProductApplyAreaModelCriteria example) throws Exception {
        try {
            return productapplyareaService.selectByExample(example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return
     * @throws Exception
     */
    public int updateByExampleSelective(ProductApplyAreaVo record, ProductApplyAreaModelCriteria example)
            throws Exception {
        try {
            return productapplyareaService.updateByExampleSelective(record, example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return
     * @throws Exception
     */
    public int updateByExample(ProductApplyAreaVo record, ProductApplyAreaModelCriteria example) throws Exception {
        try {
            return productapplyareaService.updateByExample(record, example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据主键更新数据
     *
     * @param record
     * @return
     * @throws Exception
     */
    public int updateByPrimaryKey(ProductApplyAreaVo record) throws Exception {
        try {
            return productapplyareaService.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
