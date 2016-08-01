package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.BargainJoinEntity;
import com.xiaoshabao.wechat.entity.SubscriberEntity;

/**
 * 参加砍价活动人的 详细信息
 */
public class BargainJoinResult extends SubscriberEntity {
	/**
	 * 1表示已经参加活动，0为参加活动
	 */
	private Integer status;

	private BargainJoinEntity joinUser;

	public BargainJoinEntity getJoinUser() {
		return joinUser;
	}

	public void setJoinUser(BargainJoinEntity joinUser) {
		this.joinUser = joinUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
