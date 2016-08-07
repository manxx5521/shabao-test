package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.BargainUser;
import com.xiaoshabao.wechat.entity.BargainSuccessEntity;
/**
 * 砍价记录
 */
public interface BargainSuccessDao {
	/**
	 * 获得砍价人员列表
	 * @param bargainId
	 * @return
	 */
	public List<BargainUser> getBargainUser(Integer joinId);
	/**
	 * 获得砍价人员列表
	 * @param bargainId
	 * @return
	 */
	public BargainSuccessEntity getBargainSuccessById(@Param("joinId")Integer joinId,@Param("openid")String openid);
	
	/**
	 * 添加砍价成功信息
	 * @param bargainSuccess
	 * @return
	 */
	public int insertBargainSuccess(BargainSuccessEntity bargainSuccess);

}
