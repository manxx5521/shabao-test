package com.xiaoshabao.shabaowebtest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.shabaowebtest.dto.DemoDto;
import com.xiaoshabao.shabaowebtest.service.DemoService;
/**
 * 实例controller
 */
@Controller
@RequestMapping(value="demo")
public class DemoController extends AbstractController{
	
	@Resource(name="demoService")
	private DemoService demoService;
	
	
	@RequestMapping(value="/test")
	public ModelAndView reqMap (ModelMap model) throws DaoException{
		DemoDto dto=this.demoService.testSQL();
		model.addAttribute("date", dto.getDate());
		return new ModelAndView ("/shabaotest/demo/demo");
	}
	
	@RequestMapping(value="/test1")
	public ModelAndView test1 (ModelMap model){
		return new ModelAndView ("/system/test/modeltest");
	}
	
}
