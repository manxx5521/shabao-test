package com.xiaoshabao.baseframework.component;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import util.SpringTest;

public class JedisManagerTest extends SpringTest{
	@Resource(name="jedisManager")
	private JedisManager jedisManager;
	
	//测试连接额是否成功
	@Test
	public void testGetJedis() {
		try {
			Jedis jedis=jedisManager.getJedis();
			if(jedis==null){
				fail("未获取到jedis");
			}
			//查看服务是否运行
		    System.out.println("Server is running: "+jedis.ping());
		} catch (Exception e) {
			e.printStackTrace();
			fail("程序异常终止");
		}
	}
	
	//使用方法
	@Test
	public void useTest(){
		Jedis jedis=jedisManager.getJedis();
		
		//设置字符串
		jedis.set("love", "girls");
		System.out.println("字符串 love 数据："+jedis.get("love"));
		
		// 存储数据到列表中
		jedis.lpush("tutorial-list", "Redis");
		jedis.lpush("tutorial-list", "Mongodb");
		jedis.lpush("tutorial-list", "Mysql");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("tutorial-list", 0, 5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("List string in redis:: " + list.get(i));
		}
	}

	@Test
	public void testReturnResource() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValueByKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveValueByKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJedisPool() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetJedisPool() {
		fail("Not yet implemented");
	}

}
