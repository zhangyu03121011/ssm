package com.mornsun.jsw.api.vo.product.param;

import com.mornsun.jsw.api.model.product.param.ProductParamModel;
import com.mornsun.jsw.api.vo.param.ParamVo;
import com.mornsun.jsw.api.vo.product.base.ProductCommonVo;

/**
 * 产品参数Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductParamVo extends ProductParamModel {

	private static final long serialVersionUID = 1L;

	private ParamVo paramVo;

	/**
	 * 元器件部件号
	 */
	private String productNo;

	/**
	 * 产品分类
	 */
	private String catalogId;

	/**
	 * 品牌ID
	 */
	private String brandId;

	private String paramJson;

	private ProductCommonVo productCommonVo;

	public ParamVo getParamVo() {
		return paramVo;
	}

	public void setParamVo(ParamVo paramVo) {
		this.paramVo = paramVo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getParamJson() {
		return paramJson;
	}

	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}

	public ProductCommonVo getProductCommonVo() {
		return productCommonVo;
	}

	public void setProductCommonVo(ProductCommonVo productCommonVo) {
		this.productCommonVo = productCommonVo;
	}

}
