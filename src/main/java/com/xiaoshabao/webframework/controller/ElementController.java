package com.xiaoshabao.webframework.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.baseframe.enums.ErrorEnum;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.service.ElementService;
@Controller
@RequestMapping("/admin/ui")
public class ElementController extends AbstractController{
	@Resource(name="elementServiceImpl")
	private ElementService elementService;

	@RequestMapping(value="/select/{elementId}")
	@ResponseBody
	public AjaxResult getSelect(@PathVariable("elementId") Integer elementId){
		try {
			return new AjaxResult(true,null);
		} catch (ServiceException e) {
			logger.error("请求下拉列表时，出现业务异常");
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		}catch(Exception e){
			logger.error("请求下拉列表时，出现未知异常");
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
		}
	}
}
