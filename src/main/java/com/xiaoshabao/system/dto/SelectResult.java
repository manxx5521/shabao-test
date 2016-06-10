package com.xiaoshabao.system.dto;
/**
 * 获取下拉列表
 */
public class SelectResult {
	
	private String id;
	private String text;
	
	public SelectResult(){
	}
	public SelectResult(String id,String text){
		this.id=id;
		this.text=text;
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
}
