package com.xiaoshabao.baseframework.bean;

/**
 * 分页参数
 * <p>
 * 如果想要传入其他参数，需要继承次类
 * </p>
 */
public class PagingParams {
	/**
	 * 页面索引
	 */
	private int index=1;
	/**
	 * 每页大小
	 */
	private int size=10;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
