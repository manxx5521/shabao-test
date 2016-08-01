package com.xiaoshabao.wechat.dao;

import java.util.List;

import com.xiaoshabao.wechat.dto.BargainUser;
/**
 * 砍价记录
 */
public interface BargainSuccessDao {
	/**
	 * 获得砍价人员列表
	 * @param bargainId
	 * @return
	 */
	public List<BargainUser> getBargainUser(Integer joinId);

}
