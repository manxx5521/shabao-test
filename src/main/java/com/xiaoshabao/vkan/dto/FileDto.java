package com.xiaoshabao.vkan.dto;

import java.util.List;

import com.xiaoshabao.vkan.entity.FileEntity;
import com.xiaoshabao.vkan.entity.TagEntity;

public class FileDto extends FileEntity{
	
	private List<TagEntity> tagList;

	public List<TagEntity> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagEntity> tagList) {
		this.tagList = tagList;
	}
	
}
