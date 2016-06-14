package com.xiaoshabao.wechat.api.core.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.config.WeiXinConstant;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.handler.WeiXinReqHandler;
import com.xiaoshabao.wechat.api.core.http.HttpClientManager;
import com.xiaoshabao.wechat.api.core.req.WeixinDownParam;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 通过HTTPS的get方式下载文件
 */
public class WeixinReqDownHandler implements WeiXinReqHandler {

	private static Logger logger = Logger.getLogger(WeixinReqDownHandler.class);
	
	@Override
	public String doRequest(WeixinReqParam weixinReqParam,
			WeixinReqConfig objConfig) throws WexinReqException {
		logger.info("使用WeixinReqDownHandler 下载文件文件");
		try {
			String method = objConfig.getMethod();
			WeixinDownParam upParams=(WeixinDownParam)weixinReqParam;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("media_id", upParams.getMedia_id());
			params.put("access_token", upParams.getAccess_token());
			HttpClientManager manager=HttpClientManager.getInstance();
			if(method.equalsIgnoreCase(WeiXinConstant.REQUEST_GET)){
				String fileName=manager.getFileGetSSL(objConfig.getUrl(), params, upParams.getFilePath());
				Map<String,String> result=new HashMap<String, String>();
				if(fileName==null){
					result.put("errcode", "-100");
				}
				result.put("fileName", fileName);
				result.put("filePath", upParams.getFilePath());
				return JSONObject.toJSONString(result);
			}else{
				return manager.getFilePostSSL(objConfig.getUrl(), params, upParams.getFilePath());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new WexinReqException("WeixinReqDownHandler 进行操作是出现未知异常");
		}
	}

}
