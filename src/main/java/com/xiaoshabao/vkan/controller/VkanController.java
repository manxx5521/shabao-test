package com.xiaoshabao.vkan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.vkan.service.VkanService;

@Controller
@RequestMapping("/vkan")
public class VkanController {
	@Resource(name="vkanServiceImpl")
	private VkanService vkanService;
	
	public ModelAndView getIndexData(ModelMap model,String[] tagIds,String search) {
		return null;
	}
	
	
	public ModelAndView getFileDataView(ModelMap model,Long parentId) {
		return null;
	}
	
	@RequestMapping(value="/setTag/{id}/parentId")
	@ResponseBody
	public boolean setTagByParentId(@PathVariable("id")Long parentId) {
		return true;
	}

}
