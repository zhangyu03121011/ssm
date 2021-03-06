package com.mornsun.jsw.core.service.user.suggest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.model.user.suggest.UserSuggestModelCriteria;
import com.mornsun.jsw.api.vo.user.suggest.UserSuggestVo;
import com.mornsun.jsw.core.dao.user.suggest.IUserSuggestDao;
import com.mornsun.jsw.core.service.user.suggest.IUserSuggestService;

/**
 * 用户意见Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserSuggestServiceImpl extends BasePageHelperServiceImpl<UserSuggestVo, IUserSuggestDao>
        implements IUserSuggestService {

    @Autowired
    private IUserSuggestDao usersuggestDao;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(UserSuggestModelCriteria example) throws Exception {
        try {
            return usersuggestDao.countByExample(example);
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
    public int deleteByExample(UserSuggestModelCriteria example) throws Exception {
        try {
            return usersuggestDao.deleteByExample(example);
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
    public int insertSelective(UserSuggestVo record) throws Exception {
        try {
            return usersuggestDao.insertSelective(record);
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
    public List<UserSuggestVo> selectByExample(UserSuggestModelCriteria example) throws Exception {
        try {
            return usersuggestDao.selectByExample(example);
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
    public int updateByExampleSelective(UserSuggestVo record, UserSuggestModelCriteria example) throws Exception {
        try {
            return usersuggestDao.updateByExampleSelective(record, example);
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
    public int updateByExample(UserSuggestVo record, UserSuggestModelCriteria example) throws Exception {
        try {
            return usersuggestDao.updateByExample(record, example);
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
    public int updateByPrimaryKey(UserSuggestVo record) throws Exception {
        try {
            return usersuggestDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
