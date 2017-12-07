package com.xiaoshabao.vkan.service;

import java.util.List;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.VkanIndexDto;

public interface VkanService extends AbstractService{
	
	/**
	 * 获得主页数据
	 * @return
	 */
	public VkanIndexDto getIndexData(String[] tagIds,String search);
	
	/**
	 * 根据父级id查询数据
	 * @param parentId
	 * @return
	 */
	public List<FileDto> getFileDto(String parentId);
	
}
