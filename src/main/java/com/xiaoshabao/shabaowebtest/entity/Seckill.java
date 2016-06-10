package com.xiaoshabao.shabaowebtest.entity;

import java.util.Date;
/**
 * 秒杀信息
 */
public class Seckill {
	/**
	 * 
	 */
	private long seckillId;
	private String name;
	private int number;
	private Date startTime;
	private Date endTime;
	private Date createTime;
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
	 * 获得 name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置 name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获得 number
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * 设置 number
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * 获得 startTime
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置 startTime
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获得 endTime
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置 endTime
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	@Override
	public String toString() {
		return "SeckillEntity [seckillId=" + seckillId + ", name=" + name
				+ ", number=" + number + ", starTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime + "]";
	}

}
