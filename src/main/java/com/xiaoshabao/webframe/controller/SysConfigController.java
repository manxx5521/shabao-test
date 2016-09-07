package com.xiaoshabao.webframe.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class SysConfigController {

	@RequestMapping("/config/jsconfig")
	public void getSysGlobalConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String contextpath=request.getSession().getServletContext().getContextPath();
		buffer.append(" window.webroot=\"" + contextpath + "\"");
		response.setHeader("content-type", "application/javascript");
		PrintWriter out=response.getWriter();
		out.print(buffer.toString());
		out.close();
	}
	
	/**
	 * 404界面
	 */
	@RequestMapping("/404")
	public ModelAndView get404(ModelMap model){
		return new ModelAndView("/context/404");
	}
	/**
	 * 500界面
	 */
	@RequestMapping("/500")
	public ModelAndView get500(ModelMap model){
		return new ModelAndView("/context/500");
	}
}
