package com.xiaoshabao.wechat.api.wxmedia;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.wxbase.TokenAPITest;
import com.xiaoshabao.wechat.api.wxmedia.MediaAPI;
import com.xiaoshabao.wechat.api.wxmedia.MediaType;
import com.xiaoshabao.wechat.api.wxmedia.model.Article;
import com.xiaoshabao.wechat.api.wxmedia.result.DwonloadResult;
import com.xiaoshabao.wechat.api.wxmedia.result.MediaCountResult;
import com.xiaoshabao.wechat.api.wxmedia.result.NewsMediaList;
import com.xiaoshabao.wechat.api.wxmedia.result.NewsResult;
import com.xiaoshabao.wechat.api.wxmedia.result.OthersMediaList;
import com.xiaoshabao.wechat.api.wxmedia.result.UploadMediaResult;
import com.xiaoshabao.wechat.api.wxmedia.result.UploadTempMediaResult;


public class MediaAPITest {
	
	private String accessToken=TokenAPITest.accessToken;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	/**
	 *  微信--上传临时文件接口
	 * @throws Exception 
	 */
	@Test
	public void testUploadTempMedia() throws Exception {
		try {
			UploadTempMediaResult bean=MediaAPI.uploadTempMedia(accessToken, MediaType.IMAGE, "E:\\test\\img01.jpg");
			System.out.println("media_id:"+bean.getMedia_id());
			System.out.println("created_at:"+bean.getCreated_at());
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	/**
	 *  微信--下载临时文件接口
	 * @throws Exception 
	 */
	@Test
	public void testDownTempMedia() throws Exception {
		try {
			DwonloadResult bean=MediaAPI.downTempMedia(accessToken, "h52d9ii11NC1O9ri6hlFuVW5BSeFDypBsPdl8D8PdAgRg_UCLCUZpUyEugbnumNI", "E:\\test");
			System.out.println("FileName:"+bean.getFileName());
			System.out.println("Filepath:"+bean.getFilePath());
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	/**
	 *  永久素材上传文件接口
	 * @throws Exception 
	 */
	@Test
	public void testUploadMedia() throws Exception {
		try {
			UploadMediaResult result=MediaAPI.uploadMedia(accessToken, MediaType.IMAGE, "E:\\test\\11.png");
			System.out.println("media_id:"+result.getMedia_id());
			System.out.println("返回url:"+result.getUrl());
			//ADqYlnhfHd--TyuNuo0S1SlEARy9hns-9djP8hB1Fmc
			//https://mmbiz.qlogo.cn/mmbiz/BibYy0opVNUpBx21a13hKe5RwlWd6OicQ5tXpgfu4nkPnD48n6Py45AMpRDIfmgnebnap0ZV6ibkY97oaSfsOJicPA/0?wx_fmt=jpeg
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	/**
	 *  上传永久图文-图片接口
	 *  <p>用于永久图文消息内容等</p>
	 * @throws Exception 
	 */
	@Test
	public void testUploadNewsImg() throws Exception {
		try {
			 String url=MediaAPI.uploadNewsImg(accessToken,"E:\\test\\img01.jpg");
			System.out.println("图片地址为:"+url);
			// http://mmbiz.qpic.cn/mmbiz/BibYy0opVNUoJxJXdQf9b51YFJxv2JEradEbQa5DtuMJv2ms3e4HAB9APsNsmQ8hicEfibfXGCibCok8NLiaLDE5t2A/0
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	/**
	 * 上传永久图文消息素材
	 * @throws Exception
	 */
	@Test
	public void testUploadNews() throws Exception {
		try {
			List<Article> articles=new ArrayList<Article>();
			Article a1=new Article();
			a1.setTitle("测试");
			a1.setThumb_media_id("77f1UYMo_5pyjMO9MxLm2YijLpbSoU67JaLfrxi435g");
			a1.setAuthor("manxx");
			a1.setDigest("摘要信息测试");
			a1.setShow_cover_pic("1");
			a1.setContent("测试内容");
			a1.setContent_source_url("hh");
			articles.add(a1);
			String media_id=MediaAPI.uploadNews(accessToken, articles);
			System.out.println("图文素材media_id:"+media_id);
			//ZN4l8BsTy1QPTKABBI2PzWN_B8Icx30a5-yz0UaxdMc
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	//获取永久图文素材
	@Test
	public void testDownloadNews() throws Exception {
		try {
			NewsResult bean=MediaAPI.downloadNews(accessToken, "ZN4l8BsTy1QPTKABBI2PzeJrPPPNrLByUEHnSyvMhEs");
			System.out.println("图文消息:"+JSON.toJSONString(bean));
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	//获取其他素材-不包括视频
	@Test
	public void testDownMedia() throws Exception {
		try {
			DwonloadResult bean=MediaAPI.downloadMeida(accessToken, "uP_ofwCmwqX_5Fa4nHhtuvZolDV-cv_JWT5vPrMNubE", "E:\\test");
			System.out.println("FileName:"+bean.getFileName());
			System.out.println("Filepath:"+bean.getFilePath());
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	// 删除永久素材
	@Test
	public void testDelMedia() throws Exception {
		try {
			MediaAPI.delMeida(accessToken,
					"DarxYfQt2Ef4ZS-jgrbGzO9Mc8L_gqH0p1xvA_yPXAs");
			System.out.println("测试通过");
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	// 修改永久图文素材
	@Test
	public void testUpdateNewsMeida() throws Exception {
		try {
			Article a1=new Article();
			a1.setTitle("测试111");
			a1.setThumb_media_id("uP_ofwCmwqX_5Fa4nHhtureL5r2t7Z2Jmw2TADANISs");
			a1.setAuthor("manxx");
			a1.setDigest("摘要信息测试");
			a1.setShow_cover_pic("1");
			a1.setContent("测试内容");
			a1.setContent_source_url("hh");
			MediaAPI.updateNewsMeida(accessToken,"ZN4l8BsTy1QPTKABBI2PzeJrPPPNrLByUEHnSyvMhEs",0 , a1);
			System.out.println("测试通过");
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	// 获得永久素材总数
	@Test
	public void testGetMediaCount() throws Exception {
		try {
			MediaCountResult result=MediaAPI.getMediaCount(accessToken);
			System.out.println("结果:"+JSON.toJSONString(result));
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	// 获得图文素材列表
	@Test
	public void testGetMediaListByNews() throws Exception {
		try {
			NewsMediaList result = MediaAPI.getMediaListByNews(accessToken, 0, 20);
			System.out.println("结果:" + JSON.toJSONString(result));
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	// 获得其他素材列表
	@Test
	public void testGetMediaListByOthers() throws Exception {
		try {
			OthersMediaList result = MediaAPI.getMediaListByOthers(accessToken,MediaType.IMAGE, 0,20);
			System.out.println("结果:" + JSON.toJSONString(result));
		} catch (WexinReqException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	

}
