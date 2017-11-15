package com.xiaoshabao.shabaotest.module.xml;

import java.io.Serializable;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class XStreamTest {
	/**
	 * 从XML转换成bean
	 */
	@Test
	public void testToBean() {
		XStream xStream = new XStream();// 默认方式，使用自带的包解析
		// XStream xStream=new XStream(new DomDriver());//使用JDK DOM 解析方式
		// XStream xstream = new XStream(new StaxDriver());//使用JDK STAX方式

		// 修改元素名称,相当于取别名，不设置使用全路径名
		xStream.alias("person", Person.class);

		String xml = "<person>" + "  <name>张三</name> " + "  <age>12</age> " + "</person> ";

		Person person = (Person) xStream.fromXML(xml);
		System.out.println(person);
	}

	/**
	 * 从bean转换成XML
	 */
	@Test
	public void testToXML() {
		XStream xStream = new XStream();// 默认方式，使用自带的包解析
		// XStream xStream=new XStream(new DomDriver());//使用JDK DOM 解析方式
		// XStream xstream = new XStream(new StaxDriver());//使用JDK STAX方式

		// 修改元素名称,相当于取别名，不设置使用全路径名
		xStream.alias("person", Person.class);

		Person person = new Person();
		person.setAge(12);
		person.setName("张三");

		String xml = xStream.toXML(person);
		System.out.println(xml);
	}

	class Person implements Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private Integer age;

		public String getName() {
			return name;

		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Persion [name=" + name + ", age=" + age + "]";
		}

	}

}
