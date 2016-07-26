package com.xiaoshabao.wechat.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframe.bean.PageValue;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframe.component.PosterComponent;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.component.WechatContextHolder;
import com.xiaoshabao.wechat.dao.SubscriberDao;
import com.xiaoshabao.wechat.dao.VoteCountDao;
import com.xiaoshabao.wechat.dao.VoteDao;
import com.xiaoshabao.wechat.dao.VoteImageDao;
import com.xiaoshabao.wechat.dao.VotePlayerDao;
import com.xiaoshabao.wechat.dao.VoteSuccessDao;
import com.xiaoshabao.wechat.dto.VoteDetailResult;
import com.xiaoshabao.wechat.dto.VoteListResult;
import com.xiaoshabao.wechat.dto.VoteParams;
import com.xiaoshabao.wechat.entity.SubscriberEntity;
import com.xiaoshabao.wechat.entity.VoteEntity;
import com.xiaoshabao.wechat.entity.VoteImageEntity;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;
import com.xiaoshabao.wechat.entity.VoteSuccessEntity;
import com.xiaoshabao.wechat.service.VoteService;
/**
 * 投票
 */
@Service("voteServiceImpl")
public class VoteServiceImpl extends AbstractServiceImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private VotePlayerDao playerDao;
	@Autowired
	private VoteImageDao imageDao;
	@Autowired
	private VoteCountDao countDao;
	@Autowired
	private SubscriberDao subscriberDao;
	@Resource(name="poster")
	private PosterComponent posterComponent;
	@Autowired
	private VoteSuccessDao voteSuccess;
	
	//获得投票列表详情
	@Override
	public VoteListResult getVoteListResult(Integer voteId,String code,VoteParams params){
		//判断访客加一
		if(StringUtils.isNotEmpty(code)){
			countDao.addVisitNum(voteId);
		}
		VoteListResult result=voteDao.getVoteListResult(voteId);
		if(StringUtils.isEmpty(result.getVoteName())){
			throw new ServiceException("当前投票活动不存在");
		}
		//验证创建session
		//WechatContextHolder.createSession(result.getAccountId());
		//List<VotePlayerEntity> list = playerDao.getPlayerList(voteId);
		//result.setList(list);
		params.setSize(20);
		PageValue<VotePlayerEntity> page=this.getDataPaging(VotePlayerEntity.class, params);
		result.setPage(page);
		result.setPoster(posterComponent.getWchatVotePoset(voteId));
		return result;
	}

	// 获得投票列表详情
	@Override
	public VoteListResult getVoteListResult(Integer voteId) {
		VoteListResult result = voteDao.getVoteListResult(voteId);
		if (StringUtils.isEmpty(result.getVoteName())) {
			throw new ServiceException("当前投票活动不存在");
		}
		// 验证创建session
		result.setPoster(posterComponent.getWchatVotePoset(voteId));
		return result;
	}
	//选手详情界面
	@Override
	public VoteDetailResult getVoteDetailById(Integer playerId) {
		VoteDetailResult result= playerDao.getPlayerDetail(playerId);
		if(StringUtils.isEmpty(result.getPlayerName())){
			throw new ServiceException("选手不存在");
		}
		//验证创建session
		//WechatContextHolder.createSession(result.getVote().getAccountId());
		List<VoteImageEntity> list=imageDao.getImageList(playerId);
		result.setImgList(list);
		result.setPosters(posterComponent.getWchatVotePoset(result.getVoteId()));
		return result;
	}
	
	
	//报名参加活动
	@Override
	public VoteListResult getVoteParticipate(Integer voteId) {
		VoteListResult result=voteDao.getVoteListResult(voteId);
		if(StringUtils.isEmpty(result.getVoteName())){
			throw new ServiceException("当前投票活动不存在");
		}
		//验证创建session
	//	WechatContextHolder.createSession(result.getAccountId());
		result.setPoster(posterComponent.getWchatVotePoset(voteId));
		return result;
	}
	
	//获得排行信息
	@Override
	public  VoteListResult getVoteRanking(Integer voteId,Integer type) {
		if(type==null||type==0){
			type=20;
		}
		VoteListResult result=voteDao.getVoteListResult(voteId);
		if(StringUtils.isEmpty(result.getVoteName())){
			throw new ServiceException("当前投票活动不存在");
		}
		//验证创建session
		//WechatContextHolder.createSession(result.getAccountId());
		result.setList(playerDao.getVoteRanking(voteId, type));
		return result;
	}
	
	//添加选手报名信息
	@Override
	@Transactional
	public AjaxResult addVotePlayer(VotePlayerEntity player,String[] imgs) {
		if(imgs.length<1){
			return new AjaxResult("请至少上传一张照片");
		}
		String img1=imgs[0];
		if(StringUtils.isEmpty(img1)){
			return new AjaxResult("请至少上传一张照片");
		}
		//session处理
		//Integer accountId=WechatContextHolder.getWxAccountId();
		//if(accountId==null){
		//	VoteListResult result=voteDao.getVoteListResult(player.getVoteId());
		//	WechatContextHolder.createSession(result.getAccountId());
		//}
		Integer voteId=player.getVoteId();
		VoteEntity vote=voteDao.getVote(voteId);
		Integer status=null;
		if(vote.getAudit()==null||vote.getAudit()==0){
			status=1;
			int max=playerDao.getMaxPlayerNum(voteId);
			countDao.addUserNum(voteId);
			player.setPlayerNum(++max);
		}else{
			status=0;
		}
		player.setStatus(status);
		player.setImage(img1);
		player.setVoteNum(0);
		int i=playerDao.insertPlayer(player);
		if(i<1){
			throw new ServiceException("选手信息未能正常提交");
		}
		VoteImageEntity voteImage=new VoteImageEntity();
		for(String image:imgs){
			if(StringUtils.isEmpty(image)){
				continue;
			}
			voteImage.setPlayerId(player.getPlayerId());
			voteImage.setType("1");
			voteImage.setVoteId(player.getVoteId());
			voteImage.setImage(image);
			int j=imageDao.insertImage(voteImage);
			if(j<1){
				throw new ServiceException("选手图片未能正常提交");
			}
		}
		return new AjaxResult(true,"添加成功");
	}
	
	//进行投票
	@Override
	@Transactional
	public AjaxResult addVoteNum(Integer voteId, Integer playerId) {
		String openid=WechatContextHolder.getOpenid();
		if(StringUtils.isEmpty(openid)){
			return new AjaxResult("请关注后，再投票");
		}
		List<SubscriberEntity> list=subscriberDao.getSubscriberById(null,openid);
		if(list.isEmpty()){
			return new AjaxResult("当前登录的帐号错误，请重新打开公众号或者重新关注后重试");
		}
		VoteEntity vote=voteDao.getVote(voteId);
		Date now=new Date();
		if(now.before(vote.getStartTime())){
			return new AjaxResult("投票还没有开始");
		}
		if(now.after(vote.getEndTime())){
			return new AjaxResult("投票活动已经结合了");
		}
		VoteSuccessEntity successEntity=voteSuccess.getVoteSuccess(voteId, openid);
		if(successEntity!=null){
			return new AjaxResult("您已经投过票了请勿重复投票");
		}
		
		int i =playerDao.addPlayerVote(playerId);
		if(i<1){
			throw new ServiceException("选手票数未能正常更新");
		}
		i=countDao.addVoteNum(voteId);
		if(i<1){
			throw new ServiceException("总票数未能正常更新");
		}
		i=voteSuccess.insertVoteSuccess(voteId,openid);
		if(i<1){
			throw new ServiceException("成功记录未能正常更新");
		}
		return new AjaxResult(true,"投票成功");
	}
	
}
