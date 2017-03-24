package com.mornsun.app.core.service.param.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.param.IParamApi;
import com.mornsun.app.core.service.param.IParamService;
import com.mornsun.jsw.api.vo.param.ParamVo;

/**
 * 参数Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ParamServiceImpl extends BasePageHelperApiServiceImpl<ParamVo, IParamApi> implements IParamService {

}
