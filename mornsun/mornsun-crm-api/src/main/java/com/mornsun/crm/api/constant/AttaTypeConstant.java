package com.mornsun.crm.api.constant;

/**
 * 处理信息类别常量
 * 
 * @author: HuiJunLuo
 * @date:2016年1月9日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public enum AttaTypeConstant {

	PRODUCT("1", "product"), HEAD("2", "head"), EXPERT("3", "expert"), COURSE("4", "course"), ANSWER("5",
			"answer"), COMPANY("6", "company"), COMPANY_COURSE("7", "company_course");

	/**
	 * 类别
	 */
	private String type;

	/**
	 * 名称
	 */
	private String name;

	// 构造方法
	private AttaTypeConstant(String type, String name) {
		this.type = type;
		this.name = name;
	}

	// 普通方法
	public static String getName(String type) {
		for (AttaTypeConstant fileTypeConstant : AttaTypeConstant.values()) {
			if (fileTypeConstant.getType().equals(type)) {
				return fileTypeConstant.getName();
			}
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
