package com.xiaoshabao.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.component.ContextHolderSystem;
import com.xiaoshabao.system.entity.SessionUserInfo;
import com.xiaoshabao.wechat.dao.SubscriberDao;
import com.xiaoshabao.wechat.dto.WechatUserDto;
import com.xiaoshabao.wechat.service.WechatUserService;
/**
 * 微信用户
 */
@Service("wechatUserServiceImpl")
public class WechatUserServiceImpl extends AbstractServiceImpl implements WechatUserService{
	@Autowired
	private SubscriberDao subscriberDao;
	//获得用户列表
	@Override
	public List<WechatUserDto> getList() {
		SessionUserInfo sessionInfo=ContextHolderSystem.getSeesionInfo();
		String priFrame=sessionInfo.getPriFrame();
		List<WechatUserDto> list=subscriberDao.getUserList(priFrame);
		return list;
	}

}
