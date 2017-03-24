package com.mornsun.jsw.core.api.user.question.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.user.question.IUserQuestionApi;
import com.mornsun.jsw.api.model.user.question.UserQuestionModelCriteria;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;
import com.mornsun.jsw.core.service.user.question.IUserQuestionService;

/**
 * 用户问题Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserQuestionApiImpl extends BasePageHelperProviderApiImpl<UserQuestionVo, IUserQuestionService>
        implements IUserQuestionApi {

    @Autowired
    private IUserQuestionService userquestionService;

    /**
     * 根据条件查询总数
     *
     * @param example
     * @return
     * @throws Exception
     */
    public long countByExample(UserQuestionModelCriteria example) throws Exception {
        try {
            return userquestionService.countByExample(example);
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
    public int deleteByExample(UserQuestionModelCriteria example) throws Exception {
        try {
            return userquestionService.deleteByExample(example);
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
    public int insertSelective(UserQuestionVo record) throws Exception {
        try {
            return userquestionService.insertSelective(record);
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
    public List<UserQuestionVo> selectByExample(UserQuestionModelCriteria example) throws Exception {
        try {
            return userquestionService.selectByExample(example);
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
    public int updateByExampleSelective(UserQuestionVo record, UserQuestionModelCriteria example) throws Exception {
        try {
            return userquestionService.updateByExampleSelective(record, example);
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
    public int updateByExample(UserQuestionVo record, UserQuestionModelCriteria example) throws Exception {
        try {
            return userquestionService.updateByExample(record, example);
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
    public int updateByPrimaryKey(UserQuestionVo record) throws Exception {
        try {
            return userquestionService.updateByPrimaryKey(record);
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

}
