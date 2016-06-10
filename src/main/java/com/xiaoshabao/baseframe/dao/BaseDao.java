package com.xiaoshabao.baseframe.dao;

import java.util.List;

import com.xiaoshabao.baseframe.bean.PagingPrams;

/**
 * 基本DAO
 * 
 * @param <T>
 *            业务数据类型
 * @param <P>
 *            参数类型
 */
public interface BaseDao {

	/**
	 * 新增实体
	 */
	public <T> int insert(Class<T> clasz, T t);

	/**
	 * 删除实体 T
	 */
	public <T> int delete(Class<T> clasz, T t);

	/**
	 * 修改一个实体
	 */
	public <T> int update(Class<T> clasz, Object params);

	/**
	 * 校验是否存在
	 */
	public <T> boolean exists(Class<T> clasz, T t);

	/**
	 * 按一定条件获取T类型的数据
	 */
	public <T> List<T> getData(Class<T> clasz, Object param);

	/**
	 * 通过sqlid获取数据
	 */
	public <T> List<T> getData(String sqlid, Object param);

	/**
	 * 通过实体T 和参数获得唯一记录<br/>
	 * 多条取第一条
	 */
	public <T> T getDataSingle(Class<T> clazz, Object param);

	/**
	 * 通过sqlid获得单条数据
	 */
	public <T> T getDataSingle(String sqlid, Object param);

	/**
	 * 分页查询业务数据
	 */
	public <T, P extends PagingPrams> List<T> getDataPaging(Class<T> clasz,
			P pageValue);

}
