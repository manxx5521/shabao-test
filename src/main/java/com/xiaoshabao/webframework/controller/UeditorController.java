package com.xiaoshabao.webframework.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;
import com.xiaoshabao.baseframe.controller.AbstractController;

@Controller
@RequestMapping(value="/ueditor")
public class UeditorController extends AbstractController{
	
	@RequestMapping(value="/dispatch")
	public void reqURL (ModelMap model,HttpServletRequest request,HttpServletResponse response,String action){
		response.setContentType("application/json");
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {
			request.setCharacterEncoding( "utf-8" );
			response.setHeader("Content-Type" , "text/html");
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.error("ueditor解析失败");
			e.printStackTrace();
		}
	}

}
