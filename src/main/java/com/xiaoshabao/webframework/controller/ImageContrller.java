package com.xiaoshabao.webframework.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.dto.ImageDto;
import com.xiaoshabao.webframework.service.ImageService;

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
