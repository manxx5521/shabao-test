package com.xiaoshabao.wechat.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.service.BargainService;

/**
 * 砍价
 */
@Controller
public class BargainController {
	@Resource(name="BargainService")
	private BargainService bargainService;
	
	/**
	 * 获得砍价信息
	 */
	@RequestMapping("/wechat/bargain/{bargainId}/bargain")
	public ModelAndView getBargain(ModelMap model,@PathVariable("bargainId")Integer bargainId){
		BargainResult result=bargainService.getBargainResult(bargainId);
		model.put("data", result);
		return new ModelAndView("/wechat/bargain/"+result.getTemplate()+"/bargain");
	}

}
