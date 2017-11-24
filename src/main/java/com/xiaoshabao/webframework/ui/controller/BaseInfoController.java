package com.xiaoshabao.webframework.ui.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BaseInfoDto;
import com.xiaoshabao.webframework.ui.dto.BaseInfoListDto;
import com.xiaoshabao.webframework.ui.service.BaseInfoService;

@Controller
public class BaseInfoController {
  @Resource(name = "baseInfoServiceImpl")
  private BaseInfoService baseInfoService;

  @RequestMapping(value = "/baseinfo/{id}/view")
  public ModelAndView getInfoView(ModelMap model, @PathVariable("id") String tableId) {
    BaseInfoListDto list = this.baseInfoService.getInfoView(tableId);
    model.put("dataList", list);
    return new ModelAndView("", model);
  }

  @RequestMapping(value = "/form/{id}/add")
  @ResponseBody
  public AjaxResult addBaseInfo(@PathVariable("id") String tableId,BaseInfoDto baseInfo) {
    
    
    return null;
  }

}
