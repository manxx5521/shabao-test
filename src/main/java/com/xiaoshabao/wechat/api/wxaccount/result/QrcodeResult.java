package com.xiaoshabao.wechat.api.wxaccount.result;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.xiaoshabao.wechat.api.core.exception.WeixinReqException;

/**
 * 微信请求二维码返回信息
 */
public class QrcodeResult {
	/**
	 * 凭借ticket可以通过下边的连接换取二维码和展现<br>
	 * https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
	 */
	private String ticket;
	/**
	 * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
	 */
	private Integer expire_seconds;
	/**
	 * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 */
	private String url;
	
	/**
	 * 二维码展现下载URL
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getQRurl(){
		try {
			String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode(ticket,"utf-8");
			return url;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new WeixinReqException("url解析失败",e);
		}
		
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(Integer expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	/**
	 * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
