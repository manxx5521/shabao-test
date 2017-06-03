package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Set;

import com.xiaoshabao.webframework.ui.entity.FormStaticEntity;
import com.xiaoshabao.webframework.ui.enums.FormStaticEnum;
import com.xiaoshabao.webframework.util.ResourceManager;

/**
 * 视图操作共用
 */
public abstract class AbstractFormPageServiceImpl extends AbstractFormServiceImpl{
	
	/**
	 * 获得头部引用
	 * @param header
	 * @return [css,js,beforeJS]两类
	 */
	public String[] getHeaderHtml(Set<String> header) {
		StringBuilder css = new StringBuilder();
		StringBuilder js = new StringBuilder();
		StringBuilder beforeScript = new StringBuilder();
		ResourceManager manager = ResourceManager.getInstance();
		// 引入需要的公共资源
		for (FormStaticEntity staticEntity : this.getFormStatic(FormStaticEnum.COMMON_RESOURCE)) {
			css.append(manager.getCssTag(staticEntity.getDataId()));
			js.append(manager.getJSTag(staticEntity.getDataId()));
		}
		for (FormStaticEntity staticEntity : this.getFormStatic(FormStaticEnum.BEFORE_SCRIPT)) {
			beforeScript.append(manager.getJSTag(staticEntity.getDataId()));
		}
		
		//引入自定义资源
		if (header != null && header.size() > 0) {
			
			for (String id : header) {
				css.append(manager.getCssTag(id));
				js.append(manager.getJSTag(id));
			}
		}
		return new String[] { css.toString(), js.toString(),beforeScript.toString() };
	}


}
