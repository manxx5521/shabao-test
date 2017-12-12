package com.xiaoshabao.vkan.dto;

import com.xiaoshabao.baseframework.bean.PagingParams;

public class FilePagingParams extends PagingParams{
	
	private String[] tagIds;
	
	private Integer parentId;

	public String[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(String[] tagIds) {
		this.tagIds = tagIds;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
