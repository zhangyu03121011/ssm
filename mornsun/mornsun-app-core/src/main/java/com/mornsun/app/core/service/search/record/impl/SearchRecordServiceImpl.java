package com.mornsun.app.core.service.search.record.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.search.record.ISearchRecordService;
import com.mornsun.jsw.api.api.search.record.ISearchRecordApi;
import com.mornsun.jsw.api.vo.search.record.SearchRecordVo;

/**
 * 搜索记录Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SearchRecordServiceImpl extends BasePageHelperApiServiceImpl<SearchRecordVo, ISearchRecordApi>
        implements ISearchRecordService {

    /**
     * 查询列表
     * 
     * @param obj
     * @param request
     * @return
     */
    public ResultVo<SearchRecordVo> list(SearchRecordVo obj, PageListVo<SearchRecordVo> pageListVo,
            HttpServletRequest request) throws Exception {
        ResultVo<SearchRecordVo> resultVo = new ResultVo<SearchRecordVo>();
        int res = RESULT_FAILURE;
        try {

            // 查询信息
            obj.setFlag(true);
            resultVo = super.list(obj, pageListVo, request);
            res = resultVo.getRes();

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
