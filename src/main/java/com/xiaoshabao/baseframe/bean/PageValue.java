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
	private Integer totalrowcount;
	/**
	 * 总计页数
	 */
	private Integer pagecount;
	/**
	 * 每页大小
	 */
	private Integer pagesize;

	public List<T> getModels() {
		return models;
	}

	public void setModels(List<T> models) {
		this.models = models;
	}

	public Integer getTotalrowcount() {
		return totalrowcount;
	}

	public void setTotalrowcount(Integer totalrowcount) {
		this.totalrowcount = totalrowcount;
	}

	public Integer getPagecount() {
		return pagecount;
	}

	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

}
