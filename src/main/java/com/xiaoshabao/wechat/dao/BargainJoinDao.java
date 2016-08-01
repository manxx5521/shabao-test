package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.BargainJoinResult;
import com.xiaoshabao.wechat.dto.BargainRankingDto;

/**
 * 砍价
 */
public interface BargainJoinDao {
	/**
	 *  获得参加人员信息
	 * @param bargainId
	 * @param openid
	 * @return
	 */
	public BargainJoinResult getBargainJoin(@Param("bargainId")Integer bargainId,@Param("openid")String openid);
	/**
	 * 砍价排行榜
	 * @param bargainId
	 * @return
	 */
	public List<BargainRankingDto> getBargainRanking(@Param("bargainId")Integer bargainId);
}
