package com.xiaoshabao.shabaowebtest.service;

import java.util.List;

import com.xiaoshabao.shabaowebtest.dto.SeckillExecution;
import com.xiaoshabao.shabaowebtest.dto.SeckillExposer;
import com.xiaoshabao.shabaowebtest.entity.Seckill;
import com.xiaoshabao.shabaowebtest.exception.KillCloseExcepiton;
import com.xiaoshabao.shabaowebtest.exception.KillException;
import com.xiaoshabao.shabaowebtest.exception.KillRepeatException;

/**
 * 秒杀接口
 */
public interface SeckillService {
	/**
	 * 查询所有秒杀记录
	 * 
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * 查单个记录
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getSeckillById(long seckillId);

	/**
	 * 秒杀开启是输出秒杀接口地址， 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	SeckillExposer exportSeckillUrl(long seckillId);

	/**
	 * 执行秒杀操作
	 * 
	 * @param seckillId
	 * @param userPhnoe
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws KillException, KillRepeatException, KillCloseExcepiton;

}
