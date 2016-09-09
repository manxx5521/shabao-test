package com.xiaoshabao.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.dao.DepartDao;
import com.xiaoshabao.system.entity.DepartEntity;
import com.xiaoshabao.system.service.DepartService;
import com.xiaoshabao.webframework.dto.AjaxResult;

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
		return new AjaxResult(true,depart);
	}
	//修改部门信息
	@Override
	public void editDepart(DepartEntity depart) {
		int i=departDao.updateDepart(depart);
		if(i<1){
			throw new ServiceException("信息更新失败");
		}
	}

	@Override
	public DepartEntity addSameDepart(DepartEntity depart) {
		return null;
	}

	@Override
	@Transactional
	public DepartEntity addLowerDepart(DepartEntity depart) {
		String parentDepartId=depart.getParentDepartId();
		DepartEntity parentDepart=departDao.getDepartById(parentDepartId);
		if(parentDepart==null||StringUtils.isEmpty(parentDepart.getDepartId())){
			throw new ServiceException("未正确获得父级部门信息");
		}
		int td=Integer.parseInt(parentDepartId);
		
		String departId=String.valueOf(td+1);
		depart.setDepartId(departId);
		depart.setDepartFrame(parentDepart.getDepartFrame()+departId);
		depart.setDepartLevel(parentDepart.getDepartLevel()+1);
		depart.setChildNum(0);
		depart.setUsed(1);
		int i=departDao.addDepart(depart);
		if(i<1){
			throw new ServiceException("插入部门信息失败");
		}
		//更新上级部门数据
		parentDepart.setChildNum(parentDepart.getChildNum()+1);
		int pi=departDao.updateDepart(parentDepart);
		if(pi<1){
			throw new ServiceException("父级部门信息更新异常");
		}
		return depart;
	}


}
