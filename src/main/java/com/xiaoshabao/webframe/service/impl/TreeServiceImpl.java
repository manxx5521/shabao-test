package com.xiaoshabao.webframe.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframe.dao.ElementDao;
import com.xiaoshabao.webframe.dto.JSTreeNode;
import com.xiaoshabao.webframe.entity.ElementEntity;
import com.xiaoshabao.webframe.entity.TreeElement;
import com.xiaoshabao.webframe.service.TreeService;
@Service("treeServiceImpl")
public class TreeServiceImpl extends AbstractServiceImpl implements TreeService{
	@Autowired
	private ElementDao elementDao;
	
	//获得JSTree数据
	@Override
	public List<JSTreeNode> getJSTreeList(Integer elementId){
		TreeElement param=this.validation(elementId);
		String type=param.getType();
		List<JSTreeNode> list=null;
		SqlMapper sqlMapper=this.baseDao.getSqlMapper();
		if(type.equals("1")){
			list=sqlMapper.selectList(param.getTreeSQL(), null, JSTreeNode.class);
		}
		return list;
	}
	
	/**
	 * 对数据进行验证
	 * @param elementId
	 * @return
	 */
	private TreeElement validation(Integer elementId){
		if(elementId==null||elementId==0){
			throw new ServiceException("传入的元素不能为空");
		}
		ElementEntity element=elementDao.getElementById(elementId);
		if(element==null||element.getElementId()==null||element.getElementId()==0){
			throw new ServiceException("没有获取到对应元素");
		}
		String json=element.getParam();
		if(StringUtils.isEmpty(json)){
			throw new ServiceException("没有获得树组件对应参数");
		}
		TreeElement param=JSONObject.parseObject(json, TreeElement.class);
		if(StringUtils.isEmpty(param.getType())){
			throw new ServiceException("树参数的类型不能为空");
		}
		if(StringUtils.isEmpty(param.getTreeSQL())){
			throw new ServiceException("树参数的取数sql不能为空");
		}
		return param;
	}

}
