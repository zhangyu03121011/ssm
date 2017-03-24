package com.mornsun.jsw.api.api.user.employee;

import com.common.api.page.IBasePageHelperReferenceApi;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

import com.mornsun.jsw.api.model.user.employee.UserEmployeeModelCriteria;
import java.util.List;

/**
 * 用户内部员工Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserEmployeeApi extends IBasePageHelperReferenceApi<UserEmployeeVo>  {

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int countByExample(UserEmployeeModelCriteria example) throws Exception;

    /**
    * 根据条件删除数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int deleteByExample(UserEmployeeModelCriteria example) throws Exception;

    /**
    * 根据条件插入数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int insertSelective(UserEmployeeVo record) throws Exception;

    /**
    * 根据条件查询数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public List<UserEmployeeVo> selectByExample(UserEmployeeModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExampleSelective(UserEmployeeVo record, UserEmployeeModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(UserEmployeeVo record, UserEmployeeModelCriteria example) throws Exception;

    /**
    * 根据主键更新数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int updateByPrimaryKey(UserEmployeeVo record) throws Exception;

}
