package com.xiaoshabao.wechat.api.wxmedia.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 删除永久素材
 */
@ReqType("mediaList")
public class MediaList extends WeixinReqParam {
	/**
	 * 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * {@link com.xiaoshabao.wechat.api.wxmedia.MediaType}类型
	 */
	private String type;
	/**
	 * 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 */
	private Integer offset=0;
	/**
	 * 返回素材的数量，取值在1到20之间
	 */
	private Integer count;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
