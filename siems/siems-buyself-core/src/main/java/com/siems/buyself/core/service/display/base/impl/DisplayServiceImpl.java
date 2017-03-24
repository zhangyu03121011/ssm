package com.siems.buyself.core.service.display.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.display.base.IDisplayApi;
import com.siems.buyself.core.service.display.base.IDisplayService;
import com.siems.jsw.api.vo.display.base.DisplayVo;

/**
 * 陈列区域Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class DisplayServiceImpl extends BasePageHelperApiServiceImpl<DisplayVo,IDisplayApi>  implements IDisplayService {  

}
