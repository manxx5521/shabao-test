package com.xiaoshabao.webframework.ui.service;

/**
 * 表单工厂
 */
public interface TemplateFactory {
  /**
   * 获得表单元素
   * @param templateId 模版id，必传
   */
  public String getTemplateElements(String templateId);

}
