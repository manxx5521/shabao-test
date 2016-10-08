package com.xiaoshabao.wechat.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframework.enums.ErrorEnum;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.WechatUserDto;
import com.xiaoshabao.wechat.service.WechatUserService;

/**
 * 微信用户
 */
@Controller
public class WechatUserController {
	@Resource(name="wechatUserServiceImpl")
	private WechatUserService wechatUserService;
	/**
	 * 获得用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/wechat/user/list")
	public ModelAndView getList(ModelMap model,Integer accountId){
		List<WechatUserDto> list=wechatUserService.getList(accountId);
		model.put("data", list);
		model.put("accountId", accountId);
		return new ModelAndView("/wechat/user/userList");
	}
	
	/**
	 * 同步用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/admin/wechat/user/{accountId}/sync")
	public AjaxResult syncUser(@PathVariable("accountId")Integer accountId){
		try {
			return wechatUserService.syncUser(accountId);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
		}
	}
}
