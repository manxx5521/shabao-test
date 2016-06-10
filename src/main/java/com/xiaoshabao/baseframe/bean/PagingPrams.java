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
	private Integer pageindex;
	/**
	 * 每页大小
	 */
	private Integer pagesize;

	public Integer getPageindex() {
		return pageindex;
	}

	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

}
