package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.baseframe.bean.PagingParams;

/**
 * 投票分页参数
 */
public class VoteParams extends PagingParams {

	private Integer voteId;
	/**
	 * 搜索关键字
	 */
	private String keyword;
	/**
	 * 排序
	 */
	private String order;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
