package com.xiaoshabao.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.dao.DepartDao;
import com.xiaoshabao.system.entity.DepartEntity;
import com.xiaoshabao.system.service.DepartService;
import com.xiaoshabao.webframe.dto.AjaxResult;

@Service("departServiceImpl")
public class DepartServiceImpl extends AbstractServiceImpl implements DepartService {
	
	@Autowired
	private DepartDao departDao;
	
	//获取部门详细信息
	@Override
	public AjaxResult getDepartInfo(String departId) {
		if(StringUtils.isEmpty(departId)){
			return new AjaxResult("部门不能为空");
		}
		if(departId.length()!=5){
			return new AjaxResult("部门编码错误");
		}
		DepartEntity depart= departDao.getDepartById(departId);
		
		if(depart==null||StringUtils.isEmpty(depart.getDepartId())){
			return new AjaxResult("查询的部门不存在");
		}
		return new AjaxResult(depart);
	}
	//修改部门信息
	@Override
	public void editDepart(DepartEntity depart) {
		int i=departDao.editDepart(depart);
		if(i<1){
			throw new ServiceException("信息更新失败");
		}
	}

	@Override
	public void addSameDepart(DepartEntity depart) {
	}

	@Override
	public void addLowerDepart(DepartEntity depart) {
	}


}
