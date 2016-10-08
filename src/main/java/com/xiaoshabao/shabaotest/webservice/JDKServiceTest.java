package com.xiaoshabao.shabaotest.webservice;

public class JDKServiceTest {
	public static void main(String[] args) {
		System.out.println("web service start");
		JDKISerivce implementor=new JDKISerivceImpl();
		String address="http://192.168.1.102/helloWorld";
		// Endpoint.publish(address, implementor); // jdk实现 暴露webservice接口
		/*
		JaxWsServerFactoryBean factoryBean=new JaxWsServerFactoryBean();
		factoryBean.setAddress(address); // 设置暴露地址
		factoryBean.setServiceClass(JDKISerivce.class); // 接口类
		factoryBean.setServiceBean(implementor); // 设置实现类
		factoryBean.create(); // 创建webservice接口
		*/
		System.out.println("web service started");
	}
}
