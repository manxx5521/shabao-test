package com.xiaoshabao.webframe.dto;
/**
 * JSTree状态信息
 */
public class JSTreeState {
	/**
	 * 是否打开 true展开
	 */
	private boolean opened;
	
	public JSTreeState() {
		
	}

	public JSTreeState(boolean opened) {
		this.opened = opened;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	
	

}
