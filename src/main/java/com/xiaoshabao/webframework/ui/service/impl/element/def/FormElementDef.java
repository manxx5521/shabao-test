package com.xiaoshabao.webframework.ui.service.impl.element.def;
/**
 * 通用表单参数 
 */
public class FormElementDef {
	
	/** 当自己改变时，要通知刷新的列表[{selector:'#test2',type:'select'}] **/
	private String listeners;
	/** 当自己刷新时，要传入的参数 [{selector:'#test1',name:'depart_id',type:'select'}] **/
	private String filters;
	/** 初始化时可能存在的过滤值{eparchy_code:'0472',area_code:'B001'} **/
	private String filtervals;
	
	public String getListeners() {
		return listeners;
	}
	public void setListeners(String listeners) {
		this.listeners = listeners;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getFiltervals() {
		return filtervals;
	}
	public void setFiltervals(String filtervals) {
		this.filtervals = filtervals;
	}
}
