package com.xiaoshabao.webframework.ui.enums;

/**
 * 字段类型
 */
public enum FieldTypeEnum {
	
	VARCHAR(1,"字符型","VARCHAR"),
	NUMBER(2,"数字型","NUMERIC"),
	DATE(3,"日期型","DATE");
	
	
	/** 类型 */
	private int type;
	/** 所在位置名称 */
	private String name;
	/** 对应jdbc类型，利于mybatis存储等*/
	private String jdbcType;
	
	private FieldTypeEnum(int type,String name,String jdbcType){
		this.type=type;
		this.name=name;
		this.jdbcType=jdbcType;
	}
	
	/**
	 * 根据类型获得jdbc类型
	 * @param type
	 * @return
	 */
	public static String getJdbcTypeByType(int type){
		for(FieldTypeEnum fieldType:values()){
			if(fieldType.getType()==type){
				return fieldType.getJdbcType();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public String getJdbcType() {
		return jdbcType;
	}
	
	

}
