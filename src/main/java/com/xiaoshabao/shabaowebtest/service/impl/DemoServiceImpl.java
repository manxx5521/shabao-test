package com.xiaoshabao.shabaowebtest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.DaoException;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.shabaowebtest.dao.DemoDao;
import com.xiaoshabao.shabaowebtest.dto.DemoDto;
import com.xiaoshabao.shabaowebtest.entity.DemoEntity;
import com.xiaoshabao.shabaowebtest.service.DemoService;

@Service("demoService")
public class DemoServiceImpl extends AbstractServiceImpl implements DemoService {
	@Autowired
	private DemoDao demoDao;
	
	//测试SQL,使用id的方式
	@Override
	public DemoDto testSQL() throws ServiceException, DaoException {
		String sql="select SYSDATE() date";
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("sql", sql);
		List<DemoDto> list= this.getData("demotestsql", param);
		DemoDto dato=list.get(0);	
		return dato;
	}
	
	/**
	 * 添加单个对象
	 */
	public void insertSingle(DemoEntity entity){
		entity.setName("张三");
		entity.setAge(2);
		int i=demoDao.insertSingle(entity);
		if(i<1){
			logger.error("插入失败***********");
		}else{
			logger.info("插入成功*********");
		}
	}
	/**
	 * 添加多条记录
	 */
	public void insertList(List<DemoEntity> list){
		int i=demoDao.insertList(list);
		if(i<1){
			logger.error("插入失败***********");
		}else{
			logger.info("插入成功*********");
		}
	}
	/**
	 * 删除
	 * @param entity
	 */
	public void delete(DemoEntity entity){
		
	}
	

}
