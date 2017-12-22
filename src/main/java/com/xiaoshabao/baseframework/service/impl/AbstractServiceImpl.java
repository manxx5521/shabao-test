package com.xiaoshabao.baseframework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.bean.PagingParams;
import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.baseframework.service.AbstractService;

/**
 * 抽象Service类
 * <p>
 * 添加共性的东西<br/>
 * 要想使用这个需要在项目中实现一个BaseServiceImpl来添加项目个性的东西，<br/>
 * 即使不用也可实现一个空类，达到命名一致
 * </p>
 */
public abstract class AbstractServiceImpl implements AbstractService {
	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	public AbstractServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

	@Override
	public <T> int insert(Class<T> clasz, T t){
		return this.baseDao.insert(clasz, t);
	}

	@Override
	public <T> int delete(Class<T> clasz, T t) {
		return this.baseDao.delete(clasz, t);
	}

	@Override
	public <T> int update(Class<T> clasz, Object p) {
		return this.baseDao.update(clasz, p);
	}

	@Override
	public <T> boolean exists(Class<T> clasz, T t){
		return this.baseDao.exists(clasz, t);
	}

	@Override
	public <T> List<T> getData(Class<T> clasz, Object params) {
		return this.baseDao.getData(clasz, params);
	}

	@Override
	public <T> List<T> getData(String sqlid, Object param) {
		return this.baseDao.getData(sqlid, param);
	}

	// 通过这个方法把分页查询DAO实例
	@Override
	public <T, P extends PagingParams> PageValue<T> getDataPaging(Class<T> clasz, P pageParams) {
		PageValue<T> pageValue = new PageValue<T>();
		PageHelper.startPage(pageParams.getIndex(),pageParams.getSize());
		List<T> list = this.baseDao.getDataPaging(clasz, pageParams);
		pageValue.setList(list);
		// 用PageInfo对结果进行包装
		PageInfo<T> page = new PageInfo<T>(list);
		pageValue.setRows(page.getTotal());
		// 计算页数
		pageValue.setPages(page.getPages());
		pageValue.setSize(page.getPageSize());
		pageValue.setIndex(pageParams.getIndex());
		return pageValue;
	}

	@Override
	public <T, P extends PagingParams> List<T> getPagingModels(Class<T> clasz,P p) {
		return this.baseDao.getDataPaging(clasz, p);
	}

}
