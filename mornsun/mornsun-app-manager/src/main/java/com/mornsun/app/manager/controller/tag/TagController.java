package com.mornsun.app.manager.controller.tag;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mornsun.app.core.service.tag.ITagService;
import com.mornsun.jsw.api.vo.tag.TagVo;

/**
 * 标签Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/tag")
public class TagController extends BasePageHelperApiServiceController<TagVo,ITagService> {

}
