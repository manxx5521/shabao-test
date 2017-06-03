package com.xiaoshabao.webframework.ui.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.entity.FormStaticEntity;
import com.xiaoshabao.webframework.ui.enums.FormStaticEnum;

public abstract class AbstractFormServiceImpl {

	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;
	
	@Resource(name = "formEngineComponet")
	protected FormEngineComponet formEngineComponet;

	public AbstractFormServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}
	
	/**
	 * 统一系统变量值获取（进行缓存）
	 * @param type
	 * @exception ServiceException 获得不到时异常
	 * @return 返回正确的取值
	 */
	public List<FormStaticEntity> getFormStatic(FormStaticEnum type){
		return getFormStatic(type.name());
	}
	/**
	 * 统一系统变量值获取（进行缓存）
	 * @param typeId
	 * @exception ServiceException 获得不到时异常
	 * @return 返回正确的取值
	 */
	public List<FormStaticEntity> getFormStatic(String typeId){
		List<FormStaticEntity> list=this.baseDao.getData(FormStaticEntity.class, typeId);
		if(list==null||list.size()<1){
			throw new ServiceException("获取系统静态值错误，无法根据"+typeId+"获得任何变量");
		}
		return list;
	}

}
