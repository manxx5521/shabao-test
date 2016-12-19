package com.xiaoshabao.webframework.ui.service.impl;

import javax.annotation.Resource;

import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.service.TemplateFactory;

public abstract class AbstractTemplateFactoryImpl extends AbstractTemplateServiceImpl implements TemplateFactory {
	
	@Resource(name="formEngineComponet")
	protected FormEngineComponet formEngineComponet;
  
}
