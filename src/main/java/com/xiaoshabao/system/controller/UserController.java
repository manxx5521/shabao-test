package com.xiaoshabao.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshabao.system.entity.UserEntity;
import com.xiaoshabao.system.service.UserService;

@RequestMapping("/admin/user")
@Controller
public class UserController {
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	/**
	 * 注册界面
	 * @param model
	 * @return
	 */
	@RequestMapping("registered")
	public String getRegistered(ModelMap model){
		return "/system/user/userView";
	}
	/**
	 * 注册界面
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String addUser(ModelMap model,UserEntity user){
		
		
		return "/system/user/userView";
	}
	/**
	 * 用户展示界面
	 * @param model
	 * @return
	 */
	@RequestMapping("{userId}/view")
	public String getView(@PathVariable("userId")Integer userId,ModelMap model){
		
		return "/system/user/userView";
	}
	
}
