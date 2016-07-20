package com.xiaoshabao.wechat.api.wxbase;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxbase.model.AuthBaseReq;
import com.xiaoshabao.wechat.api.wxbase.result.AuthBaseInfo;

/**
 * 网页授权相关
 */
public class AuthAPI {
	/**
	 * 获得基本信息 包括网页授权token和opendi
	 * @param appid
	 * @param secret
	 * @param code 网页获得的code
	 * @return
	 */
	public static AuthBaseInfo getBaseInfo(String appid, String secret, String code){
		AuthBaseReq req=new AuthBaseReq();
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(req);
		// 正常返回
		return JSONObject.toJavaObject(result, AuthBaseInfo.class);
	}

}
