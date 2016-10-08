package test;

import org.junit.Test;

import util.SpringTest;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.wechat.component.TokenManager;

public class SpringTest1 extends SpringTest{
	
	//ApplicationContextUtil可以参与单元测试
	@Test
	public void test1(){
		TokenManager tokenManager=ApplicationContextUtil.getBean("tokenManager",TokenManager.class);
		String token=tokenManager.getAccessToken(100001).getAccessToken();
		System.out.println(token);
	}

}
