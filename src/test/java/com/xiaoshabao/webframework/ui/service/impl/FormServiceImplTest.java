package com.xiaoshabao.webframework.ui.service.impl;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import util.SpringTest;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillViewData;
import com.xiaoshabao.webframework.ui.service.FormService;

public class FormServiceImplTest extends SpringTest{
	@Resource(name="formServiceImpl")
	FormService  formService;
	
	@Test
	public void testGetList() {
		try {
			BillListData billList=this.formService.getList("demo0001", new HashMap<String, Object>());
			System.out.println(billList.getTemplateHtml());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	
	@Test
	public void testQueryList() {
		try {
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("draw", 0);
			params.put("start", 0);
			params.put("length", 10);
			AjaxResult result=this.formService.queryList("dlist001", params);
			System.out.println(result.isSuccess());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	
	@Test
	public void testGetTemplateData() {
		try {
			String html=this.formService.getTemplateData("test000001").getHtml();
			System.out.println(html);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	@Test
	public void testGetView() {
		try {
			BillViewData viewData=this.formService.getView("demo0001", new HashMap<String, Object>());
			System.out.println(viewData.getTemplateHtml());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	

}
