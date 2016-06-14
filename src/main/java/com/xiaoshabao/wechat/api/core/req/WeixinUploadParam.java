package com.xiaoshabao.wechat.api.core.req;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信上传请求参数
 */
public class WeixinUploadParam extends WeixinReqParam {

	/**
	 * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 */
	private String type;
	/**
	 * 传送文件读取的路径
	 */
	private List<String> filePathName;
	
	/**
	 * 上传文件是可以传递的参数
	 */
	private Map<String, Object> params;

	/**
	 * 添加传递的参数
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		if(params==null||params.isEmpty()){
			params=new HashMap<String, Object>();
		}
		params.put(key, value);
	}

	/**
	 * 向文件列表添加文件
	 */
	public void addFile(String filePath) {
		if(filePathName==null||filePathName.isEmpty()){
			filePathName=new ArrayList<String>();
		}
		filePathName.add(filePath);
	}

	public List<String> getFilePathName() {
		return filePathName;
	}

	public void setFilePathName(List<String> filePathName) {
		this.filePathName = filePathName;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
