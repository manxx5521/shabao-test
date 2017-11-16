package com.xiaoshabao.shabaotest.base.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * 基本语法
 * <p>
 * 基本上是取代了接口函数和匿名类。使代码更加优雅
 * </p>
 */
public class LambdasBaseTest {

	private static List<Persion> list = new ArrayList<>();

	static {
		list.add(new Persion("张三", 11));
		list.add(new Persion("李四", 15));
		list.add(new Persion("王二", 21));
	}

	@Test
	public void test() {
		/*
		 * 此处是file的使用 File dir = new File("/an/dir/"); FileFilter directoryFilter =
		 * (File f) -> f.isDirectory(); File[] dirs = dir.listFiles(directoryFilter);
		 * 
		 * File dir = new File("/an/dir/"); File[] dirs = dir.listFiles((File f) ->
		 * f.isDirectory());
		 */
		
		String rs=null;
		// 完整步骤
		ParserAble able = new ParserAble(list);
		ParserFunction function = (Persion persion) -> persion.getAge() > 20;// 通过表达式判断
		rs=able.parser(function);
		System.out.println("年龄大于20："+rs);
		
		//最简步骤
//		ParserAble able = new ParserAble(list);
		rs=able.parser(persion -> persion.getAge() < 20);
		System.out.println("年龄小于20："+rs);
		
		//使用代码块
		rs=able.parser(persion -> {
			if(persion.getAge()<20) {
				return true;
			}
				
			return false;
		});
		System.out.println("年龄小于20："+rs);
		
		
		//可以使用预置函数
		/*
		java.util.function 包中。下面是其中的一些：
		Function<T, R> -T作为输入，返回的R作为输出
		Predicate<T> -T作为输入，返回的boolean值作为输出
		Consumer<T> - T作为输入，执行某种动作但没有返回值
		Supplier<T> - 没有任何输入，返回T
		BinaryOperator<T> -两个T作为输入，返回一个T作为输出，对于“reduce”操作很有用*/
		
		//使用自带的函数Predicate<T> 
		rs=able.parser1(persion -> persion.getAge() > 20);
		System.out.println("年龄大于20："+rs);
		
		//使用自带的函数 Function<T, R> 
		ParserAbleFunction<PersionReturn> rable = new ParserAbleFunction<PersionReturn>(list);
		rs=rable.parser(persion -> {
			if(persion.getAge() > 20) {
				return new PersionReturn(persion.getAge());
			}
			return null;
			});
		System.out.println("年龄大于20："+rs);
		

	}

}

/** 解析类 **/
class ParserAble {
	private List<Persion> data;

	public ParserAble(List<Persion> data) {
		this.data = data;
	}

	public String parser(ParserFunction function) {
		StringBuilder sb = new StringBuilder();
		for (Persion persion : data) {
			if (persion != null && function.parser(persion)) {
				sb.append(persion.getName());
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	public String parser1(Predicate<Persion> function) {
		StringBuilder sb = new StringBuilder();
		for (Persion persion : data) {
			if (persion != null && function.test(persion)) {
				sb.append(persion.getName());
				sb.append(",");
			}
		}
		return sb.toString();
	}
}

/** 解析类(使用系统自带的输入输出) **/
class ParserAbleFunction<R extends PersionReturn> {
	private List<Persion> data;

	public ParserAbleFunction(List<Persion> data) {
		this.data = data;
	}

	public String parser(Function<Persion, R> function) {
		StringBuilder sb = new StringBuilder();
		for (Persion persion : data) {
			R r=function.apply(persion);
			if(r!=null&&r.isSuccess()) {
				sb.append(r.getAge());
			}
		}
		return sb.toString();
	}
}

/** 函数接口 **/
interface ParserFunction {
	boolean parser(Persion persion);
}

class Persion {
	private String name;
	private int age;

	public Persion(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

class PersionReturn{
	private boolean success;
	private Integer age;
	
	public PersionReturn(Integer age) {
		this.success=true;
		this.age=age;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
