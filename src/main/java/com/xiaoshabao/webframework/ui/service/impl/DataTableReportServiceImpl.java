package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.ui.dto.ReportColumnDto;
import com.xiaoshabao.webframework.ui.dto.ReportData;
import com.xiaoshabao.webframework.ui.entity.ReportEntity;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.service.FormReportService;
import com.xiaoshabao.webframework.ui.service.FormTableService;

@Service("dataTableReportService")
public class DataTableReportServiceImpl extends AbstractReportServiceImpl
		implements FormReportService {
	
	@Resource(name="formTableService")
	private FormTableService formTableService;
	/*
	 * 获得Report内容
	 */
	@Override
	public ReportData getReportData(ReportEntity report,
			Map<String, Object> data) {
		if(report==null){
			throw new ServiceException("未能正常获得report");
		}
		logger.info("开始处理report:{}"+report.getReportId());
		
		TableEntity table=formTableService.getTable(report.getTableId());
		List<ReportColumnDto> reportColumns=getReportColumns(report.getReportId());
		
		List<String> title=new ArrayList<String>();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ");
		
		for(ReportColumnDto reportColumn: reportColumns){
			
		}
		
		return null;
	}
	
	/**
	 * 获得所有report的列集合
	 * <p>本类会进行缓存,会返回一个不为 空，不为0的结果</p>
	 * @param reoportId
	 * @return
	 */
	protected List<ReportColumnDto> getReportColumns(String reportId){
		if(reportId==null){
			throw new ServiceException(" Report获取列集合时错误，传入的reportId为空");
		}
		List<ReportColumnDto> list=this.baseDao.getData(ReportColumnDto.class, reportId);
		if(list==null||list.size()<1){
			throw new ServiceException(reportId+" Report获取列时错误，未能取出任何数据");
		}
		return list;
	}

	

}
