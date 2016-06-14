package com.xiaoshabao.wechat.api.core.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.config.WeiXinConstant;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.handler.WeiXinReqHandler;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;



/**
 * 获取微信接口的信息
 */
public class WeiXinReqService {
	private static Logger logger = Logger
			.getLogger(WeiXinReqService.class);

	private static WeiXinReqService weiXinReqUtil = null;


	private WeiXinReqService() throws WexinReqException {
		try {
			WeiXinReqUtil.initReqConfig("weixin-reqcongfig.xml");
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * 获取公共的调用处理
	 * @return
	 * @throws WexinReqException 
	 */
	public static WeiXinReqService getInstance() throws WexinReqException {
		if (weiXinReqUtil == null) {
			// 同步块，线程安全的创建实例
			synchronized (WeiXinReqService.class) {
				// 再次检查实例是否存在，如果不存在才真正的创建实例
				if (weiXinReqUtil == null) {
					weiXinReqUtil = new WeiXinReqService();
				}
			}
		}
		return weiXinReqUtil;
	}

	/**
	 * 传入请求的参数，获取对应接口返回信息
	 * @param weixinReqParam
	 * @return	String 返回字符串结果，没有对信息正确性验证
	 * @throws WexinReqException
	 */
	public String doWeinxinReq(WeixinReqParam weixinReqParam)
			throws WexinReqException {
		if (!weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
			logger.info("没有解析到 ReqType注解");;
			throw new WexinReqException("传入的参数不对");
		}
		
		ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
		WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
		if (objConfig == null) {
			logger.info("解析weixin-reqconfig.xml文件失败,获得不到对应信息");
			throw new WexinReqException("没有找到相应配置信息");
		}
		WeiXinReqHandler handler = WeiXinReqUtil.getMappingHander(objConfig.getMappingHandler());
		return handler.doRequest(weixinReqParam,objConfig);
	}
	
	/**
	 * 发送请求，返回json对象
	 * @param weixinReqParam
	 * @return	JSONObject 已经验证正确
	 * @throws WexinReqException 
	 */
	public JSONObject doWeinxinReqJson(WeixinReqParam weixinReqParam) throws WexinReqException{
		String strResult = this.doWeinxinReq(weixinReqParam);
		JSONObject result=JSON.parseObject(strResult);
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
		if(error !=null && Integer.parseInt(error.toString())!=0){
			throw new WexinReqException(result.toString());
		}
		return result;
	}

}
