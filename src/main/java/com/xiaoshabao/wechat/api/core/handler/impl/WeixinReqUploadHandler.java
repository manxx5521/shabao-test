package com.xiaoshabao.wechat.api.core.handler.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.xiaoshabao.wechat.api.core.exception.WeixinReqException;
import com.xiaoshabao.wechat.api.core.handler.WeiXinReqHandler;
import com.xiaoshabao.wechat.api.core.http.HttpClientManager;
import com.xiaoshabao.wechat.api.core.req.WeixinReqConfig;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;
import com.xiaoshabao.wechat.api.core.req.WeixinUploadParam;

/**
 * 微信上传文件方法<br>
 * 此方法使用HTTPS，除了URL外无需传别的参数
 */
public class WeixinReqUploadHandler implements WeiXinReqHandler {

	private static Logger logger = Logger.getLogger(WeixinReqUploadHandler.class);
	@Override
	public String doRequest(WeixinReqParam weixinReqParam,
			WeixinReqConfig objConfig) throws WeixinReqException {
		logger.info("使用WeixinUploadHandler 处理上传文件");
		try {
			WeixinUploadParam upParams=(WeixinUploadParam)weixinReqParam;
			List<String> pathList =upParams.getFilePathName();
			if(pathList.isEmpty()){
				throw new WeixinReqException("文件为空");
			}
			List<File> fileList=new ArrayList<File>();
			for(String path:pathList){
				fileList.add(new File(path));
			}
			Map<String,Object> map=upParams.getParams();
			if(map==null||map.isEmpty()){
				map=new HashMap<String, Object>();
			}
			map.put("access_token", upParams.getAccess_token());
			String type=upParams.getType();
			if(StringUtils.isNotEmpty(type)){
				map.put("type", upParams.getType());
			}
			return HttpClientManager.getInstance().doPostSSL(objConfig.getUrl(),map, fileList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WeixinReqException("WeixinReqUploadHandler 进行操作是出现未知异常");
		}
		
	}

}
