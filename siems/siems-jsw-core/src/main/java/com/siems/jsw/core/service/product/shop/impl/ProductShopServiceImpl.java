package com.siems.jsw.core.service.product.shop.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.siems.jsw.core.service.product.shop.IProductShopService;
import com.siems.jsw.core.dao.product.shop.IProductShopDao;
import com.siems.jsw.api.vo.product.shop.ProductShopVo;

import com.siems.jsw.api.model.product.shop.ProductShopModelCriteria;
import java.util.List;

/**
 * 商品门店关系Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductShopServiceImpl extends BasePageHelperServiceImpl<ProductShopVo,IProductShopDao>  implements IProductShopService {  

  @Autowired
  private IProductShopDao productshopDao;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public long countByExample(ProductShopModelCriteria example) throws Exception{
    try {
        return productshopDao.countByExample(example);
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
  public int deleteByExample(ProductShopModelCriteria example) throws Exception{
    try {
        return productshopDao.deleteByExample(example);
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
  public int insertSelective(ProductShopVo record) throws Exception{
    try {
        return productshopDao.insertSelective(record);
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
  public List<ProductShopVo> selectByExample(ProductShopModelCriteria example) throws Exception{
    try {
        return productshopDao.selectByExample(example);
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
  public int updateByExampleSelective(ProductShopVo record, ProductShopModelCriteria example) throws Exception{
    try {
        return productshopDao.updateByExampleSelective(record,example);
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
  public int updateByExample(ProductShopVo record, ProductShopModelCriteria example) throws Exception{
    try {
        return productshopDao.updateByExample(record,example);
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
  public int updateByPrimaryKey(ProductShopVo record) throws Exception{
    try {
        return productshopDao.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
