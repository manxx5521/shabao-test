package com.xiaoshabao.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframe.enums.ErrorEnum;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.component.ContextHolderWechat;
import com.xiaoshabao.wechat.component.PosterWechatComponent;
import com.xiaoshabao.wechat.dao.BargainDao;
import com.xiaoshabao.wechat.dao.BargainJoinDao;
import com.xiaoshabao.wechat.dao.BargainSuccessDao;
import com.xiaoshabao.wechat.dto.BargainDto;
import com.xiaoshabao.wechat.dto.BargainJoinInfo;
import com.xiaoshabao.wechat.dto.BargainJoinResult;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;
import com.xiaoshabao.wechat.entity.BargainJoinEntity;
import com.xiaoshabao.wechat.entity.BargainSuccessEntity;
import com.xiaoshabao.wechat.enums.ErrorWechatEnum;
import com.xiaoshabao.wechat.service.BargainService;

/**
 * 砍价
 */
@Service("BargainService")
public class BargainServiceImpl extends AbstractServiceImpl implements BargainService {
	@Autowired
	private BargainDao bargainDao;
	@Autowired
	private BargainSuccessDao bargainSuccessDao;
	@Resource(name="wechatPoster")
	private PosterWechatComponent posterComponent;
	@Autowired
	private BargainJoinDao bargainJoinDao;
	/**
	 * 数据处理时间的差值
	 */
	private int time=500;
	
