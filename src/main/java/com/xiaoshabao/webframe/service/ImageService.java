package com.xiaoshabao.webframe.service;

import java.util.List;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.ImageDto;
/**
 * 图片
 */
public interface ImageService extends AbstractService{
	/**
	 * 获得图片的数据列表
	 */
	public List<ImageDto> getList(Integer elementId);
	

}
