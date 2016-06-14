package com.xiaoshabao.wechat.api.wxaccount;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxaccount.model.QrcodeActionInfo;
import com.xiaoshabao.wechat.api.wxaccount.model.QrcodeCreate;
import com.xiaoshabao.wechat.api.wxaccount.model.QrcodeScene;
import com.xiaoshabao.wechat.api.wxaccount.model.ShortUrlCreate;
import com.xiaoshabao.wechat.api.wxaccount.result.QrcodeResult;

public class AccountAPI {
	/**
	 * 二维码类型，QR_SCENE为临时, , 
	 */
	public static final String QRCODE_TYPE_SCENE = "QR_SCENE";
	/**
	 * 二维码类型，QR_LIMIT_SCENE为永久
	 */
	public static final String QRCODE_TYPE_LIMIT = "QR_LIMIT_SCENE";
	/**
	 * 二维码类型，QR_LIMIT_STR_SCENE为永久的字符串参数值
	 */
	public static final String QRCODE_TYPE_LIMIT_STR = "QR_LIMIT_STR_SCENE";
	/**
	 * 代表长链接转短链接
	 */
	public static final String SHORT_URL_ACTION = "long2short";
	
	/**
	 * 创建二维码
	 * @param accessToken
	 * @param scene_id 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param scene_str 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	 * @param action_name 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值<br>
	 * 	可使用API中提供的静态变量 QRCODE_TYPE_*
	 * @param expire_seconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。临时二维码有效
	 * @return
	 * @throws WexinReqException
	 */
	public static QrcodeResult createQrcode(String accessToken, String scene_id,String scene_str,
			String action_name, String expire_seconds) throws WexinReqException {
		QrcodeCreate qrcodeCreate = new QrcodeCreate();
		qrcodeCreate.setAccess_token(accessToken);
		QrcodeActionInfo q = new QrcodeActionInfo();
		QrcodeScene ss = new QrcodeScene();
		ss.setScene_str(scene_str);
		ss.setScene_id(scene_id);
		q.setScene(ss);
		qrcodeCreate.setAction_info(q);
		qrcodeCreate.setExpire_seconds(expire_seconds);
		qrcodeCreate.setAction_name(action_name);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(qrcodeCreate);
		return JSON.toJavaObject(result, QrcodeResult.class);
	}
	
	/**
	 * 获取短链接信息
	 * @param accessToken
	 * @param longUrl 长连接，支持http://、https://、weixin://wxpay 格式
	 * @return 短链接
	 * @throws WexinReqException
	 */
	public static String getShortUrl(String accessToken,String longUrl) throws WexinReqException{
		ShortUrlCreate uc = new ShortUrlCreate();
		uc.setAccess_token(accessToken);
		uc.setLong_url(longUrl);
		uc.setAction(SHORT_URL_ACTION);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(uc);
		return result.getString("short_url");
	}
}
