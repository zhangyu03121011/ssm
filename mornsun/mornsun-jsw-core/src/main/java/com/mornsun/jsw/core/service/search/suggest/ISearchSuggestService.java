package com.mornsun.jsw.core.service.search.suggest;

import com.mornsun.jsw.api.vo.search.suggest.SearchSuggestVo;
import com.mornsun.jsw.api.model.search.suggest.SearchSuggestModelCriteria;
import java.util.List;

import com.common.orm.mybatis.service.page.IBasePageHelperService;

/**
 * 搜索记录反馈Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ISearchSuggestService extends IBasePageHelperService<SearchSuggestVo> {
  /**
   * 根据条件查询总数
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public long countByExample(SearchSuggestModelCriteria example) throws Exception;

  /**
   * 根据条件删除数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public int deleteByExample(SearchSuggestModelCriteria example) throws Exception;

  /**
   * 根据条件插入数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int insertSelective(SearchSuggestVo record) throws Exception;

  /**
   * 根据条件查询数据
   * 
   * @param example
   * @return
   * @throws Exception
   */
  public List<SearchSuggestVo> selectByExample(SearchSuggestModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExampleSelective(SearchSuggestVo record, SearchSuggestModelCriteria example) throws Exception;

  /**
   * 根据条件更新数据
   * 
   * @param record
   * @param example
   * @return
   * @throws Exception
   */
  public int updateByExample(SearchSuggestVo record, SearchSuggestModelCriteria example) throws Exception;

  /**
   * 根据主键更新数据
   * 
   * @param record
   * @return
   * @throws Exception
   */
  public int updateByPrimaryKey(SearchSuggestVo record) throws Exception;
}
