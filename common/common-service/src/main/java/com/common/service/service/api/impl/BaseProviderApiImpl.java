package com.common.service.service.api.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.common.api.base.IBaseApi;
import com.common.base.common.BaseLogger;
import com.common.base.vo.base.PageListVo;
import com.common.service.service.base.IBaseService;
import com.common.util.ExceptionUtil;

/**
 * 通用Api接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法（用于JSW工程的实现）
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BaseProviderApiImpl<T, E extends IBaseService<T>> extends BaseLogger implements IBaseApi<T> {

    @Autowired
    private E baseService;

    @Override
    public int insert(T entity) throws Exception {
        int result = 0;
        try {
            result = baseService.insert(entity);
            logger.debug("[" + this.getClass().getSimpleName() + "][insert][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public int update(T entity) throws Exception {
        int result = 0;
        try {
            result = baseService.update(entity);
            logger.debug("[" + this.getClass().getSimpleName() + "][update][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public int deleteById(Serializable id) throws Exception {
        int result = 0;
        try {
            result = baseService.deleteById(id);
            logger.debug("[" + this.getClass().getSimpleName() + "][deleteById][id=" + JSON.toJSONString(id) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public int delete(T entity) throws Exception {
        int result = 0;
        try {
            result = baseService.delete(entity);
            logger.debug("[" + this.getClass().getSimpleName() + "][delete][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public T getOneById(Serializable id) throws Exception {
        T obj = null;
        try {
            obj = baseService.getOneById(id);
            logger.debug("[" + this.getClass().getSimpleName() + "][getOneById][id=" + JSON.toJSONString(id) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    @Override
    public T getOne(T entity) throws Exception {
        T obj = null;
        try {
            obj = baseService.getOne(entity);
            logger.debug("[" + this.getClass().getSimpleName() + "][getOne][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    @Override
    public Object getOneObject(Object obj) throws Exception {
        Object result = null;
        try {
            result = baseService.getOneObject(obj);
            logger.debug("[" + this.getClass().getSimpleName() + "][getObject][obj=" + JSON.toJSONString(obj) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public List<T> getAll(T entity) throws Exception {
        List<T> list = null;
        try {
            list = baseService.getAll(entity);
            logger.debug("[" + this.getClass().getSimpleName() + "][getAll][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return list;
    }

    @Override
    public List<Object> getAllObject(Object obj) throws Exception {
        List<Object> list = null;
        try {
            list = baseService.getAllObject(obj);
            logger.debug(
                    "[" + this.getClass().getSimpleName() + "][getObjectList][obj=" + JSON.toJSONString(obj) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return list;
    }

    @Override
    public long getCount(T entity) throws Exception {
        long result = 0;
        try {
            result = baseService.getCount(entity);
            logger.debug(
                    "[" + this.getClass().getSimpleName() + "][getCount][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public long getCountObject(Object obj) throws Exception {
    	long result = 0;
        try {
            result = baseService.getCountObject(obj);
            logger.debug("[" + this.getClass().getSimpleName() + "][getCount][entity=" + JSON.toJSONString(obj) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return result;
    }

    @Override
    public PageListVo<T> getPage(T entity, PageListVo<T> page) throws Exception {
        PageListVo<T> list = null;
        try {
            list = baseService.getPage(entity, page);
            logger.debug(
                    "[" + this.getClass().getSimpleName() + "][getPage][entity=" + JSON.toJSONString(entity) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return list;
    }

    @Override
    public PageListVo<Object> getPageObject(Object obj, PageListVo<Object> page) throws Exception {
        PageListVo<Object> list = null;
        try {
            list = baseService.getPageObject(obj, page);
            logger.debug(
                    "[" + this.getClass().getSimpleName() + "][getPageObject][entity=" + JSON.toJSONString(obj) + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return list;
    }

}
