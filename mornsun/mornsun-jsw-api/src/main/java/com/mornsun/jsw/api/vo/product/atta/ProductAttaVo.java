package com.mornsun.jsw.api.vo.product.atta;

import com.common.base.annotation.Validate;
import com.common.base.model.atta.BaseAttaModel;
import com.mornsun.jsw.api.model.product.atta.ProductAttaModel;
import com.mornsun.jsw.api.vo.product.base.ProductCommonVo;

/**
 * 产品附件Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductAttaVo extends ProductAttaModel {

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
	 * 品牌ID
	 */
	@Validate
	private String brandId;

	private String updateUserAlias;

	private BaseAttaModel baseAttaModel;

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

	public BaseAttaModel getBaseAttaModel() {
		return baseAttaModel;
	}

	public void setBaseAttaModel(BaseAttaModel baseAttaModel) {
		this.baseAttaModel = baseAttaModel;
	}

	public String getUpdateUserAlias() {
		return updateUserAlias;
	}

	public void setUpdateUserAlias(String updateUserAlias) {
		this.updateUserAlias = updateUserAlias;
	}

	public ProductCommonVo getProductCommonVo() {
		return productCommonVo;
	}

	public void setProductCommonVo(ProductCommonVo productCommonVo) {
		this.productCommonVo = productCommonVo;
	}
}
