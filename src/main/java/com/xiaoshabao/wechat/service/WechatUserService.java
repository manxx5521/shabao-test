package com.xiaoshabao.wechat.service;

import java.util.List;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.wechat.dto.WechatUserDto;
/**
 * 微信用户
 */
public interface WechatUserService extends AbstractService {
	/**
	 * 获得用户列表
	 * @return
	 */
	public List<WechatUserDto> getList();
	
}
