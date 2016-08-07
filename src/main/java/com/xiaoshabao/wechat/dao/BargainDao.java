package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.dto.BargainDto;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;

/**
 * 砍价
 */
public interface BargainDao {
	/**
	 * 获得砍价信息
	 */
	public BargainResult getBargainResult(Integer bargainId);
	/**
	 * 获得砍价信息
	 */
	public BargainResult getBargainResultByJoinId(Integer joinId);
	/**
	 * 获得砍价信息
	 */
	public BargainEntity getBargainById(Integer bargainId);
	
	/**
	 * 获得砍价信息，带附加信息，如数据库时间
	 */
	public BargainDto getBargainDtoById(Integer bargainId);
	/**
	 * 添加当前活动的总次数,砍价的次数
	 */
	public int addBargainNum(Integer bargainId);
	/**
	 * 添加当前活动的总次数，添加次数和参与活动人数
	 */
	public int addNumber(Integer bargainId);
}
