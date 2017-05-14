package com.xiaoshabao.webframework.ui.service.impl.element;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoshabao.webframework.ui.dto.ReportColumnDto;
import com.xiaoshabao.webframework.ui.service.element.ReportElement;

/**
 * 默认表单处理元素
 */
@Service("defaultUIElement")
public class DefaultUIElement extends AbstractUIElement implements
		ReportElement {

	@Override
	public String[] getReportSql(ReportColumnDto reportColumn,String tableName,
			Map<String, Object> params) {
		return new String[] { reportColumn.getTableColumn().getFieldCode(),
				reportColumn.getTableColumn().getFieldCode(), "" };
	}

}
