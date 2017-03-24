package com.mornsun.jsw.api.api.company.course;

import com.common.api.page.IBasePageHelperReferenceApi;
import com.mornsun.jsw.api.model.company.course.CompanyCourseModelCriteria;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

import java.util.List;

/**
 * 企业秒懂Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ICompanyCourseApi extends IBasePageHelperReferenceApi<CompanyCourseVo>  {

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
