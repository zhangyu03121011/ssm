package com.mornsun.jsw.api.model.applyarea;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 应用领域Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ApplyAreaModel extends BaseModel  {

    /**
	 * 领域名称
	 */
	@Validate
	private String areaName;

	private static final long serialVersionUID = 1L;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

}
