package com.xiaoshabao.wechat.api.wxbase;


import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WeixinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxbase.model.AccessTokenReq;
import com.xiaoshabao.wechat.api.wxbase.model.JSTokenReq;
import com.xiaoshabao.wechat.api.wxbase.result.TokenResult;
/**
 * 微信--token信息
 */
public class TokenAPI {

	/**
	 * 获取权限令牌信息
	 * @param appid
	 * @param appscret
	 * @return kY9Y9rfdcr8AEtYZ9gPaRUjIAuJBvXO5ZOnbv2PYFxox__uSUQcqOnaGYN1xc4N1rI7NDCaPm_0ysFYjRVnPwCJHE7v7uF_l1hI6qi6QBsA
	 * @throws WeixinReqException
	 */
	public static String getAccessToken(String appid, String appscret) throws WeixinReqException{
		String newAccessToken = "";
		AccessTokenReq atoken = new AccessTokenReq();
		atoken.setAppid(appid);
		atoken.setSecret(appscret);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(atoken);
		// 正常返回
		newAccessToken = result.getString("access_token");;
		return newAccessToken;
	}
	/**
	 * 获取权限令牌信息
	 * @param appid
	 * @param appscret
	 * @return
	 * @throws WeixinReqException
	 */
	public static TokenResult getAccessTokenAll(String appid, String appscret) throws WeixinReqException{
		AccessTokenReq atoken = new AccessTokenReq();
		atoken.setAppid(appid);
		atoken.setSecret(appscret);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(atoken);
		// 正常返回
		String token = result.getString("access_token");;
		Integer expires_in=result.getInteger("expires_in");
		return new TokenResult(token,expires_in);
	}
	
	/**
	 * 获取JSToken信息
	 * @param appid
	 * @param appscret
	 * @return
	 * @throws WeixinReqException
	 */
	public static TokenResult getJSTokenAll(String accessToken) throws WeixinReqException{
		JSTokenReq jstoken = new JSTokenReq();
		jstoken.setAccess_token(accessToken);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(jstoken);
		// 正常返回
		String token = result.getString("ticket");;
		Integer expires_in=result.getInteger("expires_in");
		return new TokenResult(token,expires_in);
	}
}
