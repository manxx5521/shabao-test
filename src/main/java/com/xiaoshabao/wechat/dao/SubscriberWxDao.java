package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.entity.SubscriberWxEntity;

/**
 * 订阅数据操作
 */
public interface SubscriberWxDao {
	/**
	 * 获得订阅信息
	 * @param accountId
	 * @param openid
	 * @return
	 */
	public List<SubscriberWxEntity> getSubscriberById(@Param("accountId")Integer accountId,@Param("openid") String openid);
	
	/**
	 * 插入记录
	 * @param wxSubscriber
	 * @return
	 */
	public int insert(SubscriberWxEntity wxSubscriber);
	
	/**
	 * 修改记录
	 * @param wxSubscriber
	 * @return
	 */
	public int update(SubscriberWxEntity wxSubscriber);
}
