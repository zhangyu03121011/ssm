package com.mornsun.jsw.core.dao.user.catalog;

import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;
import com.mornsun.jsw.api.model.user.catalog.UserCatalogModelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.common.orm.mybatis.dao.page.IBasePageHelperDao;

/**
 * 用户分类关系Dao
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IUserCatalogDao extends IBasePageHelperDao<UserCatalogVo> {
  /**
   * 根据条件查询总数
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int countByExample(UserCatalogModelCriteria example) throws Exception;

  /**
   * 根据条件删除数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int deleteByExample(UserCatalogModelCriteria example) throws Exception;

  /**
   * 根据条件插入数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int insertSelective(UserCatalogVo record) throws Exception;

  /**
   * 根据条件查询数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public List<UserCatalogVo> selectByExample(UserCatalogModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExampleSelective(@Param("record") UserCatalogVo record, @Param("example") UserCatalogModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExample(@Param("record") UserCatalogVo record, @Param("example") UserCatalogModelCriteria example) throws Exception;

  /**
   * 根据主键更新数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int updateByPrimaryKey(UserCatalogVo record) throws Exception;
}
