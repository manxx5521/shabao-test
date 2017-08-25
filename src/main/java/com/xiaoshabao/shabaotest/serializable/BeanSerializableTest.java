package com.xiaoshabao.shabaotest.serializable;

import java.io.Serializable;

public class BeanSerializableTest {

  public static void main(String[] args) {
    /*
     * Teacher 未实现序列化，方法中可以直接修改属性值
     */
    Teacher teacher = new Teacher();
    teacher.setName("老师");
    BeanSerializableTest.changeName(teacher);
    System.out.println("老师的名字：" + teacher.getName());//实例化和不是实例的bean都可以通过方法修改值
    BeanSerializableTest.changeTeacher(teacher);
    System.out.println(teacher.getName()==null?"可以修改":"无法修改");//实例化和不是实例的bean都无法对类本身修改

    Student student = new Student();
    student.setName("学生");
    BeanSerializableTest.changeName(student);
    System.out.println("学生的名字：" + student.getName());//实例化和不是实例的bean都可以通过方法修改值
    BeanSerializableTest.changeStudent(student);
    System.out.println(student==null?"可以修改":"无法修改");//实例化和不是实例的bean都无法对类本身修改
  }

  private static void changeName(Person person) {
    person.setName("清空");
  }
  
  private static void changeTeacher(Teacher teacher) {
    teacher=new Teacher();
  }
  
  private static void changeStudent(Student student) {
    student=new Student();
  }

}

class Person{
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

class Teacher extends Person {

}

class Student extends Person implements Serializable {

  private static final long serialVersionUID = 1L;

}