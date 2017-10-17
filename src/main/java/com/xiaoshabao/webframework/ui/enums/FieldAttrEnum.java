package com.xiaoshabao.webframework.ui.enums;

/**
 * 属性
 */
public enum FieldAttrEnum {
	
	BASE_CODE(1,"基础代码"),
	BASE_NAME(2,"基础名称"),
	PARENT_CODE(3,"父级"),
	ORDER_NO(4,"排序"),
	IS_USED(5,"是否使用"),
	DEPART_TAG(6,"部门标识");
	
	
	/** 类型 */
	private int type;
	/** 所在位置名称 */
	private String name;
	
	private FieldAttrEnum(int type,String name){
		this.type=type;
		this.name=name;
	}
	
	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
