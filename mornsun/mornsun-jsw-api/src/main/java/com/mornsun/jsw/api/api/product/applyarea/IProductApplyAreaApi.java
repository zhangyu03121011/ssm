package com.mornsun.jsw.api.api.product.applyarea;

import com.common.api.page.IBasePageHelperReferenceApi;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;

import com.mornsun.jsw.api.model.product.applyarea.ProductApplyAreaModelCriteria;
import java.util.List;

/**
 * 产品应用领域Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IProductApplyAreaApi extends IBasePageHelperReferenceApi<ProductApplyAreaVo>  {

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
    public long countByExample(ProductApplyAreaModelCriteria example) throws Exception;

    /**
    * 根据条件删除数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int deleteByExample(ProductApplyAreaModelCriteria example) throws Exception;

    /**
    * 根据条件插入数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int insertSelective(ProductApplyAreaVo record) throws Exception;

    /**
    * 根据条件查询数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public List<ProductApplyAreaVo> selectByExample(ProductApplyAreaModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExampleSelective(ProductApplyAreaVo record, ProductApplyAreaModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(ProductApplyAreaVo record, ProductApplyAreaModelCriteria example) throws Exception;

    /**
    * 根据主键更新数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int updateByPrimaryKey(ProductApplyAreaVo record) throws Exception;

}
