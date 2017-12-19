package com.xiaoshabao.vkan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframework.bean.PageValue;
import com.xiaoshabao.vkan.dto.FileDto;
import com.xiaoshabao.vkan.dto.FilePagingParams;
import com.xiaoshabao.vkan.dto.IndexDataVo;
import com.xiaoshabao.vkan.entity.FileEntity;
import com.xiaoshabao.vkan.entity.TagEntity;
import com.xiaoshabao.vkan.service.VkanService;
import com.xiaoshabao.webframework.dto.AjaxResult;

@Controller
@RequestMapping("/vkan")
public class VkanController {
	@Resource(name = "vkanServiceImpl")
	private VkanService vkanService;

	@RequestMapping("/index")
	public ModelAndView getIndexData(ModelMap model,IndexDataVo indexData) {
		model.put("data", vkanService.getIndexData(indexData));
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
	
	/**
	 * 根据父级文件内容
	 * @param fileId
	 * @return
	 */
	@RequestMapping("/getParentFile")
	@ResponseBody
	public AjaxResult getParentFile(@RequestParam("id") Long fileId) {
		FileEntity req=new FileEntity();
		req.setFileId(fileId);
		List<FileEntity> list=this.vkanService.getData(FileEntity.class, req);
		if(list!=null&list.size()>0) {
			return new AjaxResult(true,list.get(0));
		}
		return new AjaxResult("未能正常获得数据");
		
	}

	@RequestMapping(value = "/setTag/{id}/parentId")
	@ResponseBody
	public boolean setTagByParentId(@PathVariable("id") Long parentId) {
		return true;
	}

}
