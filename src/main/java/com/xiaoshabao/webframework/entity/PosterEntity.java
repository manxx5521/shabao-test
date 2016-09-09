package com.xiaoshabao.webframework.entity;

/**
 * 海报
 */
public class PosterEntity {
	/** 唯一id */
	private Long posterId;
	/** 类型 比如投票 */
	private String type;
	/** 类型下id */
	private String TypeId;
	/** 图片 */
	private String image;
	/** 可能存在的标题 */
	private String title;
	/** 可能存在的按钮 */
	private String button;
	/** 排序 */
	private Integer orderNo;

	public PosterEntity() {
	}

	public PosterEntity(String type, String typeId) {
		this.type = type;
		TypeId = typeId;
	}

	public Long getPosterId() {
		return posterId;
	}

	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeId() {
		return TypeId;
	}

	public void setTypeId(String typeId) {
		TypeId = typeId;
	}

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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

}
