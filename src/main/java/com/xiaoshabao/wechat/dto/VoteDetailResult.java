package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.wechat.entity.VoteEntity;
import com.xiaoshabao.wechat.entity.VoteImageEntity;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;
/**
 * 投票选手详情
 */
public class VoteDetailResult extends VotePlayerEntity{
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
	private List<String> posters;

	public VoteEntity getVote() {
		return vote;
	}

	public void setVote(VoteEntity vote) {
		this.vote = vote;
	}

	public List<String> getPosters() {
		return posters;
	}

	public void setPosters(List<String> posters) {
		this.posters = posters;
	}

	public List<VoteImageEntity> getImgList() {
		return imgList;
	}

	public void setImgList(List<VoteImageEntity> imgList) {
		this.imgList = imgList;
	}
}
