package com.xiaoshabao.baseframe.bean;

import java.util.List;

/**
 * 分页数据bean
 */
public class PageValue<T> {

	/**
	 * 内容
	 */
	private List<T> models;
	/**
	 * 总计条数
	 */
	private Long totalRows;
	/**
	 * 总计页数
	 */
	private Integer pages;
	/**
	 * 每页大小
	 */
	private Integer pageSize;

	public List<T> getModels() {
		return models;
	}

	public void setModels(List<T> models) {
		this.models = models;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
