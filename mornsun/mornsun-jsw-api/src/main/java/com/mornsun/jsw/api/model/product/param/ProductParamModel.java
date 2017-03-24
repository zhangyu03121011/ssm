package com.mornsun.jsw.api.model.product.param;

import com.common.base.model.base.BaseModel;

/**
 * 产品参数Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductParamModel extends BaseModel {

	/**
	 * 参数ID
	 */
	private String paramId;

	/**
	 * 参数值
	 */
	private String paramValue;

	/**
	 * 产品ID
	 */
	private String productId;

	private static final long serialVersionUID = 1L;

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId == null ? null : paramId.trim();
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue == null ? null : paramValue.trim();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

}
