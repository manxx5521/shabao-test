package com.xiaoshabao.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xiaoshabao.baseframework.exception.DaoException;
import com.xiaoshabao.system.component.ContextHolderSystem;
import com.xiaoshabao.system.entity.MenuEntity;
import com.xiaoshabao.system.service.IndexService;
import com.xiaoshabao.webframework.controller.AbstractController;

/**
 * 主页
 */
@Controller
public class IndexController extends AbstractController{

	@Resource(name = "indexServiceImpl")
	private IndexService indexService;

	/**
	 * 首页frame跳转
	 * 
	 * @param model
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value = "/admin/index1", method = { RequestMethod.GET })
	public ModelAndView adminIndex(ModelMap model) throws DaoException {
//		Integer user_id=SessionManager.getInstance().getSeesionInfo().getUser_id();
		return new ModelAndView("/system/index1", model);
	}
	
	/**
	 * 主页跳转,登录后跳转main.jsp方法
	 * 
	 * @param model
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value = "/admin/index", method = { RequestMethod.GET })
	public ModelAndView adminIndex1(ModelMap model) throws DaoException {
		Integer userId=ContextHolderSystem.getUserId();
		//取出左侧菜单
		List<MenuEntity> menuList=indexService.getMenuList(userId);
		model.put("menuList", JSON.toJSONString(menuList));
		
		return new ModelAndView("/system/main", model);
	}
	/***web项目关**/
	/** 首页 */
	@RequestMapping(value = "/index")
	public ModelAndView getIndex(ModelMap model) throws DaoException {
		return new ModelAndView("/system/web/index", model);
	}

	
}
