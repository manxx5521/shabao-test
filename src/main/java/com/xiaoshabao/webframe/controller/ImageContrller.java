package com.xiaoshabao.webframe.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.webframe.dto.ImageDto;
import com.xiaoshabao.webframe.service.ImageService;

/**
 * 图片处理
 */
@Controller
@RequestMapping("/admin/image")
public class ImageContrller {
	@Resource(name="imageServiceImpl")
	private ImageService imageService;
	
	@RequestMapping(value="{elementId}/list")
	@ResponseBody
	public AjaxResult getList(@PathVariable("elementId") Integer elementId){
		try {
			List<ImageDto> list=imageService.getList(elementId);
			return new AjaxResult(true,"success",list);
		} catch (ServiceException se) {
			return new AjaxResult(false,se.getMessage());
		}
	}
}
