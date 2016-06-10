package com.xiaoshabao.shabaowebtest.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.shabaowebtest.entity.SeckillSuccess;

/**
 * 秒杀成功明细DAO
 */
public interface SeckillSuccessDao {
	/**
	 * 插入购买明细 ，可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSucessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	/**
	 * 根据id查询秒杀明细并携带产品
	 * @param seckillId
	 * @return
	 */
	SeckillSuccess queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
