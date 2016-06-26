package com.xiaoshabao.webframe.service;

import java.util.List;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.JSTreeNode;

public interface TreeService extends AbstractService{
	/**
	 * 获得JSTree的数据列表
	 */
	public List<JSTreeNode> getJSTreeList(Integer elementId);
	

}
