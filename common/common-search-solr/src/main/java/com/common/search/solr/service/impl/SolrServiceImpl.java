package com.common.search.solr.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.base.vo.base.PageListVo;
import com.common.search.solr.service.ISolrService;
import com.common.util.ExceptionUtil;

/**
 * Solr Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
@Service
public class SolrServiceImpl<T> extends BaseLogger implements ISolrService<T> {

    @Autowired
    private SolrClient solrClient;

    public T getOneById(Serializable id, String collection, Class<T> entityClass) throws Exception {
        T obj = null;
        try {

            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery("id:" + id);
            List<T> list = solrClient.query(collection, solrQuery).getBeans(entityClass);
            if (CollectionUtils.isNotEmpty(list)) {
                obj = list.get(0);
            }
            logger.info("[SolrServiceImpl][collection=" + collection + "][getById]");

            return obj;
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public List<T> list(String query, String collection, Class<T> entityClass) throws Exception {
        List<T> list = null;
        try {

            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery(query);
            list = solrClient.query(collection, solrQuery).getBeans(entityClass);
            logger.info("[SolrServiceImpl][collection=" + collection + "][list]");

            return list;
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void saveOrUpdate(T entity, String collection) throws Exception {
        try {

            solrClient.addBean(collection, entity);
            solrClient.commit(collection);
            logger.info("[SolrServiceImpl][collection=" + collection + "][saveOrUpdate]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    @Async
    public void batch(List<T> list, String collection) throws Exception {
        try {

            solrClient.addBeans(collection, list);
            solrClient.commit(collection);
            logger.info("[SolrServiceImpl][collection=" + collection + "][batch]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void deleteById(Serializable id, String collection) throws Exception {
        try {

            solrClient.deleteById(collection, (String) id);
            solrClient.commit(collection);
            logger.info("[SolrServiceImpl][collection=" + collection + "][deleteById]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void delete(String query, String collection) throws Exception {
        try {

            solrClient.deleteByQuery(collection, query);
            solrClient.commit(collection);
            logger.info("[SolrServiceImpl][collection=" + collection + "][delete]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public void delete(List<String> ids, String collection) throws Exception {
        try {

            solrClient.deleteById(collection, ids);
            solrClient.commit(collection);
            logger.info("[SolrServiceImpl][collection=" + collection + "][delete]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    @Override
    public PageListVo<T> page(SolrQuery solrQuery, PageListVo<T> pageListVo, Class<T> className, String collection)
            throws Exception {
        try {

            // Class<T> entity =
            // ReflectUtil.getInstance().getClassGenricType(this.getClass());

            // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
            solrQuery.setStart((pageListVo.getCurrPage() - 1) * pageListVo.getPageSize());
            solrQuery.setRows(pageListVo.getPageSize());

            QueryResponse response = solrClient.query(collection, solrQuery);
            List<T> list = (List<T>) response.getBeans(className);

            pageListVo.setRows(list);
            pageListVo.setTotal(response.getResults().getNumFound());
            logger.info("[SolrServiceImpl][collection=" + collection + "][page]");

        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return pageListVo;
    }

}
