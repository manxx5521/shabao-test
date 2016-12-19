package com.xiaoshabao.system.shiro;

import static org.junit.Assert.fail;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class PasswordTest {
	/**
	 * 生成密码
	 */
	@Test
	public void test() {
		//登录算法
		String algorithmName = "md5";  
		String username = "root2";  
		String password = "123456";  
		String salt1 = username;  
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();  
		int hashIterations = 2;  
		  
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);  
		String encodedPassword = hash.toHex();  
		System.out.println("password:"+encodedPassword);
		System.out.println("salt:"+ salt2);
		fail("Not yet implemented");
	}

}
