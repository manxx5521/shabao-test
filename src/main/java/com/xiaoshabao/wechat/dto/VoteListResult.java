package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.wechat.entity.VoteEntity;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;

/**
 * 投票列表
 * <p>继承字投票实体</p>
 */
public class VoteListResult extends VoteEntity{
	/**
	 * 选手人数
	 */
	private int userNum;
	/**
	 * 投票数
	 */
	private int voteNum;
	/**
	 * 访问数
	 */
	private int accessNum;
	/**
	 * 返回选手列表信息
	 */
	private List<VotePlayerEntity> list;
	/**
	 * 海报
	 */
	private List<String> imgList;
	
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}
	public int getAccessNum() {
		return accessNum;
	}
	public void setAccessNum(int accessNum) {
		this.accessNum = accessNum;
	}
	public VoteListResult() {
		super();
	}
	public VoteListResult(VoteEntity vote) {
		super();
	}

	public List<VotePlayerEntity> getList() {
		return list;
	}

	public void setList(List<VotePlayerEntity> list) {
		this.list = list;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	
}
