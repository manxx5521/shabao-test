package com.xiaoshabao.wechat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.component.ContextHolderSystem;
import com.xiaoshabao.system.entity.SessionUserInfo;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.wechat.api.wxuser.UserAPI;
import com.xiaoshabao.wechat.api.wxuser.result.UserOpenIDList;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.dao.SubscriberDao;
import com.xiaoshabao.wechat.dto.WechatUserDto;
import com.xiaoshabao.wechat.entity.SubscriberEntity;
import com.xiaoshabao.wechat.service.WechatUserService;
/**
 * 微信用户
 */
@Service("wechatUserServiceImpl")
public class WechatUserServiceImpl extends AbstractServiceImpl implements WechatUserService{
	@Autowired
	private SubscriberDao subscriberDao;
	@Resource(name="tokenManager")
	private TokenManager tokenManager;
	//获得用户列表
	@Override
	public List<WechatUserDto> getList(Integer accountId) {
		SessionUserInfo sessionInfo=ContextHolderSystem.getSeesionInfo();
		String priFrame=sessionInfo.getPriFrame();
		List<WechatUserDto> list=subscriberDao.getUserList(priFrame,accountId);
		return list;
	}
	//同步用户
	@Override
	@Transactional
	public AjaxResult syncUser(Integer accountId) {
		String accessToken =tokenManager.getAccessToken(accountId).getAccessToken();
		UserOpenIDList result=UserAPI.getUserOpenIdList(accessToken);
		List<String> openidList=result.getOpenidList();
		SubscriberEntity user=new SubscriberEntity();
		user.setAccountId(accountId);
		user.setType(1);
		int i=0;
		String lastOpenid="";
		if(!openidList.isEmpty()&&openidList.size()>0){
			for(String openid:openidList){
				user.setOpenid(openid);
				SubscriberEntity subscriberEntity=subscriberDao.getSubscriberById(openid);
				if(subscriberEntity==null){
					i=subscriberDao.insert(user);
					if(i<1){
						throw new ServiceException("数据更新失败");
					}
					lastOpenid=openid;
				}
			}
		}
		int total=result.getTotal();
		int count=result.getCount();
		while(total>count){
			result=UserAPI.getUserOpenIdList(accessToken,lastOpenid);
			if(!openidList.isEmpty()&&openidList.size()>0){
				for(String openid:openidList){
					user.setOpenid(openid);
					SubscriberEntity subscriberEntity=subscriberDao.getSubscriberById(openid);
					if(subscriberEntity==null){
						i=subscriberDao.insert(user);
						if(i<1){
							throw new ServiceException("数据更新失败");
						}
						lastOpenid=openid;
					}
				}
			}
			count=count+result.getCount();
		}
		return new AjaxResult(true, "同步成功");
	}

}
