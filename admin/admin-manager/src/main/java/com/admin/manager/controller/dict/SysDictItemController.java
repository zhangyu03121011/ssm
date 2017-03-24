package com.admin.manager.controller.dict;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.dict.SysDictItemVo;
import com.admin.core.service.dict.ISysDictItemService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 数据字典明细Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/dict/item")
public class SysDictItemController extends BasePageHelperServiceController<SysDictItemVo, ISysDictItemService> {
    
}
