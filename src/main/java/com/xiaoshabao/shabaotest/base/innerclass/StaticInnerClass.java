package com.xiaoshabao.shabaotest.base.innerclass;

/**
 * 静态内部类
 * <p>
 * 可以访问外部类的静态成员和静态方法，包括了私有的静态成员和方法。
 * 生成静态内部类对象的方式为：
　　OuterClass.InnerClass inner = new OuterClass.InnerClass();
 * </p>
 */
public class StaticInnerClass {
  private static int a = 4;

  // 静态内部类
  public static class Inner {
    public void test() {
      // 静态内部类可以访问外部类的静态成员
      // 并且它只能访问静态的
      System.out.println(a);
    }

  }

  public static void main(String[] args) {
    StaticInnerClass.Inner inner = new StaticInnerClass.Inner();
    inner.test();
  }
}
