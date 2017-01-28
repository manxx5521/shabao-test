package com.xiaoshabao.webframework.ui.service.impl.element.def;
/**
 * dataTables表单参数
 */
public class DataTablesElementDef extends FormElementDef {
	/**
	 * 开启服务器模式,AJAX请求
	 */
	private boolean serverSide=true;
	/**
	 * 要访问的数据列，数据如下
	 * [
     * 	{data: "column1"},
     *  {data: "column2"}
     * ]
	 */
	private String columns;
	/**
	 * 排序功能
	 */
	private boolean bSort=false;
	/**
	 *表单id
	 */
	private String tableId;
	
	
	public boolean isServerSide() {
		return serverSide;
	}
	public void setServerSide(boolean serverSide) {
		this.serverSide = serverSide;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public boolean isbSort() {
		return bSort;
	}
	public void setbSort(boolean bSort) {
		this.bSort = bSort;
	}
}
