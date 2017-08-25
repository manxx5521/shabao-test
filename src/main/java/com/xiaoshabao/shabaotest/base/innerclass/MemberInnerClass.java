package com.xiaoshabao.shabaotest.base.innerclass;

/**
 * 成员内部类
 * <p>
 * 成员内部类就像一个实例变量。<br>
　　它可以访问它的外部类的所有成员变量和方法，不管是静态的还是非静态的都可以。
　　在外部类里面创建成员内部类的实例：<br>
　　this.new Innerclass();<br>
　　在外部类之外创建内部类的实例：<br>
　　(new Outerclass()).new Innerclass();<br>
　　在内部类里访问外部类的成员：<br>
　　Outerclass.this.member
 * </p>
 */
public class MemberInnerClass {
  private String work = "student";

  class Student {
    private String name = "张三";

    public void say() {
      System.out.println(name + "是" + work);//可以使用外部类方法等,直接访问或者 MemberInnerClass.this.work形式访问
    }
  }

  public static void main(String[] args) {

    // 创建成员内部类的对象
    // 需要先创建外部类的实例
    MemberInnerClass.Student inner = new MemberInnerClass().new Student();

    inner.say();
  }
}
