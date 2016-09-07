package com.xiaoshabao.wechat.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView getList(ModelMap model){
		List<WechatUserDto> list=wechatUserService.getList();
		model.put("data", list);
		return new ModelAndView("/wechat/user/userList");
	}
	
	/**
	 * 修改砍价活动
	 * @param joinId
	 * @param posterState 是否需要更新海报图片
	 * @return
	 
	@ResponseBody
	@RequestMapping("/admin/wechat/user/list")
	public AjaxResult updateSystemBargain(){
		try {
			return null;
		} catch (ServiceException e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
		}
	}
	*/
}
