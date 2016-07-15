package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.entity.AccountEntity;

public interface AccountWxDao {
	
	public AccountEntity getAccountById(Integer accountId);

}
