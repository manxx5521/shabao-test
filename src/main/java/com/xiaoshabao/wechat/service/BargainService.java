package com.xiaoshabao.wechat.service;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.service.AbstractService;
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

}
