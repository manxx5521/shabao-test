package com.xiaoshabao.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
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
import com.xiaoshabao.system.component.ContextHolderSystem;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.webframe.entity.PosterEntity;
import com.xiaoshabao.wechat.api.wxaccount.AccountAPI;
import com.xiaoshabao.wechat.api.wxaccount.result.QrcodeResult;
import com.xiaoshabao.wechat.component.ContextHolderWechat;
import com.xiaoshabao.wechat.component.PosterWechatComponent;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.dao.AccountDao;
import com.xiaoshabao.wechat.dao.BargainDao;
import com.xiaoshabao.wechat.dao.BargainJoinDao;
import com.xiaoshabao.wechat.dao.BargainSuccessDao;
import com.xiaoshabao.wechat.dao.QrcodeDao;
import com.xiaoshabao.wechat.dto.AccountValue;
import com.xiaoshabao.wechat.dto.BargainAwardDto;
import com.xiaoshabao.wechat.dto.BargainDto;
import com.xiaoshabao.wechat.dto.BargainInfoDto;
import com.xiaoshabao.wechat.dto.BargainJoinInfo;
import com.xiaoshabao.wechat.dto.BargainJoinResult;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;
import com.xiaoshabao.wechat.entity.BargainJoinEntity;
import com.xiaoshabao.wechat.entity.BargainSuccessEntity;
import com.xiaoshabao.wechat.entity.QrcodeEntity;
import com.xiaoshabao.wechat.entity.QrcodeRelEntity;
import com.xiaoshabao.wechat.enums.ErrorWechatEnum;
import com.xiaoshabao.wechat.enums.WechatType;
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
	@Autowired
	private QrcodeDao qrcodeDao;
	@Resource(name="tokenManager")
	private TokenManager tokenManager;
	@Autowired
	private AccountDao accountDao;
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
		result.setType("1");//设置为自己砍价
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
		result.setType("2");//设置为分享砍价
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
	//获得兑奖二维码
	@Override
	@Transactional
	public BargainAwardDto getAward(Integer joinId) {
		BargainAwardDto awardDto=bargainJoinDao.getBargainAwardDto(joinId);
		//有效
		if(awardDto!=null&&awardDto.getFlag()==0){
			return awardDto;
		}
		//无效重新换取
		Integer accountId=ContextHolderWechat.getAccountId();
		QrcodeRelEntity qrcodeRel=new QrcodeRelEntity();
		qrcodeRel.setAccountId(accountId);
		qrcodeRel.setType(WechatType.BARGAIN.getValue());
		qrcodeRel.setParams(joinId.toString());
		int i=qrcodeDao.insertQrcodeRel(qrcodeRel);
		if(i<1){
			throw new ServiceException(ErrorWechatEnum.BARGAIN_QRCODE_ERRO);
		}
		String accessToken=tokenManager.getAccessToken(accountId).getAccessToken();
		String expire_seconds="2592000";//有效时间
		QrcodeResult qrcodeResult=AccountAPI.createQrcodeTemp(accessToken, joinId.toString(), expire_seconds);
		QrcodeEntity qrcode=new QrcodeEntity();
		qrcode.setQrcodeId(qrcodeRel.getQrcodeId());
		qrcode.setDes("砍价兑换自动获取二维码");
		qrcode.setActionName(AccountAPI.QRCODE_TYPE_SCENE);
		qrcode.setSceneId(joinId.toString());
		qrcode.setExpireSeconds(qrcodeResult.getExpire_seconds());
		qrcode.setTicket(qrcodeResult.getTicket());
		qrcode.setUrl(qrcodeResult.getUrl());
		qrcode.setQrUrl(qrcodeResult.getQRurl());
		i=qrcodeDao.insertQrcode(qrcode);
		if(i<1){
			throw new ServiceException(ErrorWechatEnum.BARGAIN_QRCODE_ERRO);
		}
		i=bargainJoinDao.updateQrcodeId(joinId, qrcode.getQrcodeId());
		if(i<1){
			throw new ServiceException(ErrorWechatEnum.BARGAIN_QRCODE_ERRO);
		}
		awardDto.setQrcode(qrcode);
		awardDto.setQrcodeId(qrcode.getQrcodeId());
		return awardDto;
	}
	/*********************** system项目相关 begin**************************/
	//获得system项目砍价列表
	@Override
	public List<BargainInfoDto> getSystemList() {
		String priFrame=ContextHolderSystem.getPriFrame();
		List<BargainInfoDto> list=bargainDao.getSystemList(priFrame);
		return list;
	}
	@Override
	public BargainInfoDto initSystemBargain() {
		String priFrame=ContextHolderSystem.getPriFrame();
		List<AccountValue> accounts=accountDao.getAccountValues(priFrame);
		BargainInfoDto result=new BargainInfoDto();
		result.setAccounts(accounts);
		return result;
	}
	//新增砍价活动
	@Override
	@Transactional
	public AjaxResult addSystemBargain(BargainInfoDto bargain) {
		String msg=this.validtionBargainInfoDto(bargain);
		if(StringUtils.isNotEmpty(msg)){
			return new AjaxResult(msg);
		}
		Integer userId=ContextHolderSystem.getUserId();
		bargain.setCreateStaff(userId);
		bargain.setTemplate("one");//先默认使用one
		int i =bargainDao.insertBargain(bargain);
		if(i<1){
			logger.debug("添加砍价活动时保存失败");
			throw new ServiceException(ErrorEnum.SAVE_ERROR);
		}
		//插入海报
		String[] images=bargain.getPoster().getImage().split(",");
		String title=bargain.getPoster().getTitle();
		PosterEntity poster=new PosterEntity();
		poster.setType(WechatType.BARGAIN.getValue());
		poster.setTypeId(bargain.getBargainId().toString());
		poster.setTitle(title);
		poster.setButton("商品详情");
		int leg=0;
		for(String image:images){
			poster.setImage(image);
			leg++;
			poster.setOrderNo(leg);
			i=posterComponent.insertPoster(poster);
			if(i<1){
				logger.debug("活动海报保存时失败");
				throw new ServiceException(ErrorEnum.SAVE_ERROR);
			}
		}
		return new AjaxResult(true,"保存成功");
	}
	
	/**
	 * 验证砍价活动信息
	 */
	private String validtionBargainInfoDto(BargainInfoDto bargain){
		String message=null;
		if(bargain.getAccountId()==null){
			message="请选择帐号";
		}
		if(StringUtils.isEmpty(bargain.getBargainName())){
			message="活动名称不能为空";
		}
		if(StringUtils.isEmpty(bargain.getDes())){
			message="活动描述不能为空";
		}
		if(StringUtils.isEmpty(bargain.getTotalPrice().toString())){
			message="商品总价不能为空";
		}
		if(StringUtils.isEmpty(bargain.getMinPrice().toString())){
			message="商品底价不能为空";
		}
		if(StringUtils.isEmpty(bargain.getOnePrice().toString())){
			message="单次砍掉的最高价钱不能为空";
		}
		if(StringUtils.isEmpty(bargain.getMaxBargainNum().toString())){
			message="砍价的最大次数不能为空";
		}
		if(StringUtils.isEmpty(bargain.getStartTime().toString())){
			message="活动开始时间不能为空";
		}
		if(StringUtils.isEmpty(bargain.getEndTime().toString())){
			message="活动结束时间不能为空";
		}
		if(StringUtils.isEmpty(bargain.getRules())){
			message="活动规则不能为空";
		}
		if(StringUtils.isEmpty(bargain.getGoods())){
			message="商品描述不能为空";
		}
		if(StringUtils.isEmpty(bargain.getPoster().getImage())){
			message="海报照片不能为空";
		}
		if(StringUtils.isEmpty(bargain.getPoster().getTitle())){
			message="海报标题不能为空";
		}
		return message;
	}
	
	//获得system项目砍价信息详情
	@Override
	public BargainInfoDto getSystemBargainDetail(Integer bargainId) {
		BargainInfoDto bargain= bargainDao.getBargainInfoDtoById(bargainId);
		List<AccountValue> accounts=accountDao.getAccountValuesById(bargain.getAccountId());
		List<String> posters=posterComponent.getPosterUrl(WechatType.BARGAIN.getValue(), bargainId.toString());
		String images=StringUtils.join(posters, ",");
		bargain.getPoster().setImage(images);
		bargain.setAccounts(accounts);
		return bargain;
	}
	//修改砍价活动
	@Override
	@Transactional
	public AjaxResult updateSystemBargain(BargainInfoDto bargain,String posterState) {
		String msg=this.validtionBargainInfoDto(bargain);
		if(StringUtils.isNotEmpty(msg)){
			return new AjaxResult(msg);
		}
		int i =bargainDao.updateBargain(bargain);
		if(i<1){
			logger.debug("修改砍价活动时修改砍价活动失败");
			throw new ServiceException(ErrorEnum.SAVE_ERROR);
		}
		PosterEntity poster=new PosterEntity(WechatType.BARGAIN.getValue(),bargain.getBargainId().toString());
		if(StringUtils.isNotEmpty(posterState)&&posterState.equals("1")){
			i=posterComponent.deletePoster(poster);
			if(i<1){
				logger.debug("活动海报，删除海报时失败");
				throw new ServiceException(ErrorEnum.SAVE_ERROR);
			}
			//插入海报
			String[] images=bargain.getPoster().getImage().split(",");
			String title=bargain.getPoster().getTitle();
			poster.setTitle(title);
			poster.setButton("商品详情");
			int leg=0;
			for(String image:images){
				poster.setImage(image);
				leg++;
				poster.setOrderNo(leg);
				i=posterComponent.insertPoster(poster);
				if(i<1){
					logger.debug("修改活动海报时，保存海报记录失败");
					throw new ServiceException(ErrorEnum.SAVE_ERROR);
				}
			}
		}else{
			//只修改标题
			poster.setTitle(bargain.getPoster().getTitle());
			i=posterComponent.updatePoster(poster);
			if(i<1){
				logger.debug("活动海报，保存海报标题时失败");
				throw new ServiceException(ErrorEnum.SAVE_ERROR);
			}
		}
		return new AjaxResult(true,"保存成功");
	}

}
