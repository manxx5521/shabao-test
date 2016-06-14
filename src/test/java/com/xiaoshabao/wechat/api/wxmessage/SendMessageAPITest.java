package com.xiaoshabao.wechat.api.wxmessage;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.xiaoshabao.wechat.api.wxbase.TokenAPITest;
import com.xiaoshabao.wechat.api.wxmedia.model.Article;
import com.xiaoshabao.wechat.api.wxmessage.SendMessageAPI;
import com.xiaoshabao.wechat.api.wxmessage.result.MessageUploadResult;
import com.xiaoshabao.wechat.api.wxmessage.result.NewsMessResult;

public class SendMessageAPITest {
	private String accessToken=TokenAPITest.accessToken;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUploadNews() throws Exception {
		try {
			List<Article> articles=new ArrayList<Article>();
			Article a1=new Article();
			a1.setAuthor("manxx");
			a1.setContent("测试内容111111");
			a1.setDigest("这是图文摘要");
			a1.setShow_cover_pic("1");
			a1.setThumb_media_id("h52d9ii11NC1O9ri6hlFuVW5BSeFDypBsPdl8D8PdAgRg_UCLCUZpUyEugbnumNI");
			a1.setTitle("这是一个标题");
			a1.setContent_source_url("www.baidu.com");
			articles.add(a1);
			MessageUploadResult result=SendMessageAPI.uploadNews(accessToken, articles);
			System.out.println("测试通过");
			System.out.println("JSON内容：  "+JSON.toJSONString(result));
			//D7Pn1QsJCIB7O6otmrdXpjZRV6dl7-gxCDGAfeMJ5RnAre2X-Zn3Ku61GRKWvWy4
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//群发图文
	@Test
	public void testSendNewsMessByGroup() throws Exception {
		try {
			NewsMessResult result=SendMessageAPI.sendNewsMessByGroup(accessToken, "ZN4l8BsTy1QPTKABBI2PzeJrPPPNrLByUEHnSyvMhEs", "102");
			System.out.println("测试通过");
			System.out.println("JSON内容："+JSON.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	// 群发图文
	@Test
	public void testSendTextMessByGroup() throws Exception {
		try {
			SendMessageAPI.sendMessTextByGroup(accessToken,"测试发送文本", "102");
			System.out.println("测试通过");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
