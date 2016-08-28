package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.webframe.dto.PosterDto;
import com.xiaoshabao.wechat.entity.BargainEntity;

/**
 * 砍价信息返回
 */
public class BargainResult extends BargainEntity {
	/**
	 * 参加活动的id
	 */
	private Integer joinId;
	
	/**
	 * 个人信息
	 */
	private BargainJoinResult info;
	
	/**
	 * 帮我砍价列表
	 */
	private List<BargainUser> users;

	/**
	 * 海报
	 */
	private List<PosterDto> posters;
	/**
	 * 排行榜
	 */
	private List<BargainRankingDto> rankingList;
	/**
	 * 1-砍过
	 */
	private Integer bargainStatus;
	/**
	 * 类型1自己砍价，2分享砍价
	 */
	private String type;
	
	public List<PosterDto> getPosters() {
		return posters;
	}

	public void setPosters(List<PosterDto> posters) {
		this.posters = posters;
	}

	public List<BargainUser> getUsers() {
		return users;
	}

	public void setUsers(List<BargainUser> users) {
		this.users = users;
	}
	
	
	public BargainJoinResult getInfo() {
		return info;
	}

	public void setInfo(BargainJoinResult info) {
		this.info = info;
	}

	public List<BargainRankingDto> getRankingList() {
		return rankingList;
	}

	public void setRankingList(List<BargainRankingDto> rankingList) {
		this.rankingList = rankingList;
	}

	public Integer getJoinId() {
		return joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

	public Integer getBargainStatus() {
		return bargainStatus;
	}

	public void setBargainStatus(Integer bargainStatus) {
		this.bargainStatus = bargainStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
