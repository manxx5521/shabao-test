package com.xiaoshabao.wechat.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoshabao.wechat.service.WechatService;
import com.xiaoshabao.wechat.util.MessageUtil;

/**
 * 微信服务核心控制类<br>
 * 这个类用来的{@link #doGet(HttpServletRequest, HttpServletResponse) doGet}
 * 方法用来接入微信帐号, {@link #wechatPost(HttpServletResponse, HttpServletRequest)
 * wechatPost}方法用来相应微信的各种事件
 */
@Controller
public class WechatController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "wechatService")
	private WechatService wechatService;

	/**
	 * * 微信帐号接入方法<br>
	 * 确认请求来自微信服务器,然后返回微信想要的信息
	 * @param uid
	 * @param signature 微信加密签名
	 * @param echostr 随机字符串
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 */
	@RequestMapping(value = "/wechat/service/{uid}/dispatch", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	public void doGet(HttpServletResponse response,@PathVariable("uid") Integer uid,
			String signature,String msg_signature, String echostr, String timestamp, String nonce){
		// 初次生成的token
		logger.info("来自微信的接入 singnature:{},echostr:{},timestamp:{},nonce:{}",signature,echostr,timestamp,nonce);
		try {
			String result= wechatService.getIn(uid, signature,msg_signature, echostr, timestamp, nonce);
			response.getWriter().print(result);
		} catch (Exception e) {
			logger.error("接入异常",e);
		}
	}

	/**
	 * 响应微信事件的方法<br>
	 * 比如关注，发送消息等
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/wechat/service/{uid}/dispatch", method = RequestMethod.POST, produces = "application/xml; charset=utf-8")
	public void wechatPost(HttpServletResponse response,HttpServletRequest request,@PathVariable("uid") Integer uid,String nonce) {
		PrintWriter out = null;
		try {
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			// request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			logger.info("开始响应微信服务 ！");
			// 返回值
			String respMessage = null;
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			respMessage = wechatService.coreService(uid,requestMap,nonce);
			
			logger.info("结束响应微信服务返回内容:" + respMessage);
			out = response.getWriter();
			out.print(respMessage);
			out.close();
		} catch (Exception e) {
			logger.error("响应微信请求失败,帐号：{}",uid,e);
			out.print("fail");
			out.close();
		}
	}
}
