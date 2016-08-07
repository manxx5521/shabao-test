package com.xiaoshabao.wechat.service;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.BargainResult;
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

}
