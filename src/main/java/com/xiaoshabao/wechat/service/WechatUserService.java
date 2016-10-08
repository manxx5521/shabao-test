package com.xiaoshabao.wechat.service;

import java.util.List;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.WechatUserDto;
/**
 * 微信用户
 */
public interface WechatUserService extends AbstractService {
	/**
	 * 获得用户列表
	 * @return
	 */
	public List<WechatUserDto> getList(Integer accountId);
	/**
	 * 同步用户
	 * @param accountId
	 * @return
	 */
	public AjaxResult syncUser(Integer accountId);
}
