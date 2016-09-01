package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.AccountValue;
import com.xiaoshabao.wechat.entity.AccountEntity;

public interface AccountDao {
	
	public AccountEntity getAccountById(Integer accountId);
	/**
	 * 获得帐号信息-根据权限
	 * @param priFrame
	 * @return
	 */
	public List<AccountValue> getAccountValues(@Param("priFrame")String priFrame);
	
	/**
	 * 获得帐号信息-根据帐号id
	 * @param accountId
	 * @return
	 */
	public List<AccountValue> getAccountValuesById(Integer accountId);
}
