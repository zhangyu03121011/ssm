package com.mornsun.jsw.core.service.company.course;

import com.mornsun.jsw.api.model.company.course.CompanyCourseModelCriteria;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

import java.util.List;

import com.common.orm.mybatis.service.page.IBasePageHelperService;

/**
 * 企业秒懂Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ICompanyCourseService extends IBasePageHelperService<CompanyCourseVo> {
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
  public int updateByExampleSelective(CompanyCourseVo record, CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExample(CompanyCourseVo record, CompanyCourseModelCriteria example) throws Exception;

  /**
   * 根据主键更新数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int updateByPrimaryKey(CompanyCourseVo record) throws Exception;
}
