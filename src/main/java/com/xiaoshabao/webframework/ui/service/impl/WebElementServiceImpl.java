package com.xiaoshabao.webframework.ui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframework.dao.ElementDao;
import com.xiaoshabao.webframework.ui.service.WebElementService;
@Service("elementServiceImpl")
public class WebElementServiceImpl extends AbstractServiceImpl implements WebElementService{
	@Autowired
	private ElementDao elementDao;
	
}
