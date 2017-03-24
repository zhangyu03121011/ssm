package com.mornsun.jsw.core.service.product.applyarea.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.model.product.applyarea.ProductApplyAreaModelCriteria;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;
import com.mornsun.jsw.core.dao.product.applyarea.IProductApplyAreaDao;
import com.mornsun.jsw.core.service.product.applyarea.IProductApplyAreaService;

/**
 * 产品应用领域Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductApplyAreaServiceImpl extends BasePageHelperServiceImpl<ProductApplyAreaVo, IProductApplyAreaDao>
        implements IProductApplyAreaService {

    @Autowired
    private IProductApplyAreaDao productapplyareaDao;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(ProductApplyAreaModelCriteria example) throws Exception {
        try {
            return productapplyareaDao.countByExample(example);
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
            return productapplyareaDao.deleteByExample(example);
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
            return productapplyareaDao.insertSelective(record);
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
            return productapplyareaDao.selectByExample(example);
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
            return productapplyareaDao.updateByExampleSelective(record, example);
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
            return productapplyareaDao.updateByExample(record, example);
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
            return productapplyareaDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
