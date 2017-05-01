package com.xiaoshabao.shabaotest.bean;

public class PersionBean {
	private String name = "张三";
	private int age = 10;

	@Override
	public String toString() {
		return "名字:" + name + "--年龄:" + age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// @JSONField(name="csage") //这个注解可以在转换时输出成指定的csage 列明
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
