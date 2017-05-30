package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.ReportData;
import com.xiaoshabao.webframework.ui.entity.ListEntity;
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
	public ReportData getReportData(ListEntity listEntity,ReportEntity report,Map<String, Object> data);
	
	/**
   * 获得Report查询SQL
   * <P>返回三个值{selectSql,fromSql,tableName}
   * </P>
   * @param tableId
   * @return {查询SQL，数据表关联SQL，主表名}
   */
  public String[] getReportQuerySql(String reportId,Map<String, Object> data);

}
