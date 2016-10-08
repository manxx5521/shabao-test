package com.xiaoshabao.webframework.ui.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframework.controller.AbstractController;
import com.xiaoshabao.baseframework.enums.ErrorEnum;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.SelectResultDto;
import com.xiaoshabao.webframework.ui.service.WebElementService;
@Controller
@RequestMapping("/admin/ui")
public class WebElementController extends AbstractController{
	@Resource(name="webElementServiceImpl")
	private WebElementService elementService;

	@RequestMapping(value="/select/{elementId}")
	@ResponseBody
	public AjaxResult getSelectValues(@PathVariable("elementId") Integer elementId){
		try {
			List<SelectResultDto> list=elementService.getSelectValues(elementId);
			return new AjaxResult(list);
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