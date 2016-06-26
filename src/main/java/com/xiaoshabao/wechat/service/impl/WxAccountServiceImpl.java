package com.xiaoshabao.wechat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.service.WxAccountService;

@Service("wxAccountService")
public class WxAccountServiceImpl extends AbstractServiceImpl implements
		WxAccountService {

	@Resource
	TokenManager tokenManager;

	

}