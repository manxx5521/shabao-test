package com.xiaoshabao.baseframe.bean;

/**
 * 分页参数
 * <p>
 * 如果想要传入其他参数，需要继承次类
 * </p>
 */
public class PagingPrams {
	/**
	 * 页面索引
	 */
	private int index;
	/**
	 * 每页大小
	 */
	private int size;
	
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
