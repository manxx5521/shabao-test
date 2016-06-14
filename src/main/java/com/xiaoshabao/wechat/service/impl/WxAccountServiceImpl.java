package com.xiaoshabao.wechat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.wechat.service.WxAccountService;
import com.xiaoshabao.wechat.util.TokenManager;

@Service("wxAccountService")
public class WxAccountServiceImpl extends AbstractServiceImpl implements
		WxAccountService {

	@Resource
	TokenManager tokenManager;

	

}