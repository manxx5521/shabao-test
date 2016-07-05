package com.xiaoshabao.system.entity;

public class SeqStringEntity {
	
	private Integer id;
	/**
	 * 元素，要循环的元素 A,B,0,1 形式
	 */
	private String elements;
	/**
	 * 想要获取的字符串长度
	 */
	private int length;
	/**
	 * 元素索引 1:12;2:1
	 */
	private String index;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
