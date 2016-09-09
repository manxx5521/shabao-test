package com.xiaoshabao.wechat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.service.AccountWxService;

@Service("wxAccountService")
public class AccountWxServiceImpl extends AbstractServiceImpl implements
		AccountWxService {

	@Resource
	TokenManager tokenManager;

	

}