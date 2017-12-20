package com.xiaoshabao.vkan.service;

import java.util.List;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.vkan.dto.TagDto;
import com.xiaoshabao.vkan.entity.TagEntity;

public interface TagService extends AbstractService {

	/**
	 * 查询tag标签
	 * @param parentId
	 * @return
	 */
	List<TagEntity> getTagList(Integer parentId);
	
	/**
	 * 查询tag标签
	 * @param parentId
	 * @return
	 */
	List<TagEntity> getlabelTag(Long fileId);
	
	/**
	 * 查询tag标签
	 * @return
	 */
	List<TagDto> getTagListByFile(Long fileId);
	

	/**
	 * 保存标签
	 * @param ids
	 */
	void saveTag(Long fileId,Integer[] tagIds);
	
	void setTagByParentId(Long parentId, String tagId);

	void setTagById(Long fileId, String tagId);

	void deleteTagByParentId(Long parentId, String tagId);

	void deleteTagById(Long fileId, String tagId);

}
