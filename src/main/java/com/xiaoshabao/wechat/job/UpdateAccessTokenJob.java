package com.xiaoshabao.wechat.job;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.component.WechatConfig;
import com.xiaoshabao.wechat.dao.AccessTokenDao;
import com.xiaoshabao.wechat.dto.TokenUpdateDto;
import com.xiaoshabao.wechat.entity.AccessToken;

public class UpdateAccessTokenJob {
	private static Logger logger = LoggerFactory.getLogger(UpdateAccessTokenJob.class);
	@Autowired
	AccessTokenDao accessTokenDao;
	@Resource(name="tokenManager")
	TokenManager TokenManager;
	@Autowired
	WechatConfig wechatConfig;

	/**
	 * 定时更新微信token
	 */
	public void work() {
		if(wechatConfig.getTokenType()==2){
			List<TokenUpdateDto> list=accessTokenDao.getTokenUpdateDto();
			if(!list.isEmpty()){
				for(TokenUpdateDto result:list){
					this.updateToken(result);
				}
			}
		}
	}
	/**
	 * 更新token信息
	 * @param result
	 */
	private void updateToken (TokenUpdateDto result){
		try {
			//计算存在的token是否符合规则
			long time_now_long = new java.util.Date().getTime();
			//更新accessToken
			if(result.getTokenState()==1){
				TokenManager.updateAccessToken(result.getAccountId(), (AccessToken)result, time_now_long);
			}
			//更新jsToken
			if(result.getTokenState()==1){
				TokenManager.updateJSToken(result.getAccountId(), (AccessToken)result, time_now_long);
			}
		} catch (Exception e) {
			logger.error("调度任务更新Token失败，更新id->"+result.getAccountId());
			e.printStackTrace();
		}
		
	}
}
