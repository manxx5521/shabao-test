package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.BargainJoinInfo;
import com.xiaoshabao.wechat.dto.BargainJoinResult;
import com.xiaoshabao.wechat.dto.BargainRankingDto;
import com.xiaoshabao.wechat.entity.BargainJoinEntity;

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
	public BargainJoinResult getBargainJoinResult(@Param("bargainId")Integer bargainId,@Param("openid")String openid);
	
	/**
	 *  获得表信息
	 * @param bargainId
	 * @param openid
	 * @return
	 */
	public BargainJoinEntity getBargainJoin(@Param("bargainId")Integer bargainId,@Param("openid")String openid);
	/**
	 * 砍价排行榜
	 * @param bargainId
	 * @return
	 */
	public List<BargainRankingDto> getBargainRanking(@Param("bargainId")Integer bargainId);
	/**
	 * 插入参加砍价活动人信息
	 * @param bargainJoin
	 * @return
	 */
	public int insertBargainJoin(BargainJoinEntity bargainJoin);
	
	/**
	 * 获得砍价参加人详细信息
	 * @param joinId
	 * @return
	 */
	public BargainJoinInfo getBargainJoinByJoinId(Integer joinId);
	
	/**
	 * 添加砍价价格
	 * @return
	 */
	public int updateBargainInfo(@Param("price")int price,@Param("joinId")Integer joinId);
	
	
}
