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
	
	
	
	
//---------------------------
	@Override
	public void setTagByParentId(Long parentId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagByParentId", FileTagEntity.class, params);
	}

	@Override
	public void setTagById(Long fileId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("tagId", tagId);
		this.baseDao.insert("insertTagById", FileTagEntity.class, params);

	}

	@Override
	public void deleteTagByParentId(Long parentId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tagId", tagId);
		this.baseDao.delete("deleteFileTagEntity", FileTagEntity.class, params);

	}

	@Override
	public void deleteTagById(Long fileId, String tagId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("tagId", tagId);
		this.baseDao.delete("deleteFileTagEntity", FileTagEntity.class, params);
	}

}
