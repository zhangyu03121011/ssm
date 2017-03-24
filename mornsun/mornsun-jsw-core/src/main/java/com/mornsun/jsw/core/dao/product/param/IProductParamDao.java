package com.mornsun.jsw.core.dao.product.param;

import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.model.product.param.ProductParamModelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.common.orm.mybatis.dao.page.IBasePageHelperDao;

/**
 * 产品参数Dao
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IProductParamDao extends IBasePageHelperDao<ProductParamVo> {
	/**
	 * 根据条件查询总数
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public long countByExample(ProductParamModelCriteria example) throws Exception;

	/**
	 * 根据条件删除数据
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int deleteByExample(ProductParamModelCriteria example) throws Exception;

	/**
	 * 根据条件插入数据
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int insertSelective(ProductParamVo record) throws Exception;

	/**
	 * 根据条件查询数据
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public List<ProductParamVo> selectByExample(ProductParamModelCriteria example) throws Exception;

	/**
	 * 根据条件更新数据
	 * 
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExampleSelective(@Param("record") ProductParamVo record,
			@Param("example") ProductParamModelCriteria example) throws Exception;

	/**
	 * 根据条件更新数据
	 * 
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExample(@Param("record") ProductParamVo record,
			@Param("example") ProductParamModelCriteria example) throws Exception;

	/**
	 * 根据主键更新数据
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int updateByPrimaryKey(ProductParamVo record) throws Exception;
}
