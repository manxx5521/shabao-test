package com.xiaoshabao.wechat.api.wxbase;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxbase.model.ServiceIP;

/**
 * 微信--token信息
 */
public class ServiceIpAPI {

	/**
	 * 返回的信息名称
	 */
	public final static String RETURN_INFO_NAME = "ip_list";
	
	/**
	 * 获取服务的ip列表信息
	 * @param accessToke
	 * @return 返回地址列表[101.226.62.77, 101.226.62.78, 101.226.62.79]
	 * @throws WexinReqException
	 */
	public static List<String> getServiceIpList(String accessToke) throws WexinReqException{
		ServiceIP param = new ServiceIP();
		param.setAccess_token(accessToke);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(param);
		List<String> lstServiceIp = null;
		JSONArray infoArray = result.getJSONArray(RETURN_INFO_NAME);
		lstServiceIp = new ArrayList<String>(infoArray.size());
		for (int i = 0; i < infoArray.size(); i++) {
			lstServiceIp.add(infoArray.getString(i));
		}
		return lstServiceIp;
	}
	
}
