package com.xiaoshabao.shabaotest.base;

import org.junit.Test;

public class BooleanTest {
	
	@Test
	public void test() {
		Boolean flag=this.getBoolean();
		
		if(flag==null) {
			System.out.println("flag现在是null");
		}
		
		if(flag!=null&&flag) {
			System.out.println("判断时要先判断空。flag是null时，会抛出异常");
		}
		
	}
	
	
	public Boolean getBoolean() {
		return null;
	}

}
