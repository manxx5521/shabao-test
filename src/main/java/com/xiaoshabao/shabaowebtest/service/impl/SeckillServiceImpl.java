package com.xiaoshabao.shabaowebtest.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.xiaoshabao.shabaowebtest.dao.SeckillDao;
import com.xiaoshabao.shabaowebtest.dao.SeckillSuccessDao;
import com.xiaoshabao.shabaowebtest.dto.SeckillExecution;
import com.xiaoshabao.shabaowebtest.dto.SeckillExposer;
import com.xiaoshabao.shabaowebtest.entity.Seckill;
import com.xiaoshabao.shabaowebtest.enums.SeckillStatEnum;
import com.xiaoshabao.shabaowebtest.exception.KillCloseExcepiton;
import com.xiaoshabao.shabaowebtest.exception.KillException;
import com.xiaoshabao.shabaowebtest.exception.KillRepeatException;
import com.xiaoshabao.shabaowebtest.service.SeckillService;

/**
 * 秒杀业务实现
 */
@Service("seckillServiceImpl")
public class SeckillServiceImpl implements SeckillService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SeckillSuccessDao seckillSuccessDao;
	//md5盐值
	private final String slat="zheshiyanzhi";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getSeckillById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public SeckillExposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		if (seckill == null) {
			return new SeckillExposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()) {
			return new SeckillExposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
		}
		String md5=this.getMD5(seckillId);
		return new SeckillExposer(true,md5,seckillId);
	}
	/**
	 * 生成md5
	 * @param seckillId
	 * @return
	 */
	private String getMD5(long seckillId){
		String base=seckillId+"/"+slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws KillException, KillRepeatException,
			KillCloseExcepiton {
		if(md5==null||!md5.equals(getMD5(seckillId))){
			throw new KillException("秒杀数据错误");
		}
		try {
			//减库存
			int updateLeg=seckillDao.reduceNumber(seckillId, new Date());
			if(updateLeg<=0){
				throw new KillCloseExcepiton("秒杀已经结束");
			}
			int insertLeg=seckillSuccessDao.insertSucessKilled(seckillId, userPhone);
			if(insertLeg<=0){
				throw new KillRepeatException("重复秒杀");
			}
			seckillSuccessDao.queryByIdWithSeckill(seckillId, userPhone);
		}catch(KillCloseExcepiton e1){
			throw e1;
		}catch(KillRepeatException e2){
			throw e2;
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new KillException("秒杀服务异常");
		}
		return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS);
	}

}
