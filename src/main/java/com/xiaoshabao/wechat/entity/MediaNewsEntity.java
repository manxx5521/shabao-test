package com.xiaoshabao.wechat.entity;

import java.util.Date;
/**
 * 图文媒体
 */
public class MediaNewsEntity {
	private String mediaId;
	private Integer accountId;
	/** 1.图文素材 */
	private Integer type;
	/** 标题 */
	private String title;
	/** 封面 */
	private String thumbMediaId;
	/** 作者 */
	private  String author;
	/** 摘要 */
	private String digest;
	/** 是否显示封面 1.显示 */
	private String showCoverPic;
	/** 内容 */
	private String content;
	/** 阅读原文的URL */
	private String contentSourceUrl;
	private Date updateTime;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getShowCoverPic() {
		return showCoverPic;
	}
	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentSourceUrl() {
		return contentSourceUrl;
	}
	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
