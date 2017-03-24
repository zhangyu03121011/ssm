package com.mornsun.jsw.core.dao.coupon;

import com.mornsun.jsw.api.vo.coupon.CouponVo;
import com.mornsun.jsw.api.model.coupon.CouponModelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.common.orm.mybatis.dao.page.IBasePageHelperDao;

/**
 * 代金劵Dao
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ICouponDao extends IBasePageHelperDao<CouponVo> {
  /**
   * 根据条件查询总数
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public long countByExample(CouponModelCriteria example) throws Exception;

  /**
   * 根据条件删除数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int deleteByExample(CouponModelCriteria example) throws Exception;

  /**
   * 根据条件插入数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int insertSelective(CouponVo record) throws Exception;

  /**
   * 根据条件查询数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public List<CouponVo> selectByExample(CouponModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExampleSelective(@Param("record") CouponVo record, @Param("example") CouponModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExample(@Param("record") CouponVo record, @Param("example") CouponModelCriteria example) throws Exception;

  /**
   * 根据主键更新数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int updateByPrimaryKey(CouponVo record) throws Exception;
}
