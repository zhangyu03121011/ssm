package com.mornsun.jsw.core.api.search.suggest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.search.suggest.ISearchSuggestApi;
import com.mornsun.jsw.api.model.search.suggest.SearchSuggestModelCriteria;
import com.mornsun.jsw.api.vo.search.suggest.SearchSuggestVo;
import com.mornsun.jsw.core.service.search.suggest.ISearchSuggestService;

/**
 * 搜索记录反馈Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SearchSuggestApiImpl extends BasePageHelperProviderApiImpl<SearchSuggestVo, ISearchSuggestService>
        implements ISearchSuggestApi {

    @Autowired
    private ISearchSuggestService searchsuggestService;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(SearchSuggestModelCriteria example) throws Exception {
        try {
            return searchsuggestService.countByExample(example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件删除数据
     *
     * @param example
     * @return
     * @throws Exception
     */
    public int deleteByExample(SearchSuggestModelCriteria example) throws Exception {
        try {
            return searchsuggestService.deleteByExample(example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件插入数据
     *
     * @param record
     * @return
     * @throws Exception
     */
    public int insertSelective(SearchSuggestVo record) throws Exception {
        try {
            return searchsuggestService.insertSelective(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件查询数据
     *
     * @param example
     * @return
     * @throws Exception
     */
    public List<SearchSuggestVo> selectByExample(SearchSuggestModelCriteria example) throws Exception {
        try {
            return searchsuggestService.selectByExample(example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return
     * @throws Exception
     */
    public int updateByExampleSelective(SearchSuggestVo record, SearchSuggestModelCriteria example) throws Exception {
        try {
            return searchsuggestService.updateByExampleSelective(record, example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return
     * @throws Exception
     */
    public int updateByExample(SearchSuggestVo record, SearchSuggestModelCriteria example) throws Exception {
        try {
            return searchsuggestService.updateByExample(record, example);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    /**
     * 根据主键更新数据
     *
     * @param record
     * @return
     * @throws Exception
     */
    public int updateByPrimaryKey(SearchSuggestVo record) throws Exception {
        try {
            return searchsuggestService.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
