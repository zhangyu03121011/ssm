package com.mornsun.jsw.core.service.user.coupon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.model.user.coupon.UserCouponModelCriteria;
import com.mornsun.jsw.api.vo.user.coupon.UserCouponVo;
import com.mornsun.jsw.core.dao.user.coupon.IUserCouponDao;
import com.mornsun.jsw.core.service.user.coupon.IUserCouponService;

/**
 * 用户代金劵Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserCouponServiceImpl extends BasePageHelperServiceImpl<UserCouponVo, IUserCouponDao>
        implements IUserCouponService {

    @Autowired
    private IUserCouponDao usercouponDao;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(UserCouponModelCriteria example) throws Exception {
        try {
            return usercouponDao.countByExample(example);
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
    public int deleteByExample(UserCouponModelCriteria example) throws Exception {
        try {
            return usercouponDao.deleteByExample(example);
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
    public int insertSelective(UserCouponVo record) throws Exception {
        try {
            return usercouponDao.insertSelective(record);
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
    public List<UserCouponVo> selectByExample(UserCouponModelCriteria example) throws Exception {
        try {
            return usercouponDao.selectByExample(example);
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
    public int updateByExampleSelective(UserCouponVo record, UserCouponModelCriteria example) throws Exception {
        try {
            return usercouponDao.updateByExampleSelective(record, example);
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
    public int updateByExample(UserCouponVo record, UserCouponModelCriteria example) throws Exception {
        try {
            return usercouponDao.updateByExample(record, example);
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
    public int updateByPrimaryKey(UserCouponVo record) throws Exception {
        try {
            return usercouponDao.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
