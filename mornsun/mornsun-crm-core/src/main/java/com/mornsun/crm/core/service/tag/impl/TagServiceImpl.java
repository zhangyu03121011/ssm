package com.mornsun.crm.core.service.tag.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.tag.ITagService;
import com.mornsun.jsw.api.api.tag.ITagApi;
import com.mornsun.jsw.api.vo.tag.TagVo;

/**
 * 标签Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class TagServiceImpl extends BasePageHelperApiServiceImpl<TagVo, ITagApi> implements ITagService {

}
