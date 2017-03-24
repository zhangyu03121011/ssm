package com.mornsun.jsw.api.vo.product.base;

import com.common.base.annotation.Validate;
import com.mornsun.jsw.api.model.product.base.ProductBaseModel;

/**
 * 产品基础Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductBaseVo extends ProductBaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 元器件部件号
	 */
	@Validate
	private String productNo;

	/**
	 * 产品分类
	 */
	@Validate
	private String catalogId;

	/**
	 * 产品标签
	 */
	@Validate
	private String tagId;

	/**
	 * 品牌ID
	 */
	@Validate
	private String brandId;

	/**
	 * 应用领域ID
	 */
	@Validate
	private String applyAreaId;

	private ProductCommonVo productCommonVo;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo == null ? null : productNo.trim();
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId == null ? null : catalogId.trim();
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId == null ? null : brandId.trim();
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getApplyAreaId() {
		return applyAreaId;
	}

	public void setApplyAreaId(String applyAreaId) {
		this.applyAreaId = applyAreaId;
	}

	public ProductCommonVo getProductCommonVo() {
		return productCommonVo;
	}

	public void setProductCommonVo(ProductCommonVo productCommonVo) {
		this.productCommonVo = productCommonVo;
	}

}
