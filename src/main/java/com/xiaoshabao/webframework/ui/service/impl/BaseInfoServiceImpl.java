package com.xiaoshabao.webframework.ui.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BaseInfoDto;
import com.xiaoshabao.webframework.ui.dto.BaseInfoListDto;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.enums.FieldAttrEnum;
import com.xiaoshabao.webframework.ui.service.BaseInfoService;

@Service("baseInfoServiceImpl")
public class BaseInfoServiceImpl extends AbstractFormServiceImpl implements BaseInfoService {
	/***pkCode主键在baseInfo中的属性名**/
	private final static String PK_FIELD_NAME="pkCode";

	@Override
	public BaseInfoListDto getInfoView(String tableId) {
		TableInfoDto tableInfo = this.getTableInfo(tableId);
		String sql = this.getSelectSql(tableInfo);
		List<BaseInfoDto> list = this.baseDao.getSqlMapper().selectList(sql, BaseInfoDto.class);
		BaseInfoListDto result = new BaseInfoListDto();
		result.setList(list);
		return result;
	}

	@Override
	public AjaxResult addBaseInfo(String tableId, BaseInfoDto baseInfo) {
		String validMsg = this.validBaseInfo(baseInfo);
		if (validMsg != null) {
			return new AjaxResult(false, validMsg);
		}

		TableInfoDto tableInfo = this.getTableInfo(tableId);
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO (");
		sql.append(tableInfo.getTable().getTableName());

		// 拼接insert字段
		ColumnParser parser = new ColumnParser(sql, tableInfo.getColumns()) {
			@Override
			String parserFileAttr(TableColumnEntity columnEntity, String asName, int fieldAttr) {
				return columnEntity.getFieldCode();
			}
		};
		sql.append(parser.parser());

		sql.append(") VALUES (");
		// 拼接values字段
		ColumnParser valuesParser = new ColumnParser(sql, tableInfo.getColumns()) {
			@Override
			String parserFileAttr(TableColumnEntity columnEntity, String asName, int fieldAttr) {
				return "#{" + asName + "}";
			}
		};
		sql.append(valuesParser.parser());

		sql.append(")");

		int i = this.baseDao.getSqlMapper().insert(sql.toString(), baseInfo);
		if (i < 1) {
			throw new MsgErrorException("数据未插入成功");
		}
		return new AjaxResult(true);
	}

	@Override
	public AjaxResult updateBaseInfo(String tableId, BaseInfoDto baseInfo) {
		String validMsg = this.validBaseInfo(baseInfo);
		if (validMsg != null) {
			return new AjaxResult(false, validMsg);
		}

		TableInfoDto tableInfo = this.getTableInfo(tableId);
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append(tableInfo.getTable().getTableName());
		sql.append(" SET ");

		// 拼接insert字段
		ColumnParser parser = new ColumnParser(sql, tableInfo.getColumns()) {
			@Override
			String parserFileAttr(TableColumnEntity columnEntity, String asName, int fieldAttr) {
				return columnEntity.getFieldCode() + "=#{" + asName + "}";
			}
		};
		sql.append(" WHERE ");
		sql.append(parser.getPkCodeName());
		sql.append("=#{");
		sql.append(BaseInfoServiceImpl.PK_FIELD_NAME);
		sql.append("}");

		int i = this.baseDao.getSqlMapper().update(sql.toString(), baseInfo);
		if (i < 1) {
			throw new MsgErrorException("数据更新失败");
		}
		return new AjaxResult(true, "数据更新成功");
	}

	@Override
	public AjaxResult deleteBaseInfo(String tableId, BaseInfoDto baseInfo) {
		TableInfoDto tableInfo = this.getTableInfo(tableId);
		StringBuilder sql = new StringBuilder();
		// 解析code字段
		ColumnParser parser = new ColumnParser(sql, tableInfo.getColumns()) {
			@Override
			String parserFileAttr(TableColumnEntity columnEntity, String asName, int fieldAttr) {
				return "";
			}
		};

		sql.append("DELETE ");
		sql.append(tableInfo.getTable().getTableName());
		sql.append(" WHERE ");
		sql.append(parser.getPkCodeName());
		sql.append("=#{");
		sql.append(BaseInfoServiceImpl.PK_FIELD_NAME);
		sql.append("}");
		
		int i = this.baseDao.getSqlMapper().delete(sql.toString(), baseInfo);
		if (i < 1) {
			throw new MsgErrorException("删除失败");
		}
		return new AjaxResult(true, "删除成功");
	}

	private String validBaseInfo(BaseInfoDto baseInfo) {
		return null;
	}

	protected String getSelectSql(TableInfoDto tableInfo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");

		// 拼接查询字段
		ColumnParser parser = new ColumnParser(sql, tableInfo.getColumns()) {
			@Override
			String parserFileAttr(TableColumnEntity columnEntity, String asName, int fieldAttr) {
				return columnEntity.getFieldCode() + " " + asName;
			}
		};
		sql.append(parser.parser());

		sql.append(" FROM ");
		sql.append(tableInfo.getTable().getTableName());
		return sql.toString();
	}

	protected TableInfoDto getTableInfo(String tableId) {
		TableEntity table = this.baseDao.getDataById(TableEntity.class, tableId);
		if (table == null) {
			throw new MsgErrorException("无法根据" + tableId + "获得对应的基础资料表");
		}
		List<TableColumnEntity> tableList = this.baseDao.getData(TableColumnEntity.class, tableId);
		if (tableList == null || tableList.size() < 1) {
			throw new MsgErrorException("无法根据" + tableId + "获得对应的详细列信息");
		}

		TableInfoDto tableInfo = new TableInfoDto();
		tableInfo.setTable(table);
		tableInfo.setColumns(tableList);
		return tableInfo;
	}

}

abstract class ColumnParser {
	private List<TableColumnEntity> columns;
	private StringBuilder sql;
	private String pkCodeName;

