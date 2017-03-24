package com.mornsun.jsw.core.dao.user.order;

import com.mornsun.jsw.api.vo.user.order.UserOrderVo;
import com.mornsun.jsw.api.model.user.order.UserOrderModelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.common.orm.mybatis.dao.page.IBasePageHelperDao;

/**
 * 用户订单Dao
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserOrderDao extends IBasePageHelperDao<UserOrderVo> {
	/**
	 * 根据条件查询总数
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public long countByExample(UserOrderModelCriteria example) throws Exception;

	/**
	 * 根据条件删除数据
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int deleteByExample(UserOrderModelCriteria example) throws Exception;

	/**
	 * 根据条件插入数据
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int insertSelective(UserOrderVo record) throws Exception;

	/**
	 * 根据条件查询数据
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public List<UserOrderVo> selectByExample(UserOrderModelCriteria example) throws Exception;

	/**
	 * 根据条件更新数据
	 * 
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExampleSelective(@Param("record") UserOrderVo record,
			@Param("example") UserOrderModelCriteria example) throws Exception;

	/**
	 * 根据条件更新数据
	 * 
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExample(@Param("record") UserOrderVo record, @Param("example") UserOrderModelCriteria example)
			throws Exception;

	/**
	 * 根据主键更新数据
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int updateByPrimaryKey(UserOrderVo record) throws Exception;
}
