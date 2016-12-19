package com.xiaoshabao.shabaowebtest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.shabaowebtest.entity.DemoEntity;

/**
 * 示例DAO
 */
public interface DemoDao {
	/**
	 * 添加单个实体
	 * @param entity
	 * @return
	 */
	public int insertSingle(DemoEntity entity);
	/**
	 * 添加多条记录
	 * @param list
	 * @return
	 */
	public int insertList(List<DemoEntity> list);
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	public int delete(DemoEntity entity);
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	public int update(@Param("entity") DemoEntity entity,@Param("id")Integer id);
}
