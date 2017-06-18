package com.xiaoshabao.webframework.ui.component;

import java.util.List;

import com.xiaoshabao.webframework.ui.dto.FormFieldSet;
import com.xiaoshabao.webframework.ui.entity.FormField;

public class FormFieldUtil {
	
	/**
	 * 获得主表的insert语句
	 * @param fieldBean
	 * @return
	 */
	public static String getMainInsertSql(FormFieldSet fieldBean){
		return getInsertSql(fieldBean.getMainTableName(),fieldBean.getMainFields());
	}
	
	/**
	 * 获得insert语句
	 * @param mainTableName
	 * @param mainFields
	 * @return
	 */
	private static String getInsertSql(String mainTableName,List<FormField> mainFields){
		StringBuilder sql=new StringBuilder("<script> INSERT INTO ");
		sql.append(mainTableName);
		
		StringBuilder paramsSql=new StringBuilder();
		sql.append(" (");
		for(int i=0,len=mainFields.size();i<len;i++){
			FormField field=mainFields.get(i);
			if(i!=0){
				sql.append(",");
			}
			sql.append(field.getFieldCode());
		}
		sql.append(" ) VALUES (");
		sql.append(paramsSql);
		sql.append(") </script>");
		return sql.toString();
	}

}
