package com.xiaoshabao.system.service;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.system.entity.DepartEntity;
import com.xiaoshabao.webframe.dto.AjaxResult;

public interface DepartService extends AbstractService {
	/**
	 * 获取部门详细信息
	 * @param departId
	 * @return
	 */
	public AjaxResult getDepartInfo(String departId);
	/**
	 * 修改部门信息
	 * @param depart
	 * @return
	 */
	public void editDepart(DepartEntity depart);
	/**
	 * 添加同级部门
	 * @param depart
	 * @return
	 */
	public DepartEntity addSameDepart(DepartEntity depart);
	/**
	 * 添加下级部门
	 * @param depart
	 * @return
	 */
	public DepartEntity addLowerDepart(DepartEntity depart);

}
