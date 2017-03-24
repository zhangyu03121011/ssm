package com.common.search.solr.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.base.vo.base.PageListVo;
import com.common.search.solr.service.ISolrTemplateService;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;

/**
 * Solr Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
@Service
public class SolrTemplateServiceImpl<T> extends BaseLogger implements ISolrTemplateService<T> {

    @Autowired
    private SolrTemplate solrTemplate;

    public T getOne(SimpleQuery query) throws Exception {
        T obj = null;
        try {
            obj = solrTemplate.queryForObject(query, ReflectUtil.getInstance().getClassGenricType(this.getClass()));
            logger.info("[SolrTemplateServiceImpl][getOne]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    @Override
    public T getOneById(Serializable id) throws Exception {
        T obj = null;
        try {

            obj = solrTemplate.getById(id, ReflectUtil.getInstance().getClassGenricType(this.getClass()));
            logger.info("[SolrTemplateServiceImpl][getOneById]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    @Override
    public void saveOrUpdate(T entity) throws Exception {
        try {

            solrTemplate.saveBean(entity);
            solrTemplate.commit();
            logger.info("[SolrTemplateServiceImpl][saveOrUpdate]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    @Async
    public void batch(List<T> list) throws Exception {
        try {

            solrTemplate.saveBeans(list);
            solrTemplate.commit();
            logger.info("[SolrTemplateServiceImpl][batch]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void deleteById(Serializable id) throws Exception {
        try {

            solrTemplate.deleteById((String) id);
            solrTemplate.commit();
            logger.info("[SolrTemplateServiceImpl][deleteById]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void delete(SolrDataQuery query) throws Exception {
        try {

            solrTemplate.delete(query);
            solrTemplate.commit();
            logger.info("[SolrTemplateServiceImpl][delete]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void delete(List<String> ids) throws Exception {
        try {

            solrTemplate.deleteById(ids);
            solrTemplate.commit();
            logger.info("[SolrTemplateServiceImpl][delete]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public PageListVo<T> page(Query query, PageListVo<T> pageListVo) throws Exception {
        try {

            Class<T> entity = ReflectUtil.getInstance().getClassGenricType(this.getClass());

            ScoredPage<T> scoredPage = solrTemplate.queryForPage(query, entity);
            List<T> list = scoredPage.getContent();

            pageListVo.setRows(list);
            pageListVo.setTotal(scoredPage.getTotalElements());
            logger.info("[SolrTemplateServiceImpl][page]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return pageListVo;
    }

}
