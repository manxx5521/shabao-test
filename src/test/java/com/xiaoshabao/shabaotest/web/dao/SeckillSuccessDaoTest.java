package com.xiaoshabao.shabaotest.web.dao;

import javax.annotation.Resource;

import org.junit.Test;

import util.DaoTest;

import com.xiaoshabao.shabaowebtest.dao.SeckillSuccessDao;
import com.xiaoshabao.shabaowebtest.entity.SeckillSuccess;

public class SeckillSuccessDaoTest extends DaoTest{
	@Resource
	private SeckillSuccessDao seckillSuccessDao;

	@Test
	public void testInsertSucessKilled() {
		int leg=seckillSuccessDao.insertSucessKilled(1000, 13841111523L);
		System.out.println("添加条数："+leg);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SeckillSuccess bean=seckillSuccessDao.queryByIdWithSeckill(1000,13841111523L);
		System.out.println("bean输出："+bean.toString());
		System.out.println("seckill输出："+bean.getSeckill());
	}

}
