package com.xiaoshabao.baseframe.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置类<br>
 * 记录系统常用配置信息
 */
@Component("sysConfig")
public class SysConfig {

	/** 资源加载方式 */
	@Value("${resourceType}")
	private String resourceType;

	/** 资源加载方式 */
	public String getResourceType() {
		return resourceType;
	}

}
