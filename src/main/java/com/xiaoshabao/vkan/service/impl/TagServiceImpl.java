package com.xiaoshabao.vkan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.vkan.dto.TagDto;
import com.xiaoshabao.vkan.entity.FileTagEntity;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.service.TagService;

@Service("tagServiceImpl")
public class TagServiceImpl extends AbstractServiceImpl implements TagService {

	@Override
	public List<TagEntity> getTagList(Integer parentId) {
		TagEntity tagEntity = new TagEntity();
		tagEntity.setParentId(parentId);
		return this.baseDao.getData(TagEntity.class, tagEntity);
	}
	
	@Override
	public List<TagEntity> getlabelTag(Long fileId) {
		return this.baseDao.getData("getlabelTag", TagEntity.class, fileId);
	}
	
	@Override
	public List<TagDto> getTagListByFile(Long fileId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);		
		return this.baseDao.getData(TagDto.class, params);
	}
	
	@Override
	@Transactional
	public void saveTag(Long fileId,Integer[] tagIds) {
		
		FileTagEntity delReq=new FileTagEntity();
		delReq.setFileId(fileId);
		this.baseDao.delete(FileTagEntity.class, delReq);
		
		//重新插入标签
		if(tagIds!=null&&tagIds.length>0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fileId", fileId);
			params.put("tagIds", tagIds);
			this.baseDao.insert("insertTagByListId", FileTagEntity.class, params);
		}
	}
	/*
	 * 保存子项标签
	 * @param fileId
	 * @param tagIds
	 * @param type 1保存，2删除
	 */
	@Override
	@Transactional
	public void saveChildTag(Long fileId, Integer[] tagIds, Integer type) {
		
		for(Integer tagId:tagIds) {
			//插入子项
			if(type==1) {
				this.addTagByParentId(fileId, tagId);
			}else if(type==2) {
				this.deleteTagByParentId(fileId, tagId);
			}
		}
	}
	
	/**
	 * 根据父级id添加标签
	 * @param parentId
	 * @param tagId
	 */
	private void addTagByParentId(Long parentId, Integer tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagByParentId", FileTagEntity.class, params);
	}
	
	/**
	 * 根据父级id删除标签
	 * @param parentId
	 * @param tagId
	 */
	private void deleteTagByParentId(Long parentId, Integer tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.delete("deleteTagByParentId", FileTagEntity.class, params);
	}
	
}
