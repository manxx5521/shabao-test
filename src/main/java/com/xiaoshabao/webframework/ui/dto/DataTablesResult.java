package com.xiaoshabao.webframework.ui.dto;

import java.util.List;

import com.xiaoshabao.webframework.dto.AjaxResult;

/**
 * dataTables返回的参数
 * <p>继承与AjaxResult其他情况更好的返回值</p>
 */
public class DataTablesResult extends AjaxResult{
	/**
	 * 绘制计数器(必须)
	 * <p>D这个是用来确保Ajax从服务器返回的是对应的,
	 * atatables发送的draw是多少那么服务器就返回多少</p>
	 */
	private Integer draw;
	
	/**
	 * 总共记录数(必要)
	 * <p>即没有过滤的记录数（数据库里总共记录数）</p>
	 */
	private Integer recordsTotal;
	
	/**
	 * 过滤后的记录数(必要)
	 * <p>如果有接收到前台的过滤条件，则返回的是过滤后的记录数</p>
	 */
	private Integer recordsFiltered;
	
	/**
	 * 表中中需要显示的数据（必要）
	 */
	private List<Object> data;
	/**
	 * 友好错误提示（可选）
	 */
	private String error;
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
