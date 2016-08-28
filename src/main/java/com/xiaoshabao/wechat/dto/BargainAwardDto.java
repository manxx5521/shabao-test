package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.BargainJoinEntity;
import com.xiaoshabao.wechat.entity.QrcodeEntity;

/**
 * 兑奖二维码
 */
public class BargainAwardDto extends BargainJoinEntity{
	/**
	 * 二维码信息
	 */
	private QrcodeEntity qrcode;
	
	/** 使用模版 ***/
	private String template;
	/**
	 * 是否要重新换取 1换取
	 */
	private Integer flag;
	/**
	 * 参数
	 */
	private String params;

	public QrcodeEntity getQrcode() {
		return qrcode;
	}

	public void setQrcode(QrcodeEntity qrcode) {
		this.qrcode = qrcode;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
}
