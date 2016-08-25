package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.entity.MediaNewsEntity;

/**
 * 图文消息
 */
public interface MediaNewsDao {
	/**
	 * 添加
	 * @param news
	 * @return
	 */
	public int insertMediaNews(MediaNewsEntity news);

}
