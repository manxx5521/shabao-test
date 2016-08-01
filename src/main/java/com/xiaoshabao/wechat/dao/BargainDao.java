package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.dto.BargainResult;

/**
 * 砍价
 */
public interface BargainDao {
	/**
	 * 获得砍价信息
	 * @param bargainId
	 * @return
	 */
	public BargainResult getBargainResult(Integer bargainId);
}
