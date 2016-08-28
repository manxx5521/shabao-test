package com.xiaoshabao.wechat.api.wxbase;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WeixinReqException;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqUtil;
import com.xiaoshabao.wechat.api.wxbase.model.AuthBaseReq;
import com.xiaoshabao.wechat.api.wxbase.model.AuthUserInfoReq;
import com.xiaoshabao.wechat.api.wxbase.result.AuthBaseInfo;

/**
 * 网页授权相关
 */
public class AuthAPI {
	/** 授权标识 */
	public final static String AUTHURL_VALUE="authUrl";
	/** 基本授权 */
	public final static String AUTHURL_SCOP_SNSAPI_BASE="snsapi_base";
	/** 详情授权 */
	public final static String AUTHURL_SCOP_SNSAPI_USERINFO="snsapi_userinfo";
	/**
	 * 获得基本信息 包括网页授权token和openid,已验证返回结果有效型
	 * @param appid
	 * @param secret
	 * @param code 网页获得的code
	 * @return
	 */
	public static AuthBaseInfo getBaseInfo(String appid, String secret, String code){
		AuthBaseReq req=new AuthBaseReq(appid,secret,code);
		JSONObject result=WeiXinReqService.getInstance().doWeinxinReqJson(req);
		return JSONObject.toJavaObject(result, AuthBaseInfo.class);
	}
	
	/**
	 * 获得基本信息-JSON形式
	 * @param appid
	 * @param secret
	 * @param code 网页获得的code
	 * @return
	 */
	public static JSONObject getBaseInfoforJson(String appid, String secret, String code){
		AuthBaseReq req=new AuthBaseReq(appid,secret,code);
		String result=WeiXinReqService.getInstance().doWeinxinReq(req);
		JSONObject josn = JSONObject.parseObject(result);
		// 正常返回
		return josn;
	}
	/**
	 * 获得用户信息,已验证返回结果有效型
	 * @param openid
	 * @param access_token
	 * @return
	 */
	public static AuthBaseInfo getUserInfo(String openid, String access_token){
		AuthUserInfoReq req=new AuthUserInfoReq(openid,access_token);
		JSONObject result=WeiXinReqService.getInstance().doWeinxinReqJson(req);
		return JSONObject.toJavaObject(result, AuthBaseInfo.class);
	}
	/**
	 * 获得用户信息-JSON形式
	 * @param openid
	 * @param access_token
	 * @return
	 */
	public static JSONObject getUserInfoForJson(String openid, String access_token){
		AuthUserInfoReq req=new AuthUserInfoReq(openid,access_token);
		String result=WeiXinReqService.getInstance().doWeinxinReq(req);
		JSONObject josn = JSONObject.parseObject(result);
		// 正常返回
		return josn;
	}
	/**
	 * 获得auth2.0授权的URL
	 * @param appid
	 * @param url  http://www.xiaoshabao.com/signin/init.jhtml
	 * @param scope 应用授权作用域，snsapi_base
	 *            （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo
	 *            （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 */
	public static String getAuthURL(String appid, String url, String scope,
			String state) {
		String resultUrl = "";
		try {
			WeiXinReqService.getInstance();//初始化配置
			WeixinReqConfig objConfig=WeiXinReqUtil.getWeixinReqConfig(AUTHURL_VALUE);
			resultUrl=objConfig.getUrl();
			String redirect_uri = URLEncoder.encode(url, "utf-8");
			resultUrl=resultUrl.replace("APPID",appid).replace("REDIRECT_URI", redirect_uri)
					.replace("SCOPE", scope).replace("STATE", state);
		} catch (Exception e) {
			throw new WeixinReqException("获得auth2.0授权的URL 失败", e);
		}
		return resultUrl;
	}
	/**
	 * 获得auth2.0简单授权的URL
	 * @param appid
	 * @param url  http://www.xiaoshabao.com/signin/init.jhtml
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 */
	public static String getAuthURL_base(String appid, String url,String state) {
		return getAuthURL(appid,url,AUTHURL_SCOP_SNSAPI_BASE,state);
	}
	/**
	 * 获得auth2.0详情授权的URL
	 * @param appid
	 * @param url  http://www.xiaoshabao.com/signin/init.jhtml
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 */
	public static String getAuthURL_info(String appid, String url,String state) {
		return getAuthURL(appid,url,AUTHURL_SCOP_SNSAPI_USERINFO,state);
	}

}
