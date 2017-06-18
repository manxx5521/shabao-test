package com.xiaoshabao.webframework.ui.enums;

/**
 * 属性
 */
public enum FieldAttrEnum {
	
	CODE(1,"代码"),
	VALUE(2,"名字值");
	
	
	/** ；类型 */
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
