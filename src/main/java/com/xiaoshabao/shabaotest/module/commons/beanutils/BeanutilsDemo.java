package com.xiaoshabao.shabaotest.module.commons.beanutils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

/**
 * BeanUtils 示例代码
 */
public class BeanutilsDemo {
	
	//属性设置
	@Test
	public void propertyTest() throws Exception{
		StudentBean student=new StudentBean("张三",18);
		
		//给某个属性设值
		BeanUtils.setProperty(student, "name", "男");
		//获得类属性值，可以使用 address.city 方式访问内部属性、customers[1].name访问list
		String sex=BeanUtils.getProperty(student, "name");
		System.out.println("获得属性name:"+sex);
	}
	
	//使用排序
	@Test
	public void beanCompartor() {
		List<StudentBean> list=new ArrayList<StudentBean>();
		list.add(new StudentBean("张三",3));
		list.add(new StudentBean("王二",2));
		list.add(new StudentBean("李四",3));
		
		//使用
	}
}
