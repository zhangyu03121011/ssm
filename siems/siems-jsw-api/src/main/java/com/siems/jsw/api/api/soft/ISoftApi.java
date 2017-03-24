package com.siems.jsw.api.api.soft;

import com.common.api.page.IBasePageHelperReferenceApi;
import com.siems.jsw.api.vo.soft.SoftVo;

import com.siems.jsw.api.model.soft.SoftModelCriteria;
import java.util.List;

/**
 * 终端软件Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISoftApi extends IBasePageHelperReferenceApi<SoftVo>  {

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
    public long countByExample(SoftModelCriteria example) throws Exception;

    /**
    * 根据条件删除数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public int deleteByExample(SoftModelCriteria example) throws Exception;

    /**
    * 根据条件插入数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int insertSelective(SoftVo record) throws Exception;

    /**
    * 根据条件查询数据
    *
    * @param example
    * @return
    * @throws Exception
    */
    public List<SoftVo> selectByExample(SoftModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExampleSelective(SoftVo record, SoftModelCriteria example) throws Exception;

    /**
    * 根据条件更新数据
    *
    * @param record
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(SoftVo record, SoftModelCriteria example) throws Exception;

    /**
    * 根据主键更新数据
    *
    * @param record
    * @return
    * @throws Exception
    */
    public int updateByPrimaryKey(SoftVo record) throws Exception;

}
