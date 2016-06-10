package com.xiaoshabao.shabaowebtest.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.shabaowebtest.entity.Seckill;
/**
 * 秒杀接口
 */
public interface SeckillDao {
	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	/**
	 * 根据id查询单挑记录
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	/**
	 * 根据偏移量查询商品列表
	 * @param offet 开始记录
	 * @param limit 查询记录大小
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
