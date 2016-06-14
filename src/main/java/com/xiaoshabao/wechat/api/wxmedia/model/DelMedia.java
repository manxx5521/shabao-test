package com.xiaoshabao.wechat.api.wxmedia.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 删除永久素材
 */
@ReqType("delMedia")
public class DelMedia extends WeixinReqParam {
	/**
	 * 素材的id
	 */
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
