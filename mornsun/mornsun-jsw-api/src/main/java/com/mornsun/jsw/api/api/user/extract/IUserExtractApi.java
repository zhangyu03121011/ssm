package com.mornsun.jsw.api.api.user.extract;

import com.common.api.page.IBasePageHelperReferenceApi;
import com.mornsun.jsw.api.vo.user.extract.UserExtractVo;

import com.mornsun.jsw.api.model.user.extract.UserExtractModelCriteria;
import java.util.List;

/**
 * 用户提现Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserExtractApi extends IBasePageHelperReferenceApi<UserExtractVo>  {

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int countByExample(UserExtractModelCriteria example) throws Exception;

    /**
    * 根据条件删除数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int deleteByExample(UserExtractModelCriteria example) throws Exception;

    /**
    * 根据条件插入数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int insertSelective(UserExtractVo record) throws Exception;

    /**
    * 根据条件查询数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public List<UserExtractVo> selectByExample(UserExtractModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExampleSelective(UserExtractVo record, UserExtractModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(UserExtractVo record, UserExtractModelCriteria example) throws Exception;

    /**
    * 根据主键更新数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int updateByPrimaryKey(UserExtractVo record) throws Exception;

}
