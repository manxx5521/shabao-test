package com.xiaoshabao.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.wechat.api.core.http.HttpClientManager;
import com.xiaoshabao.wechat.api.wxbase.AuthAPI;
import com.xiaoshabao.wechat.component.WechatConfig;

/**
 * 分享统一控制
 * 统一分享控制暂时不用 
 */
@Deprecated
@Controller
@RequestMapping("/wechat/share")
public class WechatShareController {
	/** 内部响应标识 **/
	private final static String REQ_STATE="true";
	@Autowired
	private WechatConfig config;
	/**
	 * 直接跳转界面，未关注的跳转关注界面
	 * @param model
	 * @param url
	 * @param appid
	 * @param accountId
	 * @param scope
	 * @return
	 */
	@RequestMapping("/url")
	public ModelAndView doShare(HttpServletRequest request,ModelMap model,String url,String appid,String accountId,String scope){
		String reurl;
		StringBuffer sb=new StringBuffer(config.getDomain());
		String contextpath=request.getSession().getServletContext().getContextPath();
		if(StringUtils.isNotEmpty(contextpath)){
			sb.append(contextpath);
		}
		sb.append("/wechat/share/req");
		if(!scope.equals(AuthAPI.AUTHURL_SCOP_SNSAPI_USERINFO)){
			reurl=AuthAPI.getAuthURL_info(appid, sb.toString(), accountId);
		}else{
			reurl=AuthAPI.getAuthURL_base(appid, sb.toString(), accountId);
		}
		HttpClientManager http=HttpClientManager.getInstance();
		//请求auth路径，自动登陆
		String resultString=http.doPost(reurl);
		
		if(resultString.equals(REQ_STATE)){
			return new ModelAndView(url);
		}
		return new ModelAndView("/wechat/share/req");
	}
	
	/**
	 * 上边的跳转，验证是否关注
	 * @param model
	 * @return
	 */
	@RequestMapping("/req")
	@ResponseBody
	public String getShareInfo(ModelMap model){
		return REQ_STATE;
	}

}
