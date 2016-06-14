package com.xiaoshabao.wechat.api.wxuser;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.xiaoshabao.wechat.api.wxbase.TokenAPITest;
import com.xiaoshabao.wechat.api.wxuser.UserAPI;
import com.xiaoshabao.wechat.api.wxuser.model.UserInfoGetBean;
import com.xiaoshabao.wechat.api.wxuser.result.UserBaseInfo;
import com.xiaoshabao.wechat.api.wxuser.result.UserOpenIDList;

public class UserAPITest {
	private String accessToken=TokenAPITest.accessToken;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//getUserOpenIdList(String accessToken)
	//获得用户列表-拉取10000条数据
	@Test
	public void testGetUserOpenIdListString() throws Exception {
		try {
			UserOpenIDList result=UserAPI.getUserOpenIdList(accessToken);
			System.out.println("测试通过");
			System.out.println("总计："+result.getTotal());
			System.out.println("第一个openid："+result.getOpenidList().get(0));
			System.out.println("返回json："+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	//拉取所有用户openId列表
	//UserOpenIDList getUserOpenIdList(String accessToken,String next_openid)
	@Test
	public void testGetUserOpenIdListStringString() throws Exception {
		try {
			UserOpenIDList result=UserAPI.getUserOpenIdList(accessToken,"oGMKNwnS1h3bcyKUu2WQsrFFNeZk");
			System.out.println("测试通过");
			System.out.println("总计："+result.getTotal());
			if(result.getCount()>0){
				System.out.println("第一个openid："+result.getOpenidList().get(0));
			}
			System.out.println("返回json："+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// getUserOpenIdListAll(String accessToken)
	// 获得用户列表
	@Test
	public void testGetUserOpenIdListAllString() throws Exception {
		try {
			UserOpenIDList result = UserAPI.getUserOpenIdListAll(accessToken);
			System.out.println("测试通过");
			System.out.println("总计：" + result.getTotal());
			System.out.println("第一个openid：" + result.getOpenidList().get(0));
			System.out.println("返回json：" + JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	// 拉取所有用户openId列表
	// UserOpenIDList getUserOpenIdListAll(String accessToken,String next_openid)
	@Test
	public void testGetUserOpenIdListAllStringString() throws Exception {
		try {
			UserOpenIDList result = UserAPI.getUserOpenIdListAll(accessToken,"oGMKNwnS1h3bcyKUu2WQsrFFNeZk");
			System.out.println("测试通过");
			System.out.println("总计：" + result.getTotal());
			if (result.getCount() > 0) {
				System.out.println("第一个openid"+result.getOpenidList().get(0));
			}
			System.out.println("返回json"+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//设置用户备注
	@Test
	public void testSetUserRemark() throws Exception {
		try {
			UserAPI.setUserRemark(accessToken,"oGMKNwnS1h3bcyKUu2WQsrFFNeZk","自己");
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 获得用户基本信息
	@Test
	public void testGetUserBaseInfo() throws Exception {
		try {
			UserBaseInfo result=UserAPI.getUserBaseInfo(accessToken, "oGMKNwnS1h3bcyKUu2WQsrFFNeZk","zh_CN");
			System.out.println("测试通过");
			System.out.println("JSON内容："+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 获得用户基本信息
	@Test
	public void testGetUserBaseInfoList() throws Exception {
		try {
			List<UserInfoGetBean> list=new ArrayList<UserInfoGetBean>();
			UserInfoGetBean b1=new UserInfoGetBean("oGMKNwnS1h3bcyKUu2WQsrFFNeZk");
			UserInfoGetBean b2=new UserInfoGetBean();
			b2.setLang("zh_CN");
			b2.setOpenid("oGMKNwjuA6roAPJajXrkMPlA9ZG0");
			list.add(b1);
			list.add(b2);
			
			List<UserBaseInfo> result = UserAPI.getUserBaseInfoList(accessToken,list);
			System.out.println("测试通过");
			System.out.println("JSON内容：" + JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
