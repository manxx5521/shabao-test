package com.xiaoshabao.shabaowebtest.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.shabaowebtest.dto.SeckillExecution;
import com.xiaoshabao.shabaowebtest.dto.SeckillExposer;
import com.xiaoshabao.shabaowebtest.dto.SeckillResult;
import com.xiaoshabao.shabaowebtest.entity.Seckill;
import com.xiaoshabao.shabaowebtest.enums.SeckillStatEnum;
import com.xiaoshabao.shabaowebtest.exception.KillCloseExcepiton;
import com.xiaoshabao.shabaowebtest.exception.KillRepeatException;
import com.xiaoshabao.shabaowebtest.service.SeckillService;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name="seckillServiceImpl")
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "/shabaotest/seckill/list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String getdetail(@PathVariable("seckillId") Long seckillId,
			Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getSeckillById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "/shabaotest/seckill/detail";
	}

	/**
	 * 获得秒杀地址
	 */
	@ResponseBody
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST)
	public SeckillResult<SeckillExposer> exposer(@PathVariable("seckillId")Long seckillId) {
		SeckillResult<SeckillExposer> result = null;
		try {
			SeckillExposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<SeckillExposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SeckillResult<SeckillExposer>(false, e.getMessage());
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/{secillId}/{md5}/execution", method = RequestMethod.POST, produces =MediaType.APPLICATION_JSON_VALUE)
	public SeckillResult<SeckillExecution> execute(
			@PathVariable("secillId") Long seckillId,
			@PathVariable("md5") String md5,
			@CookieValue(value = "killPhone", required = false) Long userPhone) {
		if (userPhone == null) {
			return new SeckillResult<SeckillExecution>(false, "用户未注册");
		}
		try {
			SeckillExecution execution = seckillService.executeSeckill(
					seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (KillRepeatException e) {
			logger.info(e.getMessage());
			SeckillExecution execution = new SeckillExecution(seckillId,
					SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (KillCloseExcepiton e) {
			logger.info(e.getMessage());
			SeckillExecution execution = new SeckillExecution(seckillId,
					SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			SeckillExecution execution = new SeckillExecution(seckillId,
					SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
	}
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> getTime(){
		Date now=new Date();
		return new SeckillResult<Long>(true,now.getTime());
	}
}
