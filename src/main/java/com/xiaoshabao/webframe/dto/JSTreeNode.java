package com.xiaoshabao.webframe.dto;
/**
 * JSTree的数据节点
 */
public class JSTreeNode {
	/**
	 * id
	 */
	private String id;
	/**
	 * 显示文本
	 */
	private String text;
	/**
	 * 状态
	 */
	private JSTreeState state;
	
	/**
	 * 子节点
	 */
	private JSTreeNode children;
	/**
	 * 父级节点
	 */
	private String parent;
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
	public JSTreeState getState() {
		return state;
	}
	public void setState(JSTreeState state) {
		this.state = state;
	}
	public JSTreeNode getChildren() {
		return children;
	}
	public void setChildren(JSTreeNode children) {
		this.children = children;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

}
