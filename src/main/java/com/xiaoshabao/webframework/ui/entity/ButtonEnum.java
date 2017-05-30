package com.xiaoshabao.webframework.ui.entity;

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

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
