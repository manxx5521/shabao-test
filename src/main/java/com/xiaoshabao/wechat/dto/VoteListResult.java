package com.xiaoshabao.wechat.dto;

import java.util.List;

import com.xiaoshabao.baseframe.bean.PageValue;
import com.xiaoshabao.wechat.entity.VoteCountEntity;
import com.xiaoshabao.wechat.entity.VoteEntity;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;

/**
 * 投票列表
 * <p>继承字投票实体</p>
 */
public class VoteListResult extends VoteEntity{

	/**
	 * 统计信息
	 */
	private VoteCountEntity count;
	/**
	 * 返回选手列表信息
	 */
	private List<VotePlayerEntity> list;
	/**
	 * 分页信息
	 */
	private PageValue<VotePlayerEntity> page;
	/**
	 * 海报
	 */
	private List<String> imgList;
	
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
	public VoteCountEntity getCount() {
		return count;
	}
	public void setCount(VoteCountEntity count) {
		this.count = count;
	}

	public PageValue<VotePlayerEntity> getPage() {
		return page;
	}

	public void setPage(PageValue<VotePlayerEntity> page) {
		this.page = page;
	}
}
