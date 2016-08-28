package com.xiaoshabao.wechat.dao;

import com.xiaoshabao.wechat.entity.QrcodeEntity;
import com.xiaoshabao.wechat.entity.QrcodeRelEntity;

/**
 * 二维码操作
 */
public interface QrcodeDao {
	/**
	 * 先添加映射关系
	 * @param qrcodeRel
	 * @return
	 */
	public int insertQrcodeRel(QrcodeRelEntity qrcodeRel);
	/**
	 * 插入二维码信息
	 * @param qrcode
	 * @return
	 */
	public int insertQrcode(QrcodeEntity qrcode);
}
