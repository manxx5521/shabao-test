package com.xiaoshabao.wechat.dao;

import java.util.List;

import com.xiaoshabao.wechat.entity.VoteImageEntity;

/**
 * 投票图片
 */
public interface VoteImageDao {
	
	/**
	 * 查询选手图片列表
	 * @param voteId
	 * @return
	 */
	public List<VoteImageEntity> getImageList(Integer playerId);
	
}
