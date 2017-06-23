package com.xiaoshabao.webframework.ui.dto;

import java.io.Serializable;

/**
 * render DataTables要带的参数
 */
public class DisplayColumnBean implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 数据列 */
	private String column;
	/** 是否是链接 */
	private boolean href;
	
	public DisplayColumnBean() {
	}
	public DisplayColumnBean(String column, boolean href) {
		this.column = column;
		this.href = href;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public boolean isHref() {
		return href;
	}
	public void setHref(boolean href) {
		this.href = href;
	}
	
	
}
