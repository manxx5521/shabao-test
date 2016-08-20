package com.xiaoshabao.wechat.enums;

import com.xiaoshabao.baseframe.enums.ErrorInterface;

public enum ErrorWechatEnum implements ErrorInterface{
	/** 登录错误 */
	LOGIN_ERROR(9001,"自动登录失败，重新打开微信号或者是重新关注"),
	/** 砍价活动不存在 */
	BARGAIN_NO_HAVE(9011,"未正常获得砍价信息"),
	/** 当前砍价活动未开始  */
	BARGAIN_START_ERROR(9012,"当前砍价活动未开始"),
	/** 当前砍价活动已经结束 */
	BARGAIN_END_ERROR(9013,"当前砍价活动已经结束"),
	/** 砍价失败，砍价信息未能正常记录 */
	BARGAIN_UPDATE_ERROR(9014,"砍价失败，砍价信息未能正常记录"),
	/** 最小价格错误*/
	BARGAIN_MIN_PRICE(9015,"当前价格已经是最小价格不能砍价了"),
	/** 重复砍价 */
	BARGAIN_REPEAT(9016,"你已经参加过本次砍价了"),
	
	/** 未能正常获得文章信息 */
	ARTICLE_NO(9020,"未能正常获得文章信息"),
	ARTICLE_SAVE_ERROR(9021,"保存数据错误，请重试！"),
	
	ERROR(1,"系统错误，请重试"),
	INNER_ERROR(2,"系统内部错误，请重试");
	
	/** 代码 */
	private int code;
	/** 错误信息 */
	private String message;
	
	private ErrorWechatEnum(int code,String message){
		this.code=code;
		this.message=message;
	}
	/**
	 * 代码
	 * @return
	 */
	public int getCode() {
		return code;
	}
	/**
	 * 错误信息
	 * @return
	 */
	public String getMessage() {
		return message;
	}

}
