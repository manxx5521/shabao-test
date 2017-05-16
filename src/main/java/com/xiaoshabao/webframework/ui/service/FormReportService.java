package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.ReportData;
import com.xiaoshabao.webframework.ui.entity.ReportEntity;

/**
 * 简单列表引擎
 * <p>
 *	
 * </p>
 */
public interface FormReportService {
	/**
	 * 获得Report内容
	 * @param billListDto
	 * @return
	 */
	public ReportData getReportData(ReportEntity report,Map<String, Object> data);
	
	/**
   * 获得Report查询SQL
   * @param tableId
   * @return
   */
  public String getReportQuerySql(String reportId,Map<String, Object> data);

}
