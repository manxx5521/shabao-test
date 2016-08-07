package com.xiaoshabao.wechat.dto;

import java.util.Date;

import com.xiaoshabao.wechat.entity.BargainEntity;
import com.xiaoshabao.wechat.entity.BargainJoinEntity;

/**
 * 砍价信息
 */
public class BargainJoinInfo extends BargainJoinEntity {
	private BargainEntity bargain;
	/**
	 * 数据库时间
	 */
	private Date sysdate;

	public BargainEntity getBargain() {
		return bargain;
	}

	public void setBargain(BargainEntity bargain) {
		this.bargain = bargain;
	}

	public Date getSysdate() {
		return sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}

}
