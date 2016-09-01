package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.webframe.entity.PosterEntity;
import com.xiaoshabao.wechat.entity.BargainEntity;

/**
 * 砍价详细信息
 */
public class BargainInfoDto extends BargainEntity{
	/** 应用名 **/
	private String appName;
	/** 微信帐号信息 */
	private List<AccountValue> accounts;
	/** 海报信息（详情信息中用）*/
	private PosterEntity poster;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<AccountValue> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountValue> accounts) {
		this.accounts = accounts;
	}

	public PosterEntity getPoster() {
		return poster;
	}

	public void setPoster(PosterEntity poster) {
		this.poster = poster;
	}
}
