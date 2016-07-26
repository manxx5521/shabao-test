package com.xiaoshabao.webframe.entity;
/**
 * 海报
 */
public class PosterEntity {
	/** 唯一id */
	private Long posterId;
	/** 帐号类型 1、微信 */
	private Integer accountType;
	/** 对应的帐号值 */
	private String accountId;
	/** 类型 比如投票 */
	private String type;
	/** 类型下id */
	private String TypeId;
	/** 图片 */
	private String image;
	/** 可能存在的标题 */
	private String title;
	/**排序*/
	private Integer orderNo;

	
	public PosterEntity() {
		super();
	}

	public PosterEntity(Integer accountType, String type,
			String typeId) {
		this.accountType = accountType;
		this.type = type;
		TypeId = typeId;
	}
	public PosterEntity(Integer accountType, String accountId, String type,
			String typeId) {
		this.accountType = accountType;
		this.accountId = accountId;
		this.type = type;
		TypeId = typeId;
	}



	public Long getPosterId() {
		return posterId;
	}

	public void setPosterId(Long posterId) {
		this.posterId = posterId;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

}
