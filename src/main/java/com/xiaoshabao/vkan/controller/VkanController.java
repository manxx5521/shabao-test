package com.xiaoshabao.vkan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.vkan.service.VkanService;

@Controller
public class VkanController {
	@Resource(name="vkanServiceImpl")
	private VkanService vkanService;
	
	public ModelAndView getIndexData(ModelMap model) {
		return null;
	}
	

}
