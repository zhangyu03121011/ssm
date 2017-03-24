package com.common.db.mongo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.PageListVo;

/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IMongoService<T> extends BaseConstant {

    /**
     * 修改数据源
     * 
     * @param mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) throws Exception;

    /**
     * 保存数据
     * 
     * @param entity
     */
    public void insert(T entity) throws Exception;

    /**
     * 更新数据
     * 
     * @param entity
     */
    public void update(T entity) throws Exception;

    /**
     * 根据ID更新数据
     * 
     * @param id
     * @param key
     * @param value
     */
    public void updateById(Serializable id, String key, String value, Class<T> entity) throws Exception;

    /**
     * 更加条件更新数据
     * 
     * @param query
     * @param key
     * @param value
     */
    public void update(Query query, Update update, Class<T> entity) throws Exception;

    /**
     * 更加条件更新数据
     * 
     * @param query
     * @param key
     * @param value
     */
    public void update(Query query, String key, String value, Class<T> entity) throws Exception;

    /**
     * 根据ID删除数据
     * 
     * @param id
     */
    public void deleteById(Serializable id, Class<T> entity) throws Exception;

    /**
     * 根据条件删除数据
     * 
     * @param query
     */
    public void deleteByQuery(Query query, Class<T> entity) throws Exception;

    /**
     * 根据ID查询数据
     * 
     * @param id
     * @return
     */
    public T getOneById(Serializable id, Class<T> entity) throws Exception;

    /**
     * 根据query查询数据
     * 
     * @param id
     * @return
     */
    public T getOne(Query query, Class<T> entity) throws Exception;

    /**
     * 获取所有数据
     * 
     * @return
     */
    public List<T> getAll(Class<T> entity) throws Exception;

    /**
     * 获取所有数据
     * 
     * @return
     */
    public List<T> getAll(Query query, Class<T> entity) throws Exception;

    /**
     * 根据条件获取总数
     * 
     * @param query
     * @return
     */
    public long getCount(Query query, Class<T> entity) throws Exception;

    /**
     * 根据条件查询分页
     * 
     * @param query
     * @param page
     * @return
     */
    public PageListVo<T> getPage(Query query, PageListVo<T> page, Class<T> entity) throws Exception;

}
