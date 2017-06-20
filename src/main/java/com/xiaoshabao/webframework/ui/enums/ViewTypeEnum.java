package com.xiaoshabao.webframework.ui.enums;

public enum ViewTypeEnum {
	LIST(1,"列表",false),
	VIEW(2,"视图",true),
	VIEW_ADD(3,"视图-新增",false),
	VIEW_UPDATE(4,"视图-修改",false),
	VIEW_DETAIL(5,"视图-展现",true)
	;
	
	
	/** button frame中的位置（倒数） */
	private int index;
	/** 所在位置名称 */
	private String name;
	/***是否只读*/
	private boolean isReadOnly;
	
	private ViewTypeEnum(int index,String name,boolean isReadOnly){
		this.index=index;
		this.name=name;
		this.isReadOnly=isReadOnly;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}
}
