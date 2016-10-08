package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.dto.BargainDto;
import com.xiaoshabao.wechat.dto.BargainInfoDto;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;

/**
 * 砍价
 */
public interface BargainDao {
	/**
	 * 添加砍价信息
	 */
	public int insertBargain(BargainEntity bargain);
	/**
	 * 添加砍价信息
	 */
	public int updateBargain(BargainEntity bargain);
	/**
	 * 获得砍价信息
	 */
	public BargainResult getBargainResult(Integer bargainId);
	/**
	 * 获得砍价信息
	 */
	public BargainResult getBargainResultByJoinId(Integer joinId);
	/**
	 * 获得砍价信息
	 */
	public BargainEntity getBargainById(Integer bargainId);
	
	/**
	 * 获得砍价信息，带附加信息，如数据库时间
	 */
	public BargainDto getBargainDtoById(Integer bargainId);
	/**
	 * 添加当前活动的总次数,砍价的次数
	 */
	public int addBargainNum(Integer bargainId);
	/**
	 * 添加当前活动的总次数，添加次数和参与活动人数
	 */
	public int addNumber(Integer bargainId);
	/**
	 * 获得system项目砍价列表
	 */
	public List<BargainInfoDto> getSystemList(@Param("priFrame")String priFrame,@Param("accountId")Integer accountId);
	/**
	 * 获得system项目砍价活动 详细信息
	 */
	public BargainInfoDto getBargainInfoDtoById(Integer bargainId);
}
