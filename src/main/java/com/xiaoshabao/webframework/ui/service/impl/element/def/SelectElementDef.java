package com.xiaoshabao.webframework.ui.service.impl.element.def;
/**
 * select表单参数
 */
public class SelectElementDef extends FormElementDef {
	/** 类型 */
	private Integer type;
	/** 要枚举的表 */
	private String table;
	/** 查询表返回的key */
	private String id;
	/** 查询表返回的value,显示的值 */
	private String text;
	/** 条件 */
	private String condition;
	/** 过滤 */
	private String filter;
	/** 数据源 sql*/
	private String sql;
	/** 是否显示全部 */
	private boolean TextAll=false;
	/** 是否初始化值 */
	private boolean initData=true;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public boolean isTextAll() {
		return TextAll;
	}
	public void setTextAll(boolean textAll) {
		TextAll = textAll;
	}
	public boolean isInitData() {
		return initData;
	}
	public void setInitData(boolean initData) {
		this.initData = initData;
	}
}
