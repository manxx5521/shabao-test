package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.service.TableColumnService;

@Service("tableColumnService")
public class TableColumnServiceImpl implements TableColumnService {
	/*
	 * 根据id获得数据列集合
	 */
	@Override
	public List<TableColumnEntity> getTableColumn(String tableId) {
		if(StringUtils.isEmpty(tableId)){
			throw new ServiceException("获得数据源时，数据源tableId传入为空");
		}
		
		List<TableColumnEntity> tableColumns=new ArrayList<TableColumnEntity>();
		
		if(tableColumns==null||tableColumns.size()<1){
			throw new ServiceException("数据源"+tableId+"获取错误");
		}
		return tableColumns;
	}

}
