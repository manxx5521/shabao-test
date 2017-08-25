package com.xiaoshabao.shabaotest.base.innerclass;
/**
 * 匿名内部类就是没有名字的局部内部类，不使用关键字class, extends, implements, 没有构造方法。

　　匿名内部类隐式地继承了一个父类或者实现了一个接口。

　　匿名内部类使用得比较多，通常是作为一个方法参数。
 */
public class AnonymouseInnerClass {
  
  public void say(){
    System.out.println("原来的内容");
  }
  public static void main(String[] args)
  {
      AnonymouseInnerClass test = new AnonymouseInnerClass();
      test.say();

      
      //匿名类覆盖
      AnonymouseInnerClass test2 = new AnonymouseInnerClass(){
        @Override
        public void say() {
          System.out.println("匿名类覆盖内容");
        }
        
      };
      test2.say();
  }
}
