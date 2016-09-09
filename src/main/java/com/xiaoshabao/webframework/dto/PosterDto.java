package com.xiaoshabao.webframework.dto;

/**
 * 海报
 */
public class PosterDto {
	/** 图片 */
	private String image;
	/** 可能存在的标题 */
	private String title;
	/** 可能存在的按钮 */
	private String button;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}
}
