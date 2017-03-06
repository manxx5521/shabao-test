package test;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class Test1 {
	@SuppressWarnings("unused")
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings("unused")
	@Test
	public void test(){
		//ceshi1
		Map<String,Object> params = JSON.parseObject("{'name':'wang','age':'12'}");
	}

}
