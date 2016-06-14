package com.xiaoshabao.wechat.api.core.req;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 微信请求参数父类
 */
public class WeixinReqParam {

	/**
	 * 微信获取信息令牌<br>
	 * JSON转换时不转换此属性放到URL中
	 */
	@JSONField(serialize=false)
	private String access_token;

	/**
	 * 转换为HTTP参数方法
	 * <p>
	 * 子类可以通过重写此方法，并将配置文件的设置为dataType="string"来解析参数。<br>
	 * 为了加快速度 组装字符串是可使用StringBuffer
	 * </p>
	 * 
	 * @return String 返回的参数为 key1=value1&key2=value2
	 */
	public String toParams() {
		return "access_token=" + access_token;
	}
	
	/**
	 * 设置JSON请求是URL部分必填的部分
	 * <p>其他主体，使用JSON形式传递，为了加快速度 组装字符串是可使用StringBuffer</p>
	 * @return String 返回的参数为key1=value1&key2=value2
	 */
	public String toJsonParams(){
		return "access_token=" + access_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}
