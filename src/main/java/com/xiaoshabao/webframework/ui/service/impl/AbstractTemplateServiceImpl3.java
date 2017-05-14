package com.xiaoshabao.webframework.ui.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.dao.ElementDao;

public abstract class AbstractTemplateServiceImpl3 extends AbstractServiceImpl{
	
	
	@Resource(name="formEngineComponet")
	
	protected FormEngineComponet formEngineComponet;
	
	@Autowired
	protected ElementDao elementDao;

}
