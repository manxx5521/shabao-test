package com.xiaoshabao.baseframe.bean;

import java.util.List;

/**
 * 分页数据bean
 */
public class PageValue<T> {

	/**
	 * 内容
	 */
	private List<T> list;
	/**
	 * 总计条数
	 */
	private Long rows;
	/**
	 * 总计页数
	 */
	private int pages;
	/**
	 * 每页大小
	 */
	private int size;
	/**
	 * 页面索引
	 */
	private int index;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
