package com.xiaoshabao.webframework.ui.enums;

/**
 * 按钮视图
 */
public enum ButtonEnum {
	
	LIST(1,"列表"),
	VIEW(2,"视图");
	
	
	/** frame中的位置（倒数） */
	private int index;
	/** 所在位置名称 */
	private String name;
	
	private ButtonEnum(int index,String name){
		this.index=index;
		this.name=name;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

}
