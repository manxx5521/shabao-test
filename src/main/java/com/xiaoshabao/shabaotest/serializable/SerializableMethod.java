package com.xiaoshabao.shabaotest.serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
/*
 * 实现序列化Serializable的类型经过方法时，会重新变为局部变量。
 * 不实现的指针不变，在方法内值改变，源方法的变量变化。
 */
public class SerializableMethod {
	
	//String实现序列化，原字符串不变
	@Test
	public void testString(){
		//变量式不会变
		String s="11";
		changeString(s);
		System.out.println("字符串序列化："+s);
		
		//对象式也不便，因为实例化了
		String b=new String("11");
		changeString(b);
		System.out.println("字符串序列化："+b);
	}
	private void changeString(String s){
		s="22";
	}
	
	/**
	 * 数组这种，即使是基本类型，但是改变时，值仍然改变
	 */
	@Test
	public void testArray(){
		int[] a={1,2,3};
		changeArray(a);
		System.out.println("输出数字数组："+a[0]+""+a[1]+a[2]);
	}
	
	private void changeArray(int[] a){
		a[2]=1;
		a[1]=1;
	}
	
	//Lit对象是序列化的，方法改变list本身时，list的值不变。
	//Lit里的元素是不会序列化的，方法改变元素值时，list(0)的元素值变化。
	@Test
	public void testList(){
		List<Object> list=new ArrayList<Object>();
		list.add("11");
		this.changeList1(list);
		System.out.println("List表本身序列化，值不变:"+list.get(0));
		
		this.changeList2(list);
		//元素没有序列化
		System.out.println("List添加字符串元素，值变："+list.get(0));
	}
	private void changeList1(List<Object> list){
		list=new ArrayList<Object>();
	}
	private void changeList2(List<Object> list){
		list.add(0, "22");
	}
	
	
	//和list一样
	//Lit对象是序列化的，方法改变list本身时，list的值不变。
	//Lit里的元素是不会序列化的，方法改变元素值时，list(0)的元素值变化。
	@Test
	public void testMap(){
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("zhi", "11");
		this.changeMap1(maps);
		System.out.println("Map本身序列化，值不变："+maps.get("zhi"));
		this.changeMap2(maps);
		System.out.println("Map里元素变化，值变："+maps.get("zhi"));
	}
	public void changeMap1(Map<String,String> maps){
		maps=new HashMap<String,String>();
	}
	public void changeMap2(Map<String,String> maps){
		maps.put("zhi", "22");
	}
}
