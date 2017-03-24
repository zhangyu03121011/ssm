package com.mornsun.jsw.core.dao.company.course;

import com.mornsun.jsw.api.model.company.course.CompanyCourseModelCriteria;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.common.orm.mybatis.dao.page.IBasePageHelperDao;

/**
 * 企业秒懂Dao
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ICompanyCourseDao extends IBasePageHelperDao<CompanyCourseVo> {
  /**
   * 根据条件查询总数
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int countByExample(CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据条件删除数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int deleteByExample(CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据条件插入数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int insertSelective(CompanyCourseVo record) throws Exception;

  /**
   * 根据条件查询数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public List<CompanyCourseVo> selectByExample(CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExampleSelective(@Param("record") CompanyCourseVo record, @Param("example") CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExample(@Param("record") CompanyCourseVo record, @Param("example") CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据主键更新数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int updateByPrimaryKey(CompanyCourseVo record) throws Exception;
}
