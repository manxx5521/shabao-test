package com.xiaoshabao.wechat.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.service.BargainService;

/**
 * 砍价
 */
@Controller
public class BargainController {
	@Resource(name="BargainService")
	private BargainService bargainService;
	
	/**
	 * 获得砍价信息
	 */
	@RequestMapping("/wechat/bargain/{bargainId}/bargain")
	public ModelAndView getBargain(ModelMap model,@PathVariable("bargainId")Integer bargainId){
		BargainResult result=bargainService.getBargainResult(bargainId);
		model.put("data", result);
		return new ModelAndView("/wechat/bargain/"+result.getTemplate()+"/bargain");
	}
	/**
	 * 执行砍价
	 * @param joinId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wechat/bargain/{bargainId}/exeBargain")
	public AjaxResult exeBargain(@PathVariable("bargainId")Integer bargainId){
		try {
			return bargainService.exeBargain(bargainId);
		} catch (ServiceException e) {
			return new AjaxResult(e.getMessage());
		}
	}
	/**
	 * 获得砍价信息
	 */
	@RequestMapping("/wechat/bargain/{joinId}/share")
	public ModelAndView getShareBargain(ModelMap model,@PathVariable("joinId")Integer joinId){
		BargainResult result=bargainService.getShareBargain(joinId);
		model.put("data", result);
		model.put("joinId", joinId );
		return new ModelAndView("/wechat/bargain/"+result.getTemplate()+"/bargain");
	}
	/**
	 * 分享砍价
	 * @param joinId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wechat/bargain/{joinId}/exeShareBargain")
	public AjaxResult exeShareBargain(@PathVariable("joinId")Integer joinId){
		try {
			return bargainService.exeShareBargain(joinId);
		} catch (ServiceException e) {
			return new AjaxResult(e.getMessage());
		}
	}

}
