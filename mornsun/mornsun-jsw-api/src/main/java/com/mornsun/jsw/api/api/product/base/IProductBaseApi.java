package com.mornsun.jsw.api.api.product.base;

import com.common.api.page.IBasePageHelperReferenceApi;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;

import com.mornsun.jsw.api.model.product.base.ProductBaseModelCriteria;
import java.util.List;

/**
 * 产品基础Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IProductBaseApi extends IBasePageHelperReferenceApi<ProductBaseVo>  {

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
    public long countByExample(ProductBaseModelCriteria example) throws Exception;

    /**
    * 根据条件删除数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int deleteByExample(ProductBaseModelCriteria example) throws Exception;

    /**
    * 根据条件插入数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int insertSelective(ProductBaseVo record) throws Exception;

    /**
    * 根据条件查询数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public List<ProductBaseVo> selectByExample(ProductBaseModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExampleSelective(ProductBaseVo record, ProductBaseModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(ProductBaseVo record, ProductBaseModelCriteria example) throws Exception;

    /**
    * 根据主键更新数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int updateByPrimaryKey(ProductBaseVo record) throws Exception;

}
