package com.xiaoshabao.wechat.api.wxmedia.result;


/**
 * 素材总数
 */
public class MediaCountResult {
	/**
	 * 语音
	 */
	private String voice_count;
	/**
	 * 视频
	 */
	private String video_count;
	/**
	 * 图片
	 */
	private String image_count;
	/***
	 * 图文
	 */
	private String news_count;
	public String getVoice_count() {
		return voice_count;
	}
	public void setVoice_count(String voice_count) {
		this.voice_count = voice_count;
	}
	public String getVideo_count() {
		return video_count;
	}
	public void setVideo_count(String video_count) {
		this.video_count = video_count;
	}
	public String getImage_count() {
		return image_count;
	}
	public void setImage_count(String image_count) {
		this.image_count = image_count;
	}
	public String getNews_count() {
		return news_count;
	}
	public void setNews_count(String news_count) {
		this.news_count = news_count;
	}
	
}
