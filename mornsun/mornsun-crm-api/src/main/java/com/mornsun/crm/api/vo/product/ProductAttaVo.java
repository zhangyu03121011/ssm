package com.mornsun.crm.api.vo.product;

/**
 * 产品附件信息VO
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductAttaVo {

	/**
	 * 产品信息名称
	 */
	private String productNoName;

	/**
	 * 附件名称
	 */
	private String attaName;

	public String getProductNoName() {
		return productNoName;
	}

	public void setProductNoName(String productNoName) {
		this.productNoName = productNoName;
	}

	public String getAttaName() {
		return attaName;
	}

	public void setAttaName(String attaName) {
		this.attaName = attaName;
	}

}
