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
	 * 获得update语句
	 */
	public String getUpdateSql(){
		StringBuilder sql=new StringBuilder("<script> UPDATE ");
		sql.append(mainTableName);
		sql.append(" <set> ");
		
		for(int i=0,len=mainFields.size();i<len;i++){
			FormField field=mainFields.get(i);
			this.appendIfNullStart(sql, field.getFieldCode());
			sql.append(field.getFieldCode());
			sql.append("=");
			appendVarString(sql,field.getFieldCode(),field.getFieldType());
			sql.append(",");
			this.appendIfNullEnd(sql, field.getFieldCode());
		}
		
		sql.append(" </set> ");
		sql.append(" where ");
		sql.append(getMainIdColumn());
		sql.append("= #{");
		sql.append(getMainIdColumn());
		sql.append("}");
		return sql.toString();
	}
	
	/**
	 * 向字符串添加mybatis变量属性#{tableName,jdbcType=VARCHAR}
	 * @param sb
	 * @param varName 变量名
	 * @param fieldType 字段类型
	 */
	protected void appendVarString(StringBuilder sql,String varName,Integer  fieldType){
		sql.append("#{");
		sql.append(varName);
		sql.append(",jdbcType=");
		sql.append(FieldTypeEnum.getJdbcTypeByType(fieldType));
		sql.append("}");
	}
	
	/**
	 * if语句
	 */
	protected void appendIfNullStart(StringBuilder sql,String varName){
		sql.append(" <if test=\"");
		sql.append(varName);
		sql.append(" !=null and ");
		sql.append(varName);
		sql.append(" !='' \"> ");
		
	}
	/**
	 * if语句
	 */
	protected void appendIfNullEnd(StringBuilder sql,String varName){
		sql.append(" </if> ");
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
