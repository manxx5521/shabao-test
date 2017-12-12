package com.xiaoshabao.vkan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.service.VkanService;

@Controller
@RequestMapping("/vkan")
public class VkanController {
	@Resource(name = "vkanServiceImpl")
	private VkanService vkanService;

	@RequestMapping("/index")
	public ModelAndView getIndexData(ModelMap model, String[] tagIds, String search,String projectPrefix,Integer projectId) {
		model.put("data", vkanService.getIndexData(tagIds, search,projectPrefix,projectId));
		return new ModelAndView("/vkan/index", model);
	}
	
	/**
	 * 获得标签数据
	 * @return
	 */
	@RequestMapping("/tagList")
	@ResponseBody
	public List<TagEntity> getTagList(Integer parentId){
		return this.vkanService.getTagList(parentId);
	}
	/**
	 * 获得文件数据
	 * @return
	 */
	@RequestMapping("/fileData")
	@ResponseBody
	public PageValue<FileDto> getFileDataView(ModelMap model, FilePagingParams params) {
		return this.vkanService.getFileDto(params);
	}

	@RequestMapping(value = "/setTag/{id}/parentId")
	@ResponseBody
	public boolean setTagByParentId(@PathVariable("id") Long parentId) {
		return true;
	}

}
