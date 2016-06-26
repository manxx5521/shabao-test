package com.xiaoshabao.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.system.entity.DepartEntity;
import com.xiaoshabao.system.service.DepartService;
import com.xiaoshabao.webframe.dto.AjaxResult;

@RequestMapping("/admin/depart")
@Controller
public class DepartController {
	@Resource(name="departServiceImpl")
	private DepartService departService;
	/**
	 * 部门管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String getView(ModelMap model){
		return "/system/depart/departView";
	}
	
	/**
	 * 返回部门详细信息
	 * @param depart_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("{departId}/info")
	public AjaxResult getView(@PathVariable("departId")String departId){
		return departService.getDepartInfo(departId);
	}
	/**
	 * 修改部门信息
	 * @param depart_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("{departId}/editDepart")
	public AjaxResult editDepart(@PathVariable("departId")String departId,DepartEntity depart){
		try {
			departService.editDepart(depart);
			return new AjaxResult(true,"部门更新成功");
		} catch (ServiceException se) {
			return new AjaxResult(false,se.getMessage());
		}
	}
	/**
	 * 增加同级部门
	 * @param depart_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("{departId}/addSameDepart")
	public AjaxResult addSameDepart(@PathVariable("departId")String departId,DepartEntity depart){
		return departService.getDepartInfo(departId);
	}
	
	/**
	 * 增加下级部门
	 * @param depart_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("{departId}/addLowerDepart")
	public AjaxResult addLowerDepart(@PathVariable("departId")String departId,DepartEntity depart){
		return departService.getDepartInfo(departId);
	}
}
