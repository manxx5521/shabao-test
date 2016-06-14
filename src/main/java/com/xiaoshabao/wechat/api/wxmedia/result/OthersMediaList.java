package com.xiaoshabao.wechat.api.wxmedia.result;

import java.util.ArrayList;
import java.util.List;


/**
 * 其他素材列表
 */
public class OthersMediaList {
	private String total_count;
	private String item_count;
	private List<OthersMediaItem> item = new ArrayList<OthersMediaItem>();
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public String getItem_count() {
		return item_count;
	}
	public void setItem_count(String item_count) {
		this.item_count = item_count;
	}
	public List<OthersMediaItem> getItem() {
		return item;
	}
	public void setItem(List<OthersMediaItem> item) {
		this.item = item;
	}
	
}
