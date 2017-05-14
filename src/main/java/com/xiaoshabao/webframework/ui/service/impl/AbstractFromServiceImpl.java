package com.xiaoshabao.webframework.ui.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;

public abstract class AbstractFromServiceImpl {

	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;
	
	@Resource(name = "formEngineComponet")
	protected FormEngineComponet formEngineComponet;

	public AbstractFromServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

}
