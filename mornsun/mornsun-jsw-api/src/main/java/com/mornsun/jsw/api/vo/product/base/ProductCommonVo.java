package com.mornsun.jsw.api.vo.product.base;

import java.io.Serializable;

/**
 * 产品通用信息Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductCommonVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 产品品牌名称
	 */
	private String brandName;

	/**
	 * 产品分类名称
	 */
	private String catalogName;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
}
