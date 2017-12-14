package com.xiaoshabao.baseframework.service;

import java.util.List;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.baseframework.bean.PagingParams;

/**
 * 基本sevice
 */
public interface AbstractService {

	/**
	 * 添加方法
	 */
	public <T> int insert(Class<T> clasz, T t);

	/**
	 * 删除方法
	 */
	public <T> int delete(Class<T> clasz, T t);

	/**
	 * 更新方法
	 */
	public <T> int update(Class<T> clasz, T t, Object p);

	/**
	 * 判断是否存在
	 */
	public <T> boolean exists(Class<T> clasz, T t);

	/**
	 * 根据类和参数获得多条数据
	 */
	public <T> List<T> getData(Class<T> clasz, Object params);

	/**
	 * 根据sqlid获得多条数据
	 */
	public <T> List<T> getData(String sqlid, Object param);

	/**
	 * 分页方法
	 */
	public <T, P extends PagingParams> PageValue<T> getDataPaging(Class<T> clasz, P pagingPrams);

	/**
	 * 查询
	 */
	public <T, P extends PagingParams> List<T> getPagingModels(Class<T> clasz,P p);

}