package com.xiaoshabao.baseframework.component.excel;

public class Student {
  private String age;
  private String  birth;
  
  
  public Student() {
  }
  public Student(String age, String birth) {
    this.age = age;
    this.birth = birth;
  }
  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public String getBirth() {
    return birth;
  }
  public void setBirth(String birth) {
    this.birth = birth;
  }
}
