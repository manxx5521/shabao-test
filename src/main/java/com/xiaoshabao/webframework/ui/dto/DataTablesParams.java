package com.xiaoshabao.webframework.ui.dto;


/**
 * dataTables传递的参数
 */
public class DataTablesParams {
	/**
	 * 绘制计数器(必须)
	 * <p>D这个是用来确保Ajax从服务器返回的是对应的,
	 * atatables发送的draw是多少那么服务器就返回多少</p>
	 */
	private Integer draw;
	/**
	 * 第一条数据的起始位置，比如0代表第一条数据
	 */
	private Integer start;
	/**
	 * 告诉服务器每页显示的条数
	 */
	private Integer length;
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
}
