package com.xiaoshabao.wechat.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 砍价-参与表
 */
public class BargainJoinEntity {
	/** 砍价id */
	private Integer joinId;
	/** 砍价id */
	private Integer bargainId;
	/** 微信id ***/
	private String openid;
	/** 状态，1-砍价中，2-兑奖 */
	private Integer status;
	/** 当前价格 */
	private BigDecimal price;
	/** 砍掉价钱 */
	private BigDecimal bargainPrice;
	/** 当前砍价的次数 */
	private Integer bargainNum;
	/** 兑奖时对应的二维码id **/
	private Integer qrcodeId;
	/** 参加活动时间 **/
	private Date createTime;
	/** 更新时间 **/
	private Date updateTime;
	
	public Integer getJoinId() {
		return joinId;
	}
	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}
	public Integer getBargainId() {
		return bargainId;
	}
	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getBargainPrice() {
		return bargainPrice;
	}
	public void setBargainPrice(BigDecimal bargainPrice) {
		this.bargainPrice = bargainPrice;
	}
	public Integer getBargainNum() {
		return bargainNum;
	}
	public void setBargainNum(Integer bargainNum) {
		this.bargainNum = bargainNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getQrcodeId() {
		return qrcodeId;
	}
	public void setQrcodeId(Integer qrcodeId) {
		this.qrcodeId = qrcodeId;
	}
 	
}
