package com.xiaoshabao.wechat.api.wxmenu;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.xiaoshabao.wechat.api.wxbase.TokenAPITest;
import com.xiaoshabao.wechat.api.wxmenu.MenuAPI;
import com.xiaoshabao.wechat.api.wxmenu.MenuType;
import com.xiaoshabao.wechat.api.wxmenu.model.Button;
import com.xiaoshabao.wechat.api.wxmenu.model.CommonButton;
import com.xiaoshabao.wechat.api.wxmenu.model.ComplexButton;
import com.xiaoshabao.wechat.api.wxmenu.model.Menu;
import com.xiaoshabao.wechat.api.wxmenu.model.WeixinButton;
import com.xiaoshabao.wechat.api.wxmenu.model.config.CustomWeixinButtonConfig;

public class MenuAPITest {
	private String accessToken=TokenAPITest.accessToken;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	//创建菜单子类继承方式
	@Test
	public void testCreateMenu() throws Exception {
		try {
			CommonButton btn11 = new CommonButton();
			btn11.setName("投票测试");
			btn11.setType(MenuType.VIEW);
			btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07e34f9575809866&redirect_uri=http%3A%2F%2Fwechat.xiaoshabao.com%2Fwechat%2Fvote%2F10000001%2Flist&response_type=code&scope=snsapi_base&state=100001#wechat_redirect");
			CommonButton btn12=new CommonButton();
			btn12.setName("砍价测试");
			btn12.setType(MenuType.VIEW);
			btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07e34f9575809866&redirect_uri=http%3A%2F%2Fwechat.xiaoshabao.com%2Fwechat%2Fbargain%2F11221111%2Fbargain%3Ftype%3Dinfo&response_type=code&scope=snsapi_userinfo&state=100001#wechat_redirect");
			
			
			CommonButton btn21=new CommonButton();
			btn21.setName("砍价测试");
			btn21.setType(MenuType.VIEW);
			btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx07e34f9575809866&redirect_uri=http%3A%2F%2Fwechat.xiaoshabao.com%2Fwechat%2Fbargain%2F11221111%2Fbargain&response_type=code&scope=snsapi_userinfo&state=100001#wechat_redirect");
			CommonButton btn22=new CommonButton();
			btn22.setName("文章测试");
			btn22.setType(MenuType.VIEW);
			btn22.setUrl("http://mp.weixin.qq.com/s?__biz=MzI2NDA3MDE2OA==&mid=507443969&idx=1&sn=d0f5ba036d1b6ac17dcbb4a3406120d4#rd");
			
			//存放多级菜单
			ComplexButton mainBtn1 = new ComplexButton();
			mainBtn1.setName("活动");
			mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });
			ComplexButton mainBtn2 = new ComplexButton();
			mainBtn2.setName("内容");
			mainBtn2.setSub_button(new CommonButton[] {  btn22 });
			
			CommonButton btn3 = new CommonButton();
			btn3.setName("关注我");
			btn3.setType(MenuType.VIEW);
			btn3.setUrl("http://www.soso.com/");
			Menu menu=new Menu();
			menu.setButton(new Button[] {mainBtn1,mainBtn2,btn3});
			MenuAPI.createMenu(accessToken, menu);
			System.out.print("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	//创建菜单 列表方式
	@Test
	public void testCreateMenuList() throws Exception {
		try {
			List<WeixinButton> button=new ArrayList<WeixinButton>();
			WeixinButton btn1=new WeixinButton();
			btn1.setName("登录");
			btn1.setType(MenuType.VIEW);
			btn1.setUrl("http://www.soso.com/");
			
			WeixinButton btn2=new WeixinButton();
			List<WeixinButton> btn2List=new ArrayList<WeixinButton>();
			WeixinButton btn21=new WeixinButton();
			
			btn21.setName("测试二级标题");
			btn21.setType(MenuType.CLICK);
			btn21.setKey("menutitle21");
			WeixinButton btn22=new WeixinButton();
			btn22.setName("测试二级标题");
			btn22.setType(MenuType.CLICK);
			btn22.setKey("menutitle22");
			
			//生成第二个子菜单
			btn2.setName("测试");
			btn2List.add(btn21);
			btn2List.add(btn22);
			btn2.setSub_button(btn2List);
			
			WeixinButton btn3=new WeixinButton();
			btn3.setName("关注我");
			btn3.setType(MenuType.VIEW);
			btn3.setUrl("http://www.soso.com/");
			
			//将一级菜单放到button
			button.add(btn1);
			button.add(btn2);
			button.add(btn3);
			MenuAPI.createMenu(accessToken, button);
			System.out.print("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	//查询菜单
	@Test
	public void testGetMenu() throws Exception {
		try {
			List<WeixinButton> list=MenuAPI.getMenu(accessToken);
			System.out.println("第一个类型："+list.get(0).getType());
			System.out.println("测试通过");
			System.out.println("内容：  "+JSON.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	//删除菜单
	@Test
	public void testDeleteMenu() throws Exception {
		try {
			MenuAPI.deleteMenu(accessToken);
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	
	// 获取菜单配置
	@Test
	public void testGetMenuConfigure() throws Exception {
		try {
			CustomWeixinButtonConfig result=MenuAPI.getMenuConfigure(accessToken);
			System.out.println("测试通过");
			System.out.println("内容："+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

}
