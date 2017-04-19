package com.xiaoshabao.webframework.ui.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;

public abstract class AbstractTemplateServiceImpl{
	
	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	public AbstractTemplateServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

}
