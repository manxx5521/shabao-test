package com.xiaoshabao.webframework.ui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframework.ui.dao.ElementDao;

public abstract class AbstractTemplateServiceImpl extends AbstractServiceImpl{
	
	@Autowired
	protected ElementDao elementDao;

}
