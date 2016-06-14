package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 关注取消实体类
 */
public class WXUserLogEntity {
	/**
	 * 索引
	 */
	private Integer index_id;
	/**
	 * 微信用户id
	 */
	private String open_id;
	/**
	 * 类型1关注、2取消关注
	 */
	private String type;
	/**
	 * 操作时间
	 */
	private Date update_time;

	public Integer getIndex_id() {
		return index_id;
	}

	public void setIndex_id(Integer index_id) {
		this.index_id = index_id;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
