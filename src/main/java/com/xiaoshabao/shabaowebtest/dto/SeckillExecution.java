package com.xiaoshabao.shabaowebtest.dto;

import com.xiaoshabao.shabaowebtest.entity.SeckillSuccess;
import com.xiaoshabao.shabaowebtest.enums.SeckillStatEnum;

/**
 * 秒杀执行后的结构
 */
public class SeckillExecution {
	private long seckillId;
	// 秒杀状态 1成功
	private int state;
	// 秒杀状态信息
	private String stateInfo;
	// 秒杀对象
	private SeckillSuccess seckillSuccess;

	public SeckillExecution() {
	}

	public SeckillExecution(long seckillId, SeckillStatEnum statEnum, 
			SeckillSuccess seckillSuccess) {
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.seckillSuccess = seckillSuccess;
	}

	public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}

	/**
	 * 获得 seckillId
	 * 
	 * @return the seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}

	/**
	 * 设置 seckillId
	 * 
	 * @param seckillId
	 *            the seckillId to set
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * 获得 state
	 * 
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * 设置 state
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * 获得 stateInfo
	 * 
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * 设置 stateInfo
	 * 
	 * @param stateInfo
	 *            the stateInfo to set
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	/**
	 * 获得 seckillSuccess
	 * 
	 * @return the seckillSuccess
	 */
	public SeckillSuccess getSeckillSuccess() {
		return seckillSuccess;
	}

	/**
	 * 设置 seckillSuccess
	 * 
	 * @param seckillSuccess
	 *            the seckillSuccess to set
	 */
	public void setSeckillSuccess(SeckillSuccess seckillSuccess) {
		this.seckillSuccess = seckillSuccess;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", seckillSuccess="
				+ seckillSuccess + "]";
	}
}
