package com.xiaoshabao.webframe.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("config")
public class SysConfigController {

	@RequestMapping("jsconfig")
	public void getSysGlobalConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String contextpath=request.getSession().getServletContext().getContextPath();
		buffer.append(" window.webroot=\"" + contextpath + "\"");
		response.setHeader("content-type", "application/javascript");
		response.getWriter().print(buffer.toString());
	}
}
