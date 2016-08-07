package com.xiaoshabao.wechat.dto;

import java.util.Date;

import com.xiaoshabao.wechat.entity.BargainEntity;

/**
 * 砍价活动信息
 */
public class BargainDto extends BargainEntity {
	/**
	 * 数据库时间
	 */
	private Date sysdate;

	public Date getSysdate() {
		return sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}

}
