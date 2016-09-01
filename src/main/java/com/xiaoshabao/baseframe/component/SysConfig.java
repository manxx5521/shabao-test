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
	
	/** 文件上传图片格式 */
	@Value("${file.image}")
	private String fileImage;
	/** 文件上传附件格式 */
	@Value("${file.attached}")
	private String fileAttached;

	/** 资源加载方式 */
	public String getResourceType() {
		return resourceType;
	}
	/** 文件上传图片格式 */
	public String getFileImage() {
		return fileImage;
	}
	/** 文件上传附件格式 */
	public String getFileAttached() {
		return fileAttached;
	}

}
