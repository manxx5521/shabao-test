package com.xiaoshabao.webframework.component;

import static org.junit.Assert.*;

import java.util.HashMap;
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
			
			TemplateEngine.renderTemplate("examplet.ftl", params);
		} catch (Exception e) {
			// TODO: handle exception
		}
		fail("Not yet implemented");
	}

}
