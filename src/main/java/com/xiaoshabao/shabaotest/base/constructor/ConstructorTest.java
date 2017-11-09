package com.xiaoshabao.shabaotest.base.constructor;

/**
 * 构造方法测试类
 *
 */
public class ConstructorTest {
	private String value = "1";

	public ConstructorTest() {
	}

	public ConstructorTest(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static void main(String[] args) {
		// 走无参构造
		ConstructorTest t1 = new ConstructorTest();
		System.out.println(t1.toString());

		// 走参构造
		ConstructorTest t2 = new ConstructorTest("2");
		System.out.println(t2.toString());

		// 子类直接调用有参构造,因为没有声明，调用父类时使用默认构造方法
		SubBean sub1 = new SubBean("sub3");
		System.out.println(sub1.toString());
		System.out.println(sub1.toBString());

		/**
		 * 构造方法调用时，因为ide的原因已经不出现，默认构造方法导致的错误
		 * 
		 * 声明构造方法后，默认构造方法失效。
		 * 
		 * 子类调用父类构造方法时，如果不声明，默认调用无参构造函数
		 * 
		 */

	}
}

class BaseBean {
	private String baseValue = "b1";

	public String toBString() {
		return baseValue;
	}

}

class SubBean extends BaseBean {

	private String subValue = "sub1";

	public SubBean(String value) {
		this.subValue = value;
	}

	@Override
	public String toString() {
		return subValue;
	}

}
