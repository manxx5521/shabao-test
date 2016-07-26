package com.xiaoshabao.wechat.controller;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.webframe.component.ContextHolderUtils;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.api.wxmedia.MediaAPI;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.component.WechatContextHolder;

@Controller
@RequestMapping("/wechat")
public class WechatFileController extends AbstractController{
	@Resource(name = "tokenManager")
	private TokenManager tokenManager;
	
	/**
	 * 微信相机图片下载，下载临时素材
	 * @param fileid
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/file/{fileid}/get")
	@ResponseBody
	public AjaxResult downloadfFile(@PathVariable("fileid")String fileid,String path) {
		String realPath = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("resources/wechat/upload");
		String separator=File.separator;
		if(!realPath.endsWith(separator)){
			realPath=realPath+separator;
		}
		Integer accountId=WechatContextHolder.getWxAccountId();
		try {
			String accessTonken=tokenManager.getAccessToken(accountId).getAccessToken();
			if(StringUtils.isNotEmpty(path)){
				realPath+=path;
			}
			
			String fileName=MediaAPI.downTempMedia(accessTonken, fileid, realPath).getFileName();
			String resultFile=fileName;
			if(StringUtils.isNotEmpty(path)){
				resultFile=path+"/"+fileName;
			}
			return new AjaxResult(true,fileName,resultFile);
		} catch (Exception ex) {
			return new AjaxResult(true,"图片下载失败");
		}
	}
}
