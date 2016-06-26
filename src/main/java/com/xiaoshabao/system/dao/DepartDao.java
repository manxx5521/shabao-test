package com.xiaoshabao.system.dao;

import com.xiaoshabao.system.entity.DepartEntity;

/**
 * 部门数据操作
 */
public interface DepartDao {
	/**
	 * 获得部门详细信息
	 * @param departId
	 * @return
	 */
	public DepartEntity getDepartById(String departId);
	
	/**
	 * 修改部门信息
	 * @param depart
	 * @return
	 */
	public int editDepart(DepartEntity depart);
	/**
	 * 添加统计部门
	 * @param depart
	 * @return
	 */
	public int addSameDepart(DepartEntity depart);
	
	/**
	 * 添加下级部门
	 * @param depart
	 * @return
	 */
	public int addLowerDepart(DepartEntity depart);

}
