package com.xiaoshabao.wechat.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.wechat.component.ContextHolderWechat;
import com.xiaoshabao.wechat.component.PosterWechatComponent;
import com.xiaoshabao.wechat.dao.BargainDao;
import com.xiaoshabao.wechat.dao.BargainJoinDao;
import com.xiaoshabao.wechat.dao.BargainSuccessDao;
import com.xiaoshabao.wechat.dto.BargainJoinResult;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.service.BargainService;

/**
 * 砍价
 */
@Service("BargainService")
public class BargainServiceImpl extends AbstractServiceImpl implements BargainService {
	@Autowired
	private BargainDao bargainDao;
	@Autowired
	private BargainSuccessDao successDao;
	@Resource(name="wechatPoster")
	private PosterWechatComponent posterComponent;
	@Autowired
	private BargainJoinDao bargainJoinDao;
	
	// 获得砍价信息
	@Override
	public BargainResult getBargainResult(Integer bargainId) {
		String openid=ContextHolderWechat.getOpenid();
		if(StringUtils.isEmpty(openid)){
			throw new ServiceException("自动登录失败，重新打开微信号或者是重新关注");
		}
		BargainResult result=bargainDao.getBargainResult(bargainId);
		if(result==null){
			throw new ServiceException("砍价活动不存在");
		}
		//获得参加砍价信息
		BargainJoinResult info=bargainJoinDao.getBargainJoin(bargainId, openid);
		if(info==null){
			throw new ServiceException("登录信息不正常");
		}
		result.setInfo(info);
		//帮我砍价信息
		if(info.getStatus()==1){
			result.setUsers(successDao.getBargainUser(info.getJoinUser().getJoinId()));
		}
		result.setRankingList(bargainJoinDao.getBargainRanking(bargainId));
		result.setPosters(posterComponent.getBargainPoset(bargainId));
		return result;
	}

}
