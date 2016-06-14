package com.xiaoshabao.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
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
 * 
 * @see com.wechat.controller.weixin.WeixinServlet WeixinServlet 这个类是柳风写的接入可参考
 */
@Controller
public class WechatController {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "wechatService")
	private WechatService wechatService;

	/**
	 * 微信帐号接入方法<br>
	 * 确认请求来自微信服务器,然后返回微信想要的信息
	 */
	@RequestMapping(value = "/weixinServlet/{uid}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	public void doGet(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String uid) throws ServletException, IOException {
		// 初次生成的token
		String token = "xiaoshabao123456";
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		logger.info("singnature:" + signature + "---echostr:" + echostr
				+ "----timestamp:" + timestamp + "---nonce:" + nonce);
		String[] str = { token, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = DigestUtils.sha1Hex(bigStr);// 使用commos包进行SHA1加密

		// 确认请求来至微信
		if (digest.equals(signature)) {
			response.getWriter().print(echostr);
		}
	}

	/**
	 * 响应微信事件的方法<br>
	 * 比如关注，发送消息等
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/weixinServlet/{uid}", method = RequestMethod.POST, produces = "application/xml; charset=utf-8")
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request) throws IOException, Exception {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		// request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		logger.info("开始调用微信服务 ！");
		// 返回值
		String respMessage = null;
		// xml请求解析
		Map<String, String> requestMap = MessageUtil.parseXml(request);
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		String msgType = requestMap.get("MsgType");

		// 文本消息
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			respMessage = wechatService.textService(fromUserName, toUserName,
					requestMap.get("Content"));
		}
		// 事件推送
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			String event = requestMap.get("Event");
			// 如果是点击自定义菜单
			if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				respMessage = wechatService.eventService(fromUserName,
						toUserName, event, requestMap.get("EventKey"));
			} else {
				respMessage = wechatService.eventService(fromUserName,
						toUserName, event, null);
			}
		} else {
			respMessage = wechatService
					.defaultService(fromUserName, toUserName);
		}

		logger.info("微信服务返回内容:" + respMessage);
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
}
