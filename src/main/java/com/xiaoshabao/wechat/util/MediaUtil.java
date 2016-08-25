package com.xiaoshabao.wechat.util;

import com.xiaoshabao.wechat.api.wxmedia.MediaAPI;
import com.xiaoshabao.wechat.api.wxmedia.model.Article;
import com.xiaoshabao.wechat.api.wxmedia.result.ArticleOneResult;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;

/**
 * 媒体工具
 */
public class MediaUtil {
	/**
	 * 上传永久图文消息-单个图文
	 * @param news
	 * @param token
	 * @return 微信端的URL
	 */
	public static ArticleOneResult uploadNews(MediaNewsEntity news,String token){
		Article article=new Article();
		article.setTitle(news.getTitle());
		article.setThumb_media_id(news.getThumbMediaId());
		article.setAuthor(news.getAuthor());
		article.setDigest(news.getDigest());
		article.setShow_cover_pic(news.getShowCoverPic());
		article.setContent(news.getContent());
		article.setContent_source_url(news.getContentSourceUrl());
		ArticleOneResult result=MediaAPI.uploadNews(token, article);
		return result;
	}

}
