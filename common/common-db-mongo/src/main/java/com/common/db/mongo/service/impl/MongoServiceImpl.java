package com.common.db.mongo.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.base.vo.base.PageListVo;
import com.common.db.mongo.service.IMongoService;
import com.common.util.ExceptionUtil;

/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2016年1月7日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
@Service
public class MongoServiceImpl<T> extends BaseLogger implements IMongoService<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insert(T entity) throws Exception {
        try {
            mongoTemplate.insert(entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public void update(T entity) throws Exception {
        try {
            mongoTemplate.save(entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public void updateById(Serializable id, String key, String value, Class<T> entity) throws Exception {
        try {
            // ReflectUtil.getInstance().getClassGenricType(this.getClass())
            mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)),
                    new Update().set("updateTime", Calendar.getInstance().getTime()).set(key, value), entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public void update(Query query, String key, String value, Class<T> entity) throws Exception {
        try {
            mongoTemplate.updateFirst(query,
                    new Update().set("updateTime", Calendar.getInstance().getTime()).set(key, value), entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public void update(Query query, Update update, Class<T> entity) throws Exception {
        try {
            mongoTemplate.updateFirst(query, update, entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public void deleteById(Serializable id, Class<T> entity) throws Exception {
        try {
            mongoTemplate.remove(new Query(Criteria.where("id").is(id)), entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public void deleteByQuery(Query query, Class<T> entity) throws Exception {
        try {
            mongoTemplate.remove(query, entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public T getOneById(Serializable id, Class<T> entity) throws Exception {
        T obj = null;
        try {
            obj = mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    public T getOne(Query query, Class<T> entity) throws Exception {
        T obj = null;
        try {
            obj = mongoTemplate.findOne(query, entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    public List<T> getAll(Class<T> entity) throws Exception {
        List<T> list = null;
        try {
            list = mongoTemplate.findAll(entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return list;
    }

    public List<T> getAll(Query query, Class<T> entity) throws Exception {
        List<T> list = null;
        try {
            list = mongoTemplate.find(query, entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return list;
    }

    public long getCount(Query query, Class<T> entity) throws Exception {
        long count = 0;
        try {
            count = mongoTemplate.count(query, entity);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return count;
    }

    public PageListVo<T> getPage(Query query, PageListVo<T> page, Class<T> entity) throws Exception {
        PageListVo<T> pageListVo = null;
        try {

            Long total = this.getCount(query, entity);
            page.setTotal(total);
            int currPage = page.getCurrPage();
            int pageSize = page.getPageSize();
            query.skip((currPage - 1) * pageSize).limit(pageSize);
            List<T> pageList = this.mongoTemplate.find(query, entity);

            pageListVo = new PageListVo<T>(total, pageList);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return pageListVo;
    }

}
