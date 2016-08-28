package com.xiaoshabao.wechat.service;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.BargainAwardDto;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;
/**
 * 砍价
 */
@Service("bargainService")
public interface BargainService extends AbstractService {
	/**
	 * 获得砍价信息
	 * @param bargainId
	 * @return
	 */
	public BargainResult getBargainResult(Integer bargainId);
	
	/**
	 * 执行砍价
	 * @param bargainId
	 * @return
	 */
	public AjaxResult exeBargain(Integer bargainId);
	
	/**
	 * 获得砍价信息
	 */
	public BargainResult getShareBargain(Integer joinId);
	/**
	 * 执行分享砍价
	 * @param joinId
	 * @return
	 */
	public AjaxResult exeShareBargain(Integer joinId);
	
	/**
	 * 获得商品详细信息
	 * @param bargainId
	 * @return
	 */
	public BargainEntity getDetail(Integer bargainId);
	/**
	 * 获得兑奖二维码
	 * @param bargainId
	 * @return
	 */
	public BargainAwardDto getAward(Integer joinId);

}
