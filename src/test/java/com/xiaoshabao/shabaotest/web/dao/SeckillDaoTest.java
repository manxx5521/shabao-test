package com.xiaoshabao.shabaotest.web.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import util.DaoTest;

import com.xiaoshabao.shabaowebtest.dao.SeckillDao;
import com.xiaoshabao.shabaowebtest.entity.Seckill;

public class SeckillDaoTest extends DaoTest{
	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() {
		int leg=seckillDao.reduceNumber(1000, new Date());
		System.out.println("更新条数："+leg);
	}

	@Test
	public void testQueryById() {
		long id=1000;
		Seckill seckill=seckillDao.queryById(id);
		System.out.println(seckill.toString());
	}

	@Test
	public void testQueryAll() {
		List<Seckill> list=seckillDao.queryAll(0, 100);
		for(Seckill seckill:list){
			System.out.println(seckill.toString());
		}
	}

}
