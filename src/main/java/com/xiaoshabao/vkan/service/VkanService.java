package com.xiaoshabao.vkan.service;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.dto.IndexDataVo;
import com.xiaoshabao.vkan.dto.VkanIndexDto;

public interface VkanService extends AbstractService {

	/**
	 * 获得主页数据
	 * 
	 * @return
	 */
	VkanIndexDto getIndexData(IndexDataVo indexData);
	
	/**
	 * 查询文件数据
	 * @param parentId
	 * @return
	 */
	PageValue<FileDto> getFileDto(FilePagingParams params);

}
