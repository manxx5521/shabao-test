package com.xiaoshabao.shabaowebtest.entity;

import java.util.Date;
/**
 * 秒杀结果实体
 */
public class SeckillSuccess {
	private long seckillId;
	private long userPhone;
	private short state;
	private Date createTime;
	
	private Seckill seckill;
	
	/**
	 * 获得 seckillId
	 * @return the seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}
	/**
	 * 设置 seckillId
	 * @param seckillId the seckillId to set
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	/**
	 * 获得 userPhone
	 * @return the userPhone
	 */
	public long getUserPhone() {
		return userPhone;
	}
	/**
	 * 设置 userPhone
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * 获得 state
	 * @return the state
	 */
	public short getState() {
		return state;
	}
	/**
	 * 设置 state
	 * @param state the state to set
	 */
	public void setState(short state) {
		this.state = state;
	}
	/**
	 * 获得 createTime
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 createTime
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获得 seckill
	 * @return the seckill
	 */
	public Seckill getSeckill() {
		return seckill;
	}
	/**
	 * 设置 seckill
	 * @param seckill the seckill to set
	 */
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeckillSucess [seckillId=" + seckillId + ", userPhone="
				+ userPhone + ", state=" + state + ", createTime=" + createTime
				+ "]";
	}
}
