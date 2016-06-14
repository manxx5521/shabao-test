package com.xiaoshabao.wechat.api.wxmedia.model;
/**
 * 图文消息-详细<br>
 * 素材上传处
 */
public class Article {
	
	/** 图文消息的标题 */
	private String title;
	/** 图文消息的封面图片素材id（必须是永久mediaID） */
	private String thumb_media_id;
	/** 图文消息的作者 */
	private String author;
	/** 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空 */
	private String digest;
	/** 是否显示封面，1为显示，0为不显示 */
	private String show_cover_pic;
	/** 图文消息页面的内容，支持HTML标签 */
	private String content;
	/** 在图文消息页面点击“阅读原文”后的页面 */
	private String content_source_url;
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	
	/**
	 * 图文消息的封面图片素材id/必须是永久mediaID
	 * @param thumb_media_id 
	 */
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	
	/**
	 * 图文消息的作者
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 图文消息的作者
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}
	/**
	 * 图文消息的标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent_source_url() {
		return content_source_url;
	}
	/**
	 * 在图文消息页面点击“阅读原文”后的页面 
	 * @param content_source_url
	 */
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String getContent() {
		return content;
	}
	/**
	 * 图文消息页面的内容，支持HTML标签
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空 
	 * @return
	 */
	public String getDigest() {
		return digest;
	}
	/**
	 * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 * @param digest
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShow_cover_pic() {
		return show_cover_pic;
	}
	/**
	 * 是否显示封面，1为显示，0为不显示
	 * @param show_cover_pic
	 */
	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}

	@Override
	public String toString() {
		return "WxArticle [thumb_media_id=" + thumb_media_id + ", author=" + author + ", title=" + title + ", content_source_url=" + content_source_url + ", content=" + content + ", digest=" + digest + ", show_cover_pic=" + show_cover_pic +  "]";
	}

	 

}
