package com.xiaoshabao.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 砍价
 */
@Controller
public class BargainController {
	
	@RequestMapping("/wechat/bargain/{bargainId}/bargain")
	public ModelAndView getBargain(ModelMap model,@PathVariable("bargainId")Integer bargainId){
		String template="one";
		return new ModelAndView("/wechat/bargain/"+template+"/bargain");
	}

}
