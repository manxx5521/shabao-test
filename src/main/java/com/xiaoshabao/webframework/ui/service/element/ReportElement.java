package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.ReportColumnDto;

/**
 * 报表元素借口
 */
public interface ReportElement extends AbstractElement{
	
	/**
	 * 获得报表元素的查询sql
	 * <p>返回查询的字段信息,返回信息不为空。<br>
	 * 返回类型类似{"id,id_$","id_$","left join b on b.id=a.id"}</p>
	 * @return {select条件，显示的字段值，关联表和条件}
	 */
	public String[] getReportSql(ReportColumnDto reportColumn,String tableName,Map<String, Object> params);
}
