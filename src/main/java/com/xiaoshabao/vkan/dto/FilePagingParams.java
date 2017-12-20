package com.xiaoshabao.vkan.dto;

import com.xiaoshabao.baseframework.bean.PagingParams;

public class FilePagingParams extends PagingParams{
	
	private Integer[] tagIds;
	
	private Long parentId;

	public Integer[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(Integer[] tagIds) {
		this.tagIds = tagIds;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
