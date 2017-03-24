package com.mornsun.jsw.api.vo.company.course;

import com.common.base.model.atta.BaseAttaModel;
import com.mornsun.jsw.api.model.company.course.CompanyCourseModel;
import com.mornsun.jsw.api.vo.company.base.CompanyVo;

/**
 * 企业秒懂Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CompanyCourseVo extends CompanyCourseModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 元器件部件号
	 */
	private String productNo;

	private CompanyVo companyVo;

	private BaseAttaModel baseAttaModel;

	private String isPraise;

	/**
	 * 当前登录用户
	 */
	private String currUserId;

	public String getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(String isPraise) {
		this.isPraise = isPraise;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public CompanyVo getCompanyVo() {
		return companyVo;
	}

	public void setCompanyVo(CompanyVo companyVo) {
		this.companyVo = companyVo;
	}

	public BaseAttaModel getBaseAttaModel() {
		return baseAttaModel;
	}

	public void setBaseAttaModel(BaseAttaModel baseAttaModel) {
		this.baseAttaModel = baseAttaModel;
	}

	public String getCurrUserId() {
		return currUserId;
	}

	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}

}
