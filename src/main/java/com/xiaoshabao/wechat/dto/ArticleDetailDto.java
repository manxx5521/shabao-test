package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.wechat.entity.ArticleEntity;

/**
 * 文章描述信息
 */
public class ArticleDetailDto extends ArticleEntity {
	/**
	 * 微信帐号信息
	 */
	List<AccountValue> accounts;

	public List<AccountValue> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountValue> accounts) {
		this.accounts = accounts;
	}

}
