package com.xiaoshabao.shabaotest.module.commons.beanutils;

public class StudentBean {
	
	private String name;
	private int age;
	
	public StudentBean() {
	}
	public StudentBean(String name, int age) {
		this.name = name;
		this.age = age;
	}
	/**
	 * 获得 name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获得 age
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * 设置 age
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
