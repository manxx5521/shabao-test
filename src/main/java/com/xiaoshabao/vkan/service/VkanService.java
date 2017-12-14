package com.xiaoshabao.vkan.service;

import java.util.List;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.dto.IndexDataVo;
import com.xiaoshabao.vkan.dto.VkanIndexDto;
import com.xiaoshabao.vkan.entity.TagEntity;

public interface VkanService extends AbstractService {

	/**
	 * 获得主页数据
	 * 
	 * @return
	 */
	VkanIndexDto getIndexData(IndexDataVo indexData);
	
	/**
	 * 查询tag标签
	 * @param parentId
	 * @return
	 */
	List<TagEntity> getTagList(Integer parentId);

	/**
	 * 查询文件数据
	 * @param parentId
	 * @return
	 */
	PageValue<FileDto> getFileDto(FilePagingParams params);

	void setTagByParentId(Long parentId, String tagId);

	void setTagById(Long fileId, String tagId);

	void deleteTagByParentId(Long parentId, String tagId);

	void deleteTagById(Long fileId, String tagId);

}
