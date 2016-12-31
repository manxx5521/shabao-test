package com.xiaoshabao.webframework.ui.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.dao.ElementDao;

public abstract class AbstractTemplateServiceImpl extends AbstractServiceImpl{
	
	@Autowired
	protected ElementDao elementDao;
	
	@Resource(name="formEngineComponet")
	protected FormEngineComponet formEngineComponet;

}
