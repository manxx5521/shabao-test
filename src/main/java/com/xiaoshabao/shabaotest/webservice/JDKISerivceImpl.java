package com.xiaoshabao.shabaotest.webservice;

import javax.jws.WebService;

@WebService
public class JDKISerivceImpl implements JDKISerivce{
	public String say(String str) {
		return "Hello"+str;
	}
}
