package com.siems.jsw.core.service.display.hareware;

import com.siems.jsw.api.vo.display.hareware.DisplayHarewareVo;
import com.siems.jsw.api.model.display.hareware.DisplayHarewareModelCriteria;
import java.util.List;

import com.common.orm.mybatis.service.page.IBasePageHelperService;

/**
 * 陈列区域硬件关系Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IDisplayHarewareService extends IBasePageHelperService<DisplayHarewareVo> {
  /**
   * 根据条件查询总数
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public long countByExample(DisplayHarewareModelCriteria example) throws Exception;

  /**
   * 根据条件删除数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int deleteByExample(DisplayHarewareModelCriteria example) throws Exception;

  /**
   * 根据条件插入数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int insertSelective(DisplayHarewareVo record) throws Exception;

  /**
   * 根据条件查询数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public List<DisplayHarewareVo> selectByExample(DisplayHarewareModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExampleSelective(DisplayHarewareVo record, DisplayHarewareModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExample(DisplayHarewareVo record, DisplayHarewareModelCriteria example) throws Exception;

  /**
   * 根据主键更新数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int updateByPrimaryKey(DisplayHarewareVo record) throws Exception;
}
