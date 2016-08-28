package com.xiaoshabao.wechat.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.ReturnMessageDto;
/**
 * 相应回复信息
 */
public interface ReturnMessageDao {
	/**
	 * 查询
	 * @param openid
	 * @param content
	 * @return
	 */
	public ReturnMessageDto getReturnMessageDto(@Param("openid")String openid,@Param("content")String content);

}
