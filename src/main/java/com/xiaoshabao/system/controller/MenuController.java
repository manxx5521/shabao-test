package com.xiaoshabao.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframe.controller.AbstractController;

@Controller
public class MenuController extends AbstractController{
	
	/**
	 * 获得微信菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/wechatMenu")
	public ModelAndView getWechatMenu(ModelMap model){
		
		return new ModelAndView("/admin/menu/wechatMenu",model);
	}
	

}
