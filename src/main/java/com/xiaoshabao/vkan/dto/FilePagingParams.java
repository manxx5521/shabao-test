package com.xiaoshabao.vkan.dto;

import java.util.List;

import com.xiaoshabao.baseframework.bean.PagingParams;

public class FilePagingParams extends PagingParams{
	
	/** 经过解析的二层分组id **/
	private List<List<String>> tagIds;
	
	private Long parentId;
	
	/**
	 * 查询类型 1、当前 2、全部
	 */
	private Integer searchType;
	
	private Integer projectId;

	public List<List<String>> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<List<String>> tagIds) {
		this.tagIds = tagIds;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
}
