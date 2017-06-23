package com.xiaoshabao.webframework.ui.dto;

import java.util.List;
import java.util.Map;

import com.xiaoshabao.webframework.ui.entity.FormField;
import com.xiaoshabao.webframework.ui.enums.FieldTypeEnum;

public class FormFieldSet {
	private String mainTableName;
	private List<FormField> mainFields;
	private String mainIdColumn;
	/** 数据*/
	private Map<String,Object> data;
	
	

	
	/**
	 * 获得insert语句
	 */
	public String getInsertSql(){
		StringBuilder sql=new StringBuilder("<script> INSERT INTO ");
		sql.append(mainTableName);
		
		StringBuilder paramsSql=new StringBuilder();
		sql.append(" (");
		for(int i=0,len=mainFields.size();i<len;i++){
			FormField field=mainFields.get(i);
			if(i!=0){
				sql.append(",");
				paramsSql.append(",");
			}
			sql.append(field.getFieldCode());
			appendVarString(paramsSql,field.getFieldCode(),field.getFieldType());
		}
		sql.append(" ) VALUES (");
		sql.append(paramsSql);
		sql.append(") </script>");
		return sql.toString();
	}
	
	/**
	 * 向字符串添加mybatis变量属性#{tableName,jdbcType=VARCHAR}
	 * @param sb
	 * @param varName 变量名
	 * @param fieldType 字段类型
	 */
	protected void appendVarString(StringBuilder sb,String varName,Integer  fieldType){
		sb.append("#{");
		sb.append(varName);
		sb.append(",jdbcType=");
		sb.append(FieldTypeEnum.getJdbcTypeByType(fieldType));
		sb.append("}");
	}
	
	/**
	 * 获得主表主键列
	 * @return
	 */
	public String getMainIdColumn() {
		if(mainIdColumn==null){
			for(FormField field: mainFields){
				if(field.isKey()){
					mainIdColumn=field.getFieldCode();
				}
			}
		}
		return mainIdColumn;
	}
	
	

	public void setMainIdColumn(String mainIdColumn) {
		this.mainIdColumn = mainIdColumn;
	}

	public List<FormField> getMainFields() {
		return mainFields;
	}

	public void setMainFields(List<FormField> mainFields) {
		this.mainFields = mainFields;
	}

	public String getMainTableName() {
		return mainTableName;
	}

	public void setMainTableName(String mainTableName) {
		this.mainTableName = mainTableName;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
