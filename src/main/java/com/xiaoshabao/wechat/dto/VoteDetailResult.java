package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.webframework.dto.PosterDto;
import com.xiaoshabao.wechat.entity.VoteEntity;
import com.xiaoshabao.wechat.entity.VoteImageEntity;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;

/**
 * 投票选手详情
 */
public class VoteDetailResult extends VotePlayerEntity {
	/**
	 * 投票内容
	 */
	private VoteEntity vote;
	/**
	 * 照片列表
	 */
	private List<VoteImageEntity> imgList;
	/**
	 * 海报
	 */
	private List<PosterDto> posters;

	public VoteEntity getVote() {
		return vote;
	}

	public void setVote(VoteEntity vote) {
		this.vote = vote;
	}

	public List<PosterDto> getPosters() {
		return posters;
	}

	public void setPosters(List<PosterDto> posters) {
		this.posters = posters;
	}

	public List<VoteImageEntity> getImgList() {
		return imgList;
	}

	public void setImgList(List<VoteImageEntity> imgList) {
		this.imgList = imgList;
	}
}