	// 获得砍价信息
	@Override
	public BargainResult getBargainResult(Integer bargainId) {
		String openid=ContextHolderWechat.getOpenid();
		if(StringUtils.isEmpty(openid)){
			throw new ServiceException(ErrorWechatEnum.LOGIN_ERROR);
		}
		BargainResult result=bargainDao.getBargainResult(bargainId);
		if(result==null){
			throw new ServiceException(ErrorWechatEnum.BARGAIN_NO_HAVE);
		}
		//获得参加砍价信息
		BargainJoinResult info=bargainJoinDao.getBargainJoinResult(bargainId, openid);
		if(info==null){
			throw new ServiceException(ErrorEnum.ERROR);
		}
		result.setInfo(info);
		result.setBargainStatus(0);
		//帮我砍价信息
		if(info.getStatus()==1){
			result.setUsers(bargainSuccessDao.getBargainUser(info.getJoinUser().getJoinId()));
			result.setBargainStatus(1);
		}
		result.setRankingList(bargainJoinDao.getBargainRanking(bargainId));
		result.setPosters(posterComponent.getBargainPoset(bargainId));
		return result;
	}
	//执行砍价
	@Override
	@Transactional
	public AjaxResult exeBargain(Integer bargainId) {
		String openid=ContextHolderWechat.getOpenid();
		if(StringUtils.isEmpty(openid)){
			return new AjaxResult(ErrorWechatEnum.LOGIN_ERROR);
		}
		BargainDto bargain=bargainDao.getBargainDtoById(bargainId);
		if(bargain==null){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_NO_HAVE);
		}
		Long now=bargain.getSysdate().getTime();
		if(now<bargain.getStartTime().getTime()){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_START_ERROR);
		}
		if(now+time>bargain.getEndTime().getTime()){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_END_ERROR);
		}
		BargainJoinEntity bargainJoin=bargainJoinDao.getBargainJoin(bargainId, openid);
		if(bargainJoin!=null){
			//已经砍价信息
			return new AjaxResult(true,"have",bargainJoin);
		}
		bargainJoin=new BargainJoinEntity();
		bargainJoin.setBargainId(bargainId);
		bargainJoin.setOpenid(openid);
		bargainJoin.setStatus(1);
		int num=this.getBargainPrice(bargain.getOnePrice(),bargain.getMinPrice(),bargain.getTotalPrice());
		bargainJoin.setBargainPrice(num);
		bargainJoin.setBargainNum(1);
		bargainJoin.setPrice(bargain.getTotalPrice()-num);
		int i=bargainJoinDao.insertBargainJoin(bargainJoin);
		if(i<1){
			logger.error(ErrorWechatEnum.BARGAIN_UPDATE_ERROR.getMessage()+"；添加砍价信息join信息错误");
			throw new ServiceException(ErrorWechatEnum.BARGAIN_UPDATE_ERROR);
		}
		BargainSuccessEntity bargainSuccess=new BargainSuccessEntity(bargainJoin.getJoinId(),openid);
		bargainSuccess.setBargainPrice(num);
		bargainSuccess.setPrice(bargain.getTotalPrice()-num);
		i=bargainSuccessDao.insertBargainSuccess(bargainSuccess);
		if(i<1){
			logger.error(ErrorWechatEnum.BARGAIN_UPDATE_ERROR.getMessage()+"；添加砍价success日志错误");
			throw new ServiceException(ErrorWechatEnum.BARGAIN_UPDATE_ERROR);
		}
		i=bargainDao.addNumber(bargainId);
		if(i<1){
			logger.error(ErrorWechatEnum.BARGAIN_UPDATE_ERROR.getMessage()+"；更新砍价活动信息bargain错误");
			throw new ServiceException(ErrorWechatEnum.BARGAIN_UPDATE_ERROR);
		}
		return new AjaxResult(true,"true",bargainSuccess); 
	}
	//分享砍价获取信息
	@Override
	public BargainResult getShareBargain(Integer joinId) {
		BargainResult result=bargainDao.getBargainResultByJoinId(joinId);
		if(result==null){
			throw new ServiceException(ErrorWechatEnum.BARGAIN_NO_HAVE);
		}
		// 帮我砍价信息
		result.setUsers(bargainSuccessDao.getBargainUser(joinId));
		result.setRankingList(bargainJoinDao.getBargainRanking(result.getBargainId()));
		result.setPosters(posterComponent.getBargainPoset(result.getBargainId()));
		return result;
	}
	
	//执行分享砍价
	@Override
	@Transactional
	public AjaxResult exeShareBargain(Integer joinId) {
		String openid=ContextHolderWechat.getOpenid();
		BargainJoinInfo bargain=bargainJoinDao.getBargainJoinByJoinId(joinId);
		if(bargain==null){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_NO_HAVE);
		}
		Long now=bargain.getSysdate().getTime();
		if(now<bargain.getBargain().getStartTime().getTime()){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_START_ERROR);
		}
		if(now+time>bargain.getBargain().getEndTime().getTime()){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_END_ERROR);
		}
		BargainSuccessEntity success=bargainSuccessDao.getBargainSuccessById(joinId, openid);
		if(success!=null){
			return new AjaxResult(ErrorWechatEnum.BARGAIN_REPEAT);
		}
		//砍价价钱
		int price=this.getBargainPrice(bargain.getBargain().getOnePrice(),bargain.getBargain().getMinPrice(),bargain.getPrice());
		int i=bargainDao.addNumber(bargain.getBargainId());
		if(i<1){
			logger.error(ErrorWechatEnum.BARGAIN_UPDATE_ERROR.getMessage()+";砍价失败，未能正常记录bargain砍价次数");
			throw new ServiceException(ErrorWechatEnum.BARGAIN_UPDATE_ERROR);
		}
		i=bargainJoinDao.updateBargainInfo(price, joinId);
		if(i<1){
			logger.error(ErrorWechatEnum.BARGAIN_UPDATE_ERROR.getMessage()+";未能正常记录砍价价格");
			throw new ServiceException(ErrorWechatEnum.BARGAIN_UPDATE_ERROR);
		}
		BargainSuccessEntity bargainSuccess=new BargainSuccessEntity(joinId,openid);
		bargainSuccess.setBargainPrice(price);
		bargainSuccess.setPrice(bargain.getPrice()-price);
		i=bargainSuccessDao.insertBargainSuccess(bargainSuccess);
		if(i<1){
			logger.error(ErrorWechatEnum.BARGAIN_UPDATE_ERROR.getMessage()+";砍价失败，未能正常记录砍价成功日志");
			throw new ServiceException(ErrorWechatEnum.BARGAIN_UPDATE_ERROR);
		}
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("bargainPrice", price);
		return new AjaxResult(true,"true",result);
	}
	
	/**
	 * 获得随机砍价，价格
	 * @param onePrice 一次砍掉的最大价格
	 * @param mimPrice 能砍到的最小价格
	 * @param price 当前价钱
	 * @return
	 */
	private int getBargainPrice(int onePrice,int mimPrice,int price){
		try {
			if(mimPrice==price){
				throw new ServiceException(ErrorWechatEnum.BARGAIN_MIN_PRICE);
			}
			Random random = new Random();
			int bargainPrice= random.nextInt(10)%(onePrice+1);
			if(bargainPrice==0){
				bargainPrice+=0.1;
			}
			if(bargainPrice>onePrice){
				bargainPrice=onePrice;
			}
			if(price-bargainPrice<mimPrice){
				bargainPrice=mimPrice;
			}
			return bargainPrice;
		} catch (Exception e) {
			logger.error("获得随机砍价数字是错误");
			throw new ServiceException("砍价价格获取失败");
		}
	}
	//获得商品详细信息
	@Override
	public BargainEntity getDetail(Integer bargainId) {
		BargainEntity bargain=bargainDao.getBargainById(bargainId);
		if(bargain==null){
			throw new ServiceException(ErrorWechatEnum.BARGAIN_NO_HAVE);
		}
		return bargain;
	}

}
