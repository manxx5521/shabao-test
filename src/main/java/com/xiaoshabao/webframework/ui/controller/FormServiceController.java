package com.xiaoshabao.webframework.ui.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframework.enums.ErrorEnum;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.controller.AbstractController;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillViewData;
import com.xiaoshabao.webframework.ui.enums.ViewTypeEnum;
import com.xiaoshabao.webframework.ui.service.FormService;

@Controller
@RequestMapping("/admin/ui")
public class FormServiceController extends AbstractController {
	@Resource(name = "formServiceImpl")
	private FormService formService;
	@Resource(name = "formEngineComponet")
	FormEngineComponet formEngineComponet;
	
	/**
	 * 带参数列表界面请求
	 * @param model
	 * @param engineType 引擎类型
	 * @param templateId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form/{billId}/list")
	public ModelAndView getList(ModelMap model,@PathVariable("billId") String billId,
			HttpServletRequest request) {
		Map<String, Object> params=getRequestParams(request,ViewTypeEnum.LIST);
		BillListData data=formService.getList( billId, params);
		model.put("data", data);
//		model.put("reqParam", params);//请求参数
		return new ModelAndView("/webframe/ui/"+data.getPagePath(),model);
	}
	
	/**
	 * 获得前台传过来的参数
	 */
	private Map<String, Object> getRequestParams(HttpServletRequest request,ViewTypeEnum viewType,String id){
		Map<String, Object> result= getRequestParams( request, viewType);
		result.put(FormConstants.REQ_ID_TAG, id);
		return result;
	}
	/**
	 * 获得前台传过来的参数
	 */
	private Map<String, Object> getRequestParams(HttpServletRequest request,ViewTypeEnum viewType){
	  Map<String, Object> params = new HashMap<String, Object>();
    Enumeration<String> paramnames = request.getParameterNames();
    while (paramnames.hasMoreElements()) {
      String paramname = paramnames.nextElement();
      params.put(paramname, request.getParameter(paramname));
    }
    
    //添加session信息
    params.put(FormConstants.REQ_SESSION_TAG,formEngineComponet.getFormSessionService().getSessionMap(request));
    params.put(FormConstants.REQ_VIEW_TYPE_ENUM,viewType);
    
    return params;
	}
  
	/**
	 * 查询列表
	 * @param listId 视图id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form/{listId}/query")
	@ResponseBody
	public AjaxResult queryList(@PathVariable("listId") String listId,
			HttpServletRequest request) {
		Map<String, Object> data = getRequestParams(request,ViewTypeEnum.LIST);
		return formService.queryList(listId, data);
	}
  
	/**
	 * 列表界面按钮功能操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form/{billId}/list/{buttonId}/function")
	@ResponseBody
	public AjaxResult doButtonList(@PathVariable("billId") String billId,
			@PathVariable("buttonId") String buttonId,
			HttpServletRequest request) {
		Map<String, Object> data = getRequestParams(request,ViewTypeEnum.LIST);
		return formService.doButtonList(buttonId, data);
	}
	
	/**
	 * 视图界面按钮功能操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form/{billId}/view/{buttonId}/function")
	@ResponseBody
	public AjaxResult doButtonView(@PathVariable("billId") String billId,
			@PathVariable("buttonId") String buttonId,
			HttpServletRequest request) {
		Map<String, Object> data = getRequestParams(request,ViewTypeEnum.VIEW);
		return formService.doButtonView(billId,buttonId, data);
	}
	
	/**
	 * 新增界面
	 * @param model
	 * @param templateId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form/{billId}/add")
	public ModelAndView getView(ModelMap model,@PathVariable("billId") String billId,
			HttpServletRequest request) {
		Map<String, Object> params=getRequestParams(request,ViewTypeEnum.VIEW_ADD);
		BillViewData data=formService.getView( billId, params);
		model.put("data", data);
//		model.put("reqParam", params);//请求参数
		return new ModelAndView("/webframe/ui/"+data.getPagePath(),model);
	}
	
	/**
	 * 展示界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/form/{billId}/view/{id}/detail")
	@ResponseBody
	public ModelAndView getViewDetail(ModelMap model,@PathVariable("billId") String billId,
			@PathVariable("id") String id,
			HttpServletRequest request) {
		Map<String, Object> params=getRequestParams(request,ViewTypeEnum.VIEW_DETAIL,id);
		BillViewData data=formService.getView( billId, params);
		model.put("data", data);
//		model.put("reqParam", params);//请求参数
		return new ModelAndView("/webframe/ui/"+data.getPagePath(),model);
	}
	
	
//	-----------------------

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
