package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test1 {
	protected Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test(){
		Map<String,String> params=new HashMap<String, String>();
		String name=params.get("name");
		System.out.println(name==null?"是空值":name);
	}

}
