package com.siems.buyself.core.service.log.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.log.ILogApi;
import com.siems.buyself.core.service.log.ILogService;
import com.siems.jsw.api.vo.log.LogVo;

/**
 * 日志Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class LogServiceImpl extends BasePageHelperApiServiceImpl<LogVo,ILogApi>  implements ILogService {  

}
