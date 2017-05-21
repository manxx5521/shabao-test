package com.xiaoshabao.webframework.component;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TemplateEngineTest {
	
	/**
	 * 示例模版 examplet.ftl
	 */
	@Test
	public void test() {
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			FreemarkerUser user=new FreemarkerUser();//内部类不行，必须public类
			user.setName("老高");
			user.setAge(40);
			params.put("user", user);
			
			params.put("booleanStr", true);
			params.put("nowDate", new Date());
			params.put("htmlContent", "<font color='red'>我是红色字</font>");
			
			//列表
			List<String> list=new ArrayList<String>();
			list.add("java");
			list.add("html");
			list.add("javascript");
			params.put("dataList", list);
			
			//map
			Map<String,Object> dataMap=new HashMap<String,Object>();
			dataMap.put("java", "你好java");
			dataMap.put("javascript", "你好javascript");
			params.put("dataMap", dataMap);
			
			//添加自定义函数sort_int
			params.put("sort_int", new FreemarkerSortMethod());
			
			//设置自定义指令
			TemplateEngine.setSharedVariable("role", new FreemarkerRoleDirectiveModel());
			
			String result=TemplateEngine.renderTemplate("examplet.ftl", params);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	
	
}


