package com.xiaoshabao.shabaowebtest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.DaoException;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.shabaowebtest.dto.DemoDto;
import com.xiaoshabao.shabaowebtest.service.DemoService;

@Service("demoService")
public class DemoServiceImpl extends AbstractServiceImpl implements DemoService {
	
	//测试SQL
	@Override
	public DemoDto testSQL() throws ServiceException, DaoException {
		String sql="select SYSDATE() date";
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("sql", sql);
		List<DemoDto> list= this.getData("demotestsql", param);
		DemoDto dato=list.get(0);	
		return dato;
	}

	

}
