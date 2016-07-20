package com.xiaoshabao.webframe.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.webframe.dto.JSTreeNode;
import com.xiaoshabao.webframe.service.TreeService;
@Controller
@RequestMapping("tree")
public class TreeController extends AbstractController{
	@Resource(name="treeServiceImpl")
	private TreeService treeService;

	@RequestMapping(value="{elementId}/list")
	@ResponseBody
	public AjaxResult getList(@PathVariable("elementId") Integer elementId){
		try {
			List<JSTreeNode> list= treeService.getJSTreeList(elementId);
			return new AjaxResult(list);
		} catch (ServiceException se) {
			return new AjaxResult(false,se.getMessage());
		}
	}
}