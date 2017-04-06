package com.xiaoshabao.webframework.ui.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframework.controller.AbstractController;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.TableDto;
import com.xiaoshabao.webframework.ui.service.FormTableService;

@Controller
@RequestMapping("/admin/ui")
public class FormTableController extends AbstractController {
	@Resource(name = "formTableService")
	private FormTableService formTableService;

	@RequestMapping(value = "/table/{tableId}/info")
	@ResponseBody
	public AjaxResult getTableInfo(@PathVariable("tableId") String tableId) {
		return formTableService.getTableInfo(tableId);
	}

	@RequestMapping(value = "/table/add")
	@ResponseBody
	public AjaxResult addTable(TableDto table) {
		return formTableService.addTable(table);
	}

}
