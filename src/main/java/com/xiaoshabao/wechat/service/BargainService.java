package com.xiaoshabao.wechat.service;

import java.util.List;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.BargainAwardDto;
import com.xiaoshabao.wechat.dto.BargainInfoDto;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;
/**
 * 砍价
 */
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
	
	/**
	 * 获得system项目砍价列表
	 * @return
	 */
	public List<BargainInfoDto> getSystemList();
	
	/**
	 * 添加初始化
	 */
	public BargainInfoDto initSystemBargain();
	/**
	 * 添加砍价活动
	 * @param bargain
	 * @return
	 */
	public AjaxResult addSystemBargain(BargainInfoDto bargain);
	
	/**
	 * 获得system项目砍价信息详情
	 */
	public BargainInfoDto getSystemBargainDetail(Integer bargainId);
	
	/**
	 * 修改砍价活动
	 * @param bargain
	 * @return
	 */
	public AjaxResult updateSystemBargain(BargainInfoDto bargain,String posterState);

}
