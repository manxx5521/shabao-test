package com.xiaoshabao.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.webframework.controller.AbstractController;
import com.xiaoshabao.webframework.dto.AjaxResult;
/**
 * 扫码
 */
@Controller
public class ScanQRCodeController extends AbstractController{
	
	/**
	 * 兑奖初始化界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/wechat/scanqr/exchange/{type}/{id}/init")
	public ModelAndView initExchange (ModelMap model,@PathVariable("type")String type,@PathVariable("id")String id){
		model.put("type", type);
		model.put("id", id);
		return new ModelAndView ("/wechat/qrcode/exchange");
	}
	
	/**
	 * 投票列表下一页<br>
	 */
	@ResponseBody
	@RequestMapping(value="/wechat/scanqr/exchange/{type}/{id}/exe")
	public AjaxResult exeExchange (ModelMap model,@PathVariable("type")String type,@PathVariable("id")String id){
		try {
			return new AjaxResult(true,"");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(false,"数量获取异常");
		}
		
	}
}
