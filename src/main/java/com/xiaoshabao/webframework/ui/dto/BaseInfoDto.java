package com.xiaoshabao.webframework.ui.dto;

import java.util.Map;

public class BaseInfoDto {

	private String pkCode;

	private String baseCode;

	private String baseName;

	private String parentCode;

	private Boolean isUsed;

	private Integer orderNo;
	
	/**扩展参数*/
	private Map<String,String> ext;

	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getPkCode() {
		return pkCode;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}

	public Map<String, String> getExt() {
		return ext;
	}

	public void setExt(Map<String, String> ext) {
		this.ext = ext;
	}
	
}
