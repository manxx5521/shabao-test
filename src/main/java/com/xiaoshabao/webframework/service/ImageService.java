package com.xiaoshabao.webframework.service;

import java.util.List;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.webframework.dto.ImageDto;
/**
 * 图片
 */
public interface ImageService extends AbstractService{
	/**
	 * 获得图片的数据列表
	 */
	public List<ImageDto> getList(String elementId);
	

}
