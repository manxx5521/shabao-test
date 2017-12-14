package com.xiaoshabao.vkan.dto;

import com.xiaoshabao.baseframework.bean.PagingParams;

public class FilePagingParams extends PagingParams{
	
	private String[] tagIds;
	
	private Long parentId;

	public String[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(String[] tagIds) {
		this.tagIds = tagIds;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
