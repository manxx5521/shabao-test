package com.xiaoshabao.wechat.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.util.UUIDGenerator;
import com.xiaoshabao.wechat.entity.AccessToken;
/**
 * 微信工具类
 */
public class WechatUtil {
	private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);
	/**
	 * token静态类，使用内存缓存
	 */
	public static Map<Integer, AccessToken> accessTokens = new HashMap<Integer, AccessToken>();
	
	/**
	 * 给url js-sdk签名
	 */
	public static Map<String, String> getjsSignStr(String appid,String jsapi_ticket, String url) {
		Map<String, String> result= new HashMap<String, String>();
		result.put("appid", appid);
		String nonce_str = UUIDGenerator.generate();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);;
		// 注意这里参数名必须全部小写，且必须有序
		StringBuffer sb=new StringBuffer();
		sb.append("jsapi_ticket=");
		sb.append(jsapi_ticket);
		sb.append("&noncestr=");
		sb.append(nonce_str);
		sb.append("&timestamp=");
		sb.append(timestamp);
		sb.append("&url=");
		sb.append(url);
		logger.debug("js签名参数:"+sb.toString());
		// SHA1加密
		String digest = DigestUtils.sha1Hex(sb.toString());
		result.put("url", url);
		result.put("jstoken", jsapi_ticket);
		result.put("nonceStr", nonce_str);
		result.put("timestamp", timestamp);
		result.put("signature", digest);
		return result;
	}

}
