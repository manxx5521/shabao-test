package com.xiaoshabao.webframework.ui.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframework.controller.AbstractController;
import com.xiaoshabao.baseframework.enums.ErrorEnum;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.service.FormService;

@Controller
@RequestMapping("/admin/ui")
public class FormServiceController extends AbstractController {
	@Resource(name = "webElementServiceImpl")
	private FormService formService;

	@RequestMapping(value = "/{engineType}/ajax/{elementId}")
	@ResponseBody
	public AjaxResult getElementResponse(
			@PathVariable("engineType") String engineType,
			@PathVariable("elementId") String elementId,
			HttpServletRequest request) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Enumeration<String> paramnames = request.getParameterNames();
			while (paramnames.hasMoreElements()) {
				String paramname = paramnames.nextElement();
				params.put(paramname, request.getParameter(paramname));
			}
			AjaxResult result = formService.getElementResponse(engineType,
					elementId, params);
			return result;
		} catch (ServiceException e) {
			logger.error("请求下拉列表时，出现业务异常");
			e.printStackTrace();
			return new AjaxResult(e.getMessage());
		} catch (Exception e) {
			logger.error("请求下拉列表时，出现未知异常");
			e.printStackTrace();
			return new AjaxResult(ErrorEnum.INNER_ERROR);
		}
	}

}
