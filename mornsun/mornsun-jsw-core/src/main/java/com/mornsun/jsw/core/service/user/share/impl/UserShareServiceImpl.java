package com.mornsun.jsw.core.service.user.share.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.model.user.share.UserShareModelCriteria;
import com.mornsun.jsw.api.vo.user.share.UserShareVo;
import com.mornsun.jsw.core.dao.user.share.IUserShareDao;
import com.mornsun.jsw.core.service.user.share.IUserShareService;

/**
 * 用户分享Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserShareServiceImpl extends BasePageHelperServiceImpl<UserShareVo, IUserShareDao>
        implements IUserShareService {

    @Autowired
    private IUserShareDao usershareDao;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(UserShareModelCriteria example) throws Exception {
        try {
            return usershareDao.countByExample(example);
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
    public int deleteByExample(UserShareModelCriteria example) throws Exception {
        try {
            return usershareDao.deleteByExample(example);
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
    public int insertSelective(UserShareVo record) throws Exception {
        try {
            return usershareDao.insertSelective(record);
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
    public List<UserShareVo> selectByExample(UserShareModelCriteria example) throws Exception {
        try {
            return usershareDao.selectByExample(example);
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
    public int updateByExampleSelective(UserShareVo record, UserShareModelCriteria example) throws Exception {
        try {
            return usershareDao.updateByExampleSelective(record, example);
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
    public int updateByExample(UserShareVo record, UserShareModelCriteria example) throws Exception {
        try {
            return usershareDao.updateByExample(record, example);
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
    public int updateByPrimaryKey(UserShareVo record) throws Exception {
        try {
            return usershareDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
