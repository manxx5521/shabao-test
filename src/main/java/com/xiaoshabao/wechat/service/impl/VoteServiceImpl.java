package com.xiaoshabao.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dao.VoteDao;
import com.xiaoshabao.wechat.dao.VoteImageDao;
import com.xiaoshabao.wechat.dao.VotePlayerDao;
import com.xiaoshabao.wechat.dto.VoteDetailResult;
import com.xiaoshabao.wechat.dto.VoteListResult;
import com.xiaoshabao.wechat.entity.VoteImageEntity;
import com.xiaoshabao.wechat.entity.VotePlayerEntity;
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
	
	//获得投票列表详情
	@Override
	public VoteListResult getVoteList(Integer voteId){
		VoteListResult result=voteDao.getVoteListResult(voteId);
		if(StringUtils.isEmpty(result.getVoteName())){
			throw new ServiceException("当前投票活动不存在");
		}
		List<VotePlayerEntity> list = playerDao.getPlayerList(voteId);
		result.setList(list);
		result.setImgList(this.getPosterList(result.getPosters()));
		return result;
	}
	
	//选手详情界面
	@Override
	public VoteDetailResult getVoteDetailById(Integer playerId) {
		VoteDetailResult result= playerDao.getPlayerDetail(playerId);
		if(StringUtils.isEmpty(result.getPlayerName())){
			throw new ServiceException("选手不存在");
		}
		List<VoteImageEntity> list=imageDao.getImageList(playerId);
		result.setImgList(list);
		result.setPosters(this.getPosterList(result.getVote().getPosters()));
		return result;
	}
	
	/**
	 * 将字符串海报解析为列表
	 * @param posters
	 * @return
	 */
	private List<String> getPosterList(String posters){
		List<String> posterList=new ArrayList<String>();
		if(StringUtils.isNotEmpty(posters)){
			String[] poster=posters.split(";");
			for (String b:poster){
				posterList.add(b);
			}
		}
		return posterList;
	}
	//报名参加活动
	@Override
	public VoteListResult getVoteParticipate(Integer voteId) {
		VoteListResult result=voteDao.getVoteListResult(voteId);
		if(StringUtils.isEmpty(result.getVoteName())){
			throw new ServiceException("当前投票活动不存在");
		}
		result.setImgList(this.getPosterList(result.getPosters()));
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
		int i=playerDao.insertPlayer(player);
		if(i<1){
			throw new ServiceException("选手信息未能正常提交");
		}
		VoteImageEntity voteImage=new VoteImageEntity();
		for(String image:imgs){
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

}
