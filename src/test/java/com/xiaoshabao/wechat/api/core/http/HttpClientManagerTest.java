package com.xiaoshabao.wechat.api.core.http;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpClientManagerTest {
	
	HttpClientManager http=HttpClientManager.getInstance();

	@Test
	public void testDoGetString() {
		String rs=http.doGet("http://weibo.com/p/1005052930285990/photos?from=page_100505&mod=TAB#place");
		System.out.println(rs);
	}

	@Test
	public void testDoGetHttpGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoGetSSLString() {
		String rs=http.doGetSSL("http://weibo.com/p/1005052930285990/photos?from=page_100505&mod=TAB#place");
		System.out.println(rs);
	}

	@Test
	public void testDoGetSSLStringMapOfStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFileGetSSLStringMapOfStringObjectString() {
		try {
			Map<String ,Object> params=new HashMap<String ,Object>();
			http.getFileGetSSL("http://wx.qlogo.cn/mmopen/OQKjftn2qHhibGwZQic3iazczIFLNP9qgVMZqmq195Y6iblnu8BJEbd1OpWVLU9XBSNsCK2JZhTRVOIaGQEjOCtyibKInUx2t1lUic/0", 
					params, "E://test");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testGetFileGetSSLStringMapOfStringObjectStringString() {
		try {
			HttpClientManager http=HttpClientManager.getInstance();
			Map<String ,Object> params=new HashMap<String ,Object>();
			http.getFileGetSSL("http://wx.qlogo.cn/mmopen/OQKjftn2qHhibGwZQic3iazczIFLNP9qgVMZqmq195Y6iblnu8BJEbd1OpWVLU9XBSNsCK2JZhTRVOIaGQEjOCtyibKInUx2t1lUic/0", 
					params, "E://test","aa.jpg");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testDoPostString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostStringMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostStringMapOfStringObjectListOfFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostHttpPost() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostSSLString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostSSLStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostSSLByJSON() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostSSLStringMapOfStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostSSLStringMapOfStringObjectListOfFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoPostSSLHttpPost() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFilePostSSLStringMapOfStringObjectString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFilePostSSLStringMapOfStringObjectStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDisposition() {
		fail("Not yet implemented");
	}

}
