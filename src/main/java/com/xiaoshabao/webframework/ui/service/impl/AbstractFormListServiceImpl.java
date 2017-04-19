package com.xiaoshabao.webframework.ui.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;

/**
 * 表单列表界面抽象类
 */
public class AbstractFormListServiceImpl {
	
	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	public AbstractFormListServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}
	
	/**
	 * 获得模版引擎
	 * @param listEngine 列表引擎
	 * @return 引擎名字
	 */
	protected String getTemplateEngineType(String listEngine){
		return "simpleTemplateService";//先默认
	}
	

}
