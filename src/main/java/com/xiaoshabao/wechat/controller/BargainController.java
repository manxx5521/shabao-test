package com.xiaoshabao.wechat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframework.enums.ErrorEnum;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.BargainAwardDto;
import com.xiaoshabao.wechat.dto.BargainInfoDto;
import com.xiaoshabao.wechat.dto.BargainResult;
import com.xiaoshabao.wechat.entity.BargainEntity;
import com.xiaoshabao.wechat.service.BargainService;

/**
 * 砍价
 */
@Controller
public class BargainController {
	@Resource(name="BargainService")
	private BargainService bargainService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
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
		}catch(Exception e){
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
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
	
	/**
	 * 获得商品信息
	 */
	@RequestMapping("/wechat/bargain/{bargainId}/detail")
	public ModelAndView getDetail(ModelMap model,@PathVariable("bargainId")Integer bargainId){
		BargainEntity result=bargainService.getDetail(bargainId);
		model.put("data", result);
		return new ModelAndView("/wechat/bargain/"+result.getTemplate()+"/detail");
	}
	
	/**
	 * 获得兑奖二维码
	 */
	@RequestMapping("/wechat/bargain/{joinId}/award")
	public ModelAndView getAward(ModelMap model,@PathVariable("joinId")Integer joinId){
		BargainAwardDto result=bargainService.getAward(joinId);
		model.put("data", result);
		return new ModelAndView("/wechat/bargain/"+result.getTemplate()+"/qrcode");
	}
	
	/**************************系统管理相关 **************/
	/**
	 * 获得system项目砍价列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/wechat/bargain/list")
	public ModelAndView getSystemList(ModelMap model,Integer accountId){
		List<BargainInfoDto> list=bargainService.getSystemList(accountId);
		model.put("data", list);
		model.put("accountId", accountId);
		return new ModelAndView("/wechat/bargain/system/bargainList");
	}
	/**
	 * 添加初始化
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/wechat/bargain/init")
	public ModelAndView initSystemBargain(ModelMap model){
		BargainInfoDto data=bargainService.initSystemBargain();
		model.put("data", data);
		return new ModelAndView("/wechat/bargain/system/detail");
	}
	
	/**
	 * 新增砍价活动
	 * @param joinId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/admin/wechat/bargain/add")
	public AjaxResult addSystemBargain(BargainInfoDto bargain){
		try {
			return bargainService.addSystemBargain(bargain);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
		}
	}
	
	/**
	 * 获得system项目砍价信息详情
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/wechat/bargain/{bargainId}/detail")
	public ModelAndView getSystemBargainDetail(ModelMap model,@PathVariable("bargainId")Integer bargainId){
		BargainInfoDto data=bargainService.getSystemBargainDetail(bargainId);
		model.put("data", data);
		model.put("type", "update");
		return new ModelAndView("/wechat/bargain/system/detail");
	}
	
	/**
	 * 修改砍价活动
	 * @param joinId
	 * @param posterState 是否需要更新海报图片
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/admin/wechat/bargain/{bargainId}/update")
	public AjaxResult updateSystemBargain(BargainInfoDto bargain,@PathVariable("bargainId")Integer bargainId,String posterState){
		try {
			return bargainService.updateSystemBargain(bargain,posterState);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
		}
	}
}
