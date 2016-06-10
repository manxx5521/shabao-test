package com.xiaoshabao.baseframe.service;

import java.util.List;

import com.xiaoshabao.baseframe.bean.PageValue;
import com.xiaoshabao.baseframe.bean.PagingPrams;

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
	 * 根据sqlid获得单条数据
	 */
	public <T> T getDataSingle(String sqlid, Object param);

	/**
	 * 根据类型获得单条数据
	 */
	public <T> T getDataSingle(Class<T> clazz, Object param);

	/**
	 * 分页方法
	 */
	public <T, P extends PagingPrams> PageValue<T> getDataPaging(
			Class<T> clasz, P pagingPrams);

	/**
	 * 查询
	 */
	public <T, P extends PagingPrams> List<T> getPagingModels(Class<T> clasz,
			P p);

}