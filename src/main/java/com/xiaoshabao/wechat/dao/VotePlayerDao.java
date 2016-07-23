package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.VoteDetailResult;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;

/**
 * 投票选手数据操作
 */
public interface VotePlayerDao {
	
	/**
	 * 查询选手列表
	 * @param voteId
	 * @return
	 */
	public List<VotePlayerEntity> getPlayerList(@Param("voteId") Integer voteId);
	
	/**
	 * 获得选手详细信息
	 * @param playerId
	 * @return
	 */
	public VoteDetailResult getPlayerDetail(Integer playerId);
	
	/**
	 * 排行榜信息
	 * @param voteId
	 */
	public List<VotePlayerEntity> getVoteRanking(@Param("voteId")Integer voteId,@Param("type")Integer type);
	/**
	 * 添加选手
	 * @param player
	 * @return
	 */
	public int insertPlayer(VotePlayerEntity player);
	/**
	 * 获得选手
	 * @param player
	 * @return
	 */
	public VotePlayerEntity getPlayer(Integer playerId);
	/**
	 * 获得最大选手编码
	 * @param voteId
	 * @return
	 */
	public int getMaxPlayerNum(Integer voteId);
	/**
	 * 给选手添加一票
	 * @param playerId
	 * @return
	 */
	public int addPlayerVote(Integer playerId);
}