	public ColumnParser(List<TableColumnEntity> columns) {
		this.columns = columns;
		sql = new StringBuilder();
	}

	public ColumnParser(StringBuilder sql, List<TableColumnEntity> columns) {
		this.sql = sql;
		this.columns = columns;
	}

	public String parser() {
		for (int i = 0, size = columns.size(); i < size; i++) {
			TableColumnEntity columnEntity = columns.get(i);
			Integer fieldAttr = columnEntity.getFieldAttr();
			String addSql = null;
			// 配置字段类型
			if (fieldAttr != null && fieldAttr.intValue() != 0) {
				addSql = parserFileAttr(columnEntity, fieldAttr.intValue());
			}

			if (StringUtils.isNotEmpty(addSql) && i != 0) {
				sql.append(",");
			}
			if (StringUtils.isNotEmpty(addSql)) {
				sql.append(addSql);
			}
		}
		return sql.toString();
	}

	private String parserFileAttr(TableColumnEntity columnEntity, int fieldAttr) {
		if (FieldAttrEnum.PK_CODE.getType() == fieldAttr) {
			this.pkCodeName=columnEntity.getFieldCode();
			return parserFileAttr(columnEntity, "pkCode", fieldAttr);
		}else if (FieldAttrEnum.BASE_CODE.getType() == fieldAttr) {
			return parserFileAttr(columnEntity, "baseCode", fieldAttr);
		} else if (FieldAttrEnum.BASE_NAME.getType() == fieldAttr) {
			return parserFileAttr(columnEntity, "baseName", fieldAttr);
		} else if (FieldAttrEnum.PARENT_CODE.getType() == fieldAttr) {
			return parserFileAttr(columnEntity, "parentCode", fieldAttr);
		} else if (FieldAttrEnum.IS_USED.getType() == fieldAttr) {
			return parserFileAttr(columnEntity, "isUsed", fieldAttr);
		} else if (FieldAttrEnum.ORDER_NO.getType() == fieldAttr) {
			return parserFileAttr(columnEntity, "orderNo", fieldAttr);
		}
		return null;
	}

	public String getPkCodeName() {
		return pkCodeName;
	}

	/**
	 * 解析添加了字段类型的sql
	 * 
	 * @param columnEntity
	 * @param asName
	 *            别名,对应{@link BaseInfoDto}的属性名
	 * @param fieldAttr
	 * @return
	 */
	abstract String parserFileAttr(TableColumnEntity columnEntity, String asName, int fieldAttr);

}

class TableInfoDto {
	private TableEntity table;

	private List<TableColumnEntity> columns;

	public TableEntity getTable() {
		return table;
	}

	public void setTable(TableEntity table) {
		this.table = table;
	}

	public List<TableColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<TableColumnEntity> columns) {
		this.columns = columns;
	}

}
