package com.xiaoshabao.shabaotest.web.service.impl;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import util.ServiceTest;

import com.xiaoshabao.shabaowebtest.dto.SeckillExecution;
import com.xiaoshabao.shabaowebtest.dto.SeckillExposer;
import com.xiaoshabao.shabaowebtest.entity.Seckill;
import com.xiaoshabao.shabaowebtest.exception.KillCloseExcepiton;
import com.xiaoshabao.shabaowebtest.exception.KillException;
import com.xiaoshabao.shabaowebtest.exception.KillRepeatException;
import com.xiaoshabao.shabaowebtest.service.SeckillService;

public class SeckillServiceImplTest extends ServiceTest{
	private final Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list=seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetSeckillById() {
		long id=1000;
		Seckill seckill=seckillService.getSeckillById(id);
		logger.info("seckill={}",seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long id=1000;
		SeckillExposer exposer=seckillService.exportSeckillUrl(id);
		logger.info("exposer={}",exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long id=1000;
		long userPhnoe=132546666662L;
		String md5="447edae8fbdff0795426b104007ed001";
		try {
			SeckillExecution seckillExecution=seckillService.executeSeckill(id, userPhnoe, md5);
			logger.info("seckillExecution={}",seckillExecution);
		} catch (KillRepeatException e) {
			e.printStackTrace();
		} catch (KillCloseExcepiton e) {
			e.printStackTrace();
		} catch (KillException e) {
			e.printStackTrace();
		}
	}

}
