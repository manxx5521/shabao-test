package com.xiaoshabao.shabaotest.base.loadinit;

import java.util.Arrays;

/**
 * 演示自定义类的初始化过程
 * <p>调用方法前会优先初始化非静态变量</p>
 */
public class ClassInit {
	private static int[] a = new int[4];
	private static ClassInit aa = new ClassInit();
	boolean[] flag = new boolean[5];
	private static char[] ad = new char[4];

	private ClassInit() {// 构造方法
		init();
	}

	public static ClassInit getAA() {// 得到单态实例
		return aa;
	}

	public void init() {
		Arrays.fill(a, 1);// 此处不会出错
		Arrays.fill(flag, true);// 此处也不会出错，用作对比
		Arrays.fill(ad, 'd');// 此处将会出错
		System.out.println(a[0]);
	}

	/** * @param args */
	public static void main(String[] args) {
		/**
		 a,aa,ad都是静态的，在类加载的时候，
		 会按顺序给他们创建空间，a创建了4个int，
		 到了aa，aa是个自定义的对象，调用其无参构造方法，
		 然后，在调用init（）前JVM会先初始化类中非静态属性flag，
		 为flag创建了5个boolean，然后调用其init（），
		 而在init（）中你调用了未初始化的ad，ad此时为空，异常就出来了。
		 */
	}
}
