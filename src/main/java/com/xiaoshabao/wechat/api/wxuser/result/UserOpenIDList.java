package com.xiaoshabao.wechat.api.wxuser.result;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 获取用户openID列表
 */
public class UserOpenIDList {
	/**
	 * 关注该公众账号的总用户数
	 */
	private Integer total;
	/**
	 * 拉取的OPENID个数，最大值为10000
	 */
	private Integer count;
	
	/**
	 * 拉取列表的最后一个用户的OPENID
	 */
	private String next_openid;
	
	private List<String> openidList=new ArrayList<String>();
	
	@JSONField(name="openid")
	public List<String> getOpenidList() {
		return openidList;
	}
	
	@JSONField(name="openid")
	public void setOpenidList(List<String> openidList) {
		this.openidList = openidList;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
}
