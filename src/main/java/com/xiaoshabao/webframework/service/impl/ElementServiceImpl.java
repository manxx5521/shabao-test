package com.xiaoshabao.webframework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframework.dao.ElementDao;
import com.xiaoshabao.webframework.service.ElementService;
@Service("elementServiceImpl")
public class ElementServiceImpl extends AbstractServiceImpl implements ElementService{
	@Autowired
	private ElementDao elementDao;
	
}
