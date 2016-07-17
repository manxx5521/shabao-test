package com.xiaoshabao.system.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframe.component.SysConfig;

/**
 * 系统配置类<br>
 * 记录系统常用配置信息
 */
@Component("systemConfig")
public class SystemConfig extends SysConfig{

	/**
	 * 后台管理最上级菜单
	 */
	@Value("${system.parent_menu_admin}")
	private String parent_menu_admin;
	
	/**
	 * 后台管理左侧菜单分组
	 */
	@Value("${group_id_admin}")
	private String group_id_admin;

	public String getParent_menu_admin() {
		return parent_menu_admin;
	}

	public String getGroup_id_admin() {
		return group_id_admin;
	}
	
}
