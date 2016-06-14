package com.xiaoshabao.wechat.api.core.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.config.WeiXinConstant;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.handler.WeiXinReqHandler;
import com.xiaoshabao.wechat.api.core.http.HttpClientManager;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 默认的处理微信请求handler
 * <p>
 * 方法使用HttpClient来发送请求，使用HTTPS方式
 * </p>
 */
public class WeixinReqDefaultHandler implements WeiXinReqHandler {

	private static Logger logger = Logger
			.getLogger(WeixinReqDefaultHandler.class);
	
	/**
	 * 默认的请求方法，使用HTTPS方式
	 */
	@Override
	public String doRequest(WeixinReqParam weixinReqParam,WeixinReqConfig objConfig)
			throws WexinReqException {
		logger.info("使用WeixinReqDefaultHandler 处理请求");
		try {
			String reqUrl = objConfig.getUrl();
			String method = objConfig.getMethod();
			String datatype = objConfig.getDataType();
			// 处理JSON请求,只对post有效
			if (WeiXinConstant.DATA_TYPE_JSON.equalsIgnoreCase(datatype)&&WeiXinConstant.REQUEST_POST.equalsIgnoreCase(method)) {
				String jsonData = JSONObject.toJSONString(weixinReqParam);
				StringBuffer sb=new StringBuffer(reqUrl);
				sb.append("&").append(weixinReqParam.toJsonParams());
				return HttpClientManager.getInstance().doPostSSLByJSON(sb.toString(),jsonData);
			}

			// 处理字符串请求
			if (WeiXinConstant.DATA_TYPE_STRING.equalsIgnoreCase(datatype)) {
				//处理post方式
				if (WeiXinConstant.REQUEST_POST.equalsIgnoreCase(method)) {
					return HttpClientManager.getInstance().doPostSSL(reqUrl,
							weixinReqParam.toParams());
				}
				//处理get方式,和其他方式
				StringBuffer sb=new StringBuffer(reqUrl);
				sb.append("&").append(weixinReqParam.toParams());
				return HttpClientManager.getInstance().doGet(sb.toString());
			}

			// 使用Map方式解析
			String jsonData = JSONObject.toJSONString(weixinReqParam, true);
			Map<String, Object> params = JSONObject.parseObject(jsonData);
			//使用map的post方式解析
			StringBuffer sb=new StringBuffer(reqUrl);
			sb.append("&").append(weixinReqParam.toParams());
			reqUrl=sb.toString();
			if (WeiXinConstant.REQUEST_POST.equalsIgnoreCase(method)) {
				return HttpClientManager.getInstance().doPostSSL(reqUrl, params);
			}
			//使用map的get方式解析
			return HttpClientManager.getInstance().doGetSSL(reqUrl, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WexinReqException("WeixinReqDefaultHandler 进行操作是出现未知异常");
		}
	}

}
