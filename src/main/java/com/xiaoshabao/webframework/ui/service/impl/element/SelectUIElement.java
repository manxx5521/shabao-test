package com.xiaoshabao.webframework.ui.service.impl.element;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.ReportColumnDto;
import com.xiaoshabao.webframework.ui.dto.SelectResultDto;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.service.FormTableService;
import com.xiaoshabao.webframework.ui.service.TableColumnService;
import com.xiaoshabao.webframework.ui.service.element.ReportElement;
import com.xiaoshabao.webframework.ui.service.element.WebElement;
import com.xiaoshabao.webframework.ui.service.impl.element.def.SelectElementDef;
import com.xiaoshabao.webframework.ui.service.impl.element.dto.SelectElementDto;

/**
 * 下拉列表
 */
@Service("selectUIElement")
public class SelectUIElement extends AbstractUIElement implements
		ReportElement, WebElement {

	@Resource(name = "tableColumnService")
	private TableColumnService tableColumnService;
	@Resource(name = "formTableService")
	private FormTableService formTableService;

	// render时的参数
	@Override
	protected void getElementParams(ElementColumnDto elementDto,
			Map<String, Object> data, Map<String, Object> elementParams) {
		if (!elementDto.getTableColumn().isRef()) {
			logger.error("元素{}渲染错误，不是一个引用", elementDto.getElementId());
			throw new MsgErrorException("元素渲染错误");
		}

		String tableId = elementDto.getTableColumn().getRefTable();
		String[] tableAttr=getRefTableInfo(tableId);
		
		elementParams.put("ref_table_name", tableAttr[0]);
		elementParams.put("ref_table_id", tableAttr[1]);
		elementParams.put("ref_table_text", tableAttr[2]);

		List<SelectElementDto> selectList = this.baseDao.getData(
				SelectElementDto.class, elementParams);

		elementParams.put("dataList", selectList);
	}

	@Override
	public String[] getReportSql(ReportColumnDto reportColumn,String tableName,
			Map<String, Object> params) {
		String columnName=reportColumn.getTableColumn().getFieldCode();
		
		String tableId = reportColumn.getTableColumn().getRefTable();
		String[] tableInfo=getRefTableInfo(tableId);
		
		StringBuilder selectSql=new StringBuilder();
		selectSql.append(tableName);
		selectSql.append(".");
		selectSql.append(columnName);
		selectSql.append(",");
		selectSql.append(tableInfo[0]);
		selectSql.append(".");
		selectSql.append(tableInfo[2]);
		selectSql.append(" ");
		selectSql.append(columnName);
		selectSql.append("_$ ");

		StringBuilder leftJoin=new StringBuilder();
		leftJoin.append(" LEFT JOIN ");
		leftJoin.append(tableInfo[0]);
		leftJoin.append(" ON ");
		leftJoin.append(tableName);
		leftJoin.append(".");
		leftJoin.append(columnName);
		leftJoin.append("=");
		leftJoin.append(tableInfo[0]);
		leftJoin.append(".");
		leftJoin.append(tableInfo[1]);
		return new String[]{selectSql.toString(),columnName+"_$",leftJoin.toString()};
	}

	/**
	 * 获得关联表查询信息
	 * <p>
	 * 有必要可以考虑缓存
	 * </p>
	 * 
	 * @return {表名，编码id，名字text值}
	 */
	private String[] getRefTableInfo(String tableId) {
		List<TableColumnEntity> tableColumns = tableColumnService
				.getTableColumn(tableId);
		TableEntity table = formTableService.getTable(tableId);
		String columnId = null;
		String columnText = null;
		for (TableColumnEntity column : tableColumns) {
			Integer fieldAttr = column.getFieldAttr();
			if (fieldAttr != null) {
				if (fieldAttr == FormConstants.FIELD_ATTR_KEY) {
					columnId = column.getFieldCode();
				}
				if (fieldAttr == FormConstants.FIELD_ATTR_VALUE) {
					columnText = column.getFieldCode();
				}
			}
		}
		if (columnId == null || columnText == null) {
			logger.error("元素数据源{}配置错误，未配置下拉列表所需的列属性",
					tableId);
			throw new MsgErrorException("数据源配置错误");
		}
		return new String[]{table.getTableName(),columnId,columnText};
	}

	// -----------------------------------

	// web数据AJAX响应
	@Override
	public AjaxResult getElementResponse(Map<String, Object> params,
			JSONObject paramJSON) {
		SelectElementDef selectInfo = JSONObject.toJavaObject(paramJSON,
				SelectElementDef.class);
		SqlMapper sqlMapper = this.baseDao.getSqlMapper();
		List<SelectResultDto> list = null;
		if (selectInfo.getType() == 1) {
			list = sqlMapper.selectList(
					"SELECT A.DATA_ID id, A.DATA_NAME text FROM TD_S_STATIC A WHERE A.TYPE_ID ='"
							+ selectInfo.getCondition() + "'", params,
					SelectResultDto.class);
		} else if (selectInfo.getType() == 2) {
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			sql.append(selectInfo.getId());
			sql.append(" ID,");
			sql.append(selectInfo.getText());
			sql.append(" text from ");
			sql.append(selectInfo.getTable());
			String condition = selectInfo.getCondition();
			if (StringUtils.isNotEmpty(condition)) {
				sql.append(" where ");
				sql.append(selectInfo.getCondition());
			}
			list = sqlMapper.selectList(sql.toString(), params,
					SelectResultDto.class);
		} else if (selectInfo.getType() == 3) {
			list = sqlMapper.selectList(selectInfo.getSql(), params,
					SelectResultDto.class);
		}
		return new AjaxResult(true, "刷新成功", list);
	}


}
