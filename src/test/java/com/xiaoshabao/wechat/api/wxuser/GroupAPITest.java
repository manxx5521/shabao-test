package com.xiaoshabao.wechat.api.wxuser;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.xiaoshabao.wechat.api.wxbase.TokenAPITest;
import com.xiaoshabao.wechat.api.wxuser.GroupAPI;
import com.xiaoshabao.wechat.api.wxuser.model.Group;
import com.xiaoshabao.wechat.api.wxuser.model.GroupCreate;

public class GroupAPITest {
	private String accessToken=TokenAPITest.accessToken;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//创建分组
	@Test
	public void testCreateGroup() throws Exception {
		try {
			GroupCreate result=GroupAPI.createGroup(accessToken, "自己");
			System.out.println("测试通过");
			System.out.println("分组名字："+result.getGroup().getName());
			System.out.println("分组id："+result.getGroup().getId());
			System.out.println("返回json："+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 获得分组信息
	@Test
	public void testGetGroup() throws Exception {
		try {
			List<Group> list= GroupAPI.getGroup(accessToken);
			System.out.println("测试通过");
			System.out.println("返回json：" + JSON.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 获得用户所在分组
	@Test
	public void testGetUserGroup() throws Exception {
		try {
			String groupid = GroupAPI.getUserGroup(accessToken,"oGMKNwnS1h3bcyKUu2WQsrFFNeZk");
			System.out.println("测试通过");
			System.out.println("返回groupid：" + groupid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 获得用户所在分组
	@Test
	public void testUpdateGroup() throws Exception {
		try {
			GroupAPI.updateGroup(accessToken,"100","修改分组");
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 移动用户分组
	@Test
	public void testMoveUserGroupByid() throws Exception {
		try {
			GroupAPI.moveUserGroup(accessToken, "oGMKNwnS1h3bcyKUu2WQsrFFNeZk", "102");
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	// 移动用户分组
	@Test
	public void testMoveUserGroup() throws Exception {
		try {
			List<String> list=new ArrayList<String>();
			list.add("oGMKNwnS1h3bcyKUu2WQsrFFNeZk");
			list.add("oGMKNwm9osU9blaemFC59HHYfdXs");
			GroupAPI.moveUserGroup(accessToken,list, "100");
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 删除分组
	@Test
	public void testDeleteGroup() throws Exception {
		try {
			GroupAPI.deleteGroup(accessToken, "101");
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
