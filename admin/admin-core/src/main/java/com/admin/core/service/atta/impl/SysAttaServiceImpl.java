package com.admin.core.service.atta.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.api.vo.atta.SysAttaVo;
import com.admin.core.dao.atta.ISysAttaDao;
import com.admin.core.service.atta.ISysAttaService;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;
import com.common.service.service.atta.IUploadService;
import com.common.util.ExceptionUtil;

/**
 * 系统附件Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysAttaServiceImpl extends BasePageHelperControllerServiceImpl<SysAttaVo, ISysAttaDao>
        implements ISysAttaService {

    @Autowired
    private IUploadService uploadService;

    /**
     * 文件上传
     * 
     * @param sourceType
     * @param request
     * @return
     * @throws Exception
     */
    public ResultVo<SysAttaVo> upload(String sourceId, String sourceType, HttpServletRequest request) throws Exception {
        ResultVo<SysAttaVo> resultVo = new ResultVo<SysAttaVo>();
        int res = RESULT_FAILURE;
        try {

            // 文件上传
            List<BaseAttaModel> baseAttaModels = uploadService.upload(sourceId, sourceType, sourceType, request);
            List<SysAttaVo> sysAttaModels = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(baseAttaModels)) {
                for (BaseAttaModel baseAttaModel : baseAttaModels) {
                    SysAttaVo sysAttaVo = new SysAttaVo();
                    BeanUtils.copyProperties(baseAttaModel, sysAttaVo);
                    this.save(sysAttaVo, request);
                    sysAttaModels.add(sysAttaVo);
                }
                resultVo.setList(sysAttaModels);
                res = RESULT_SUCCESS;
            }

            logger.info("[base][upload][sourceType=" + sourceType + "][res=" + res + "]");
        } catch (Exception e) {
            res = RESULT_EXCEPTION;
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        resultVo.setRes(res);
        return resultVo;
    }

}
