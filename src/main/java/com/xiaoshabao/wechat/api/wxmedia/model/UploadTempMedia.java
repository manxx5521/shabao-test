package com.xiaoshabao.wechat.api.wxmedia.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinUploadParam;


/**
 * 上传-永久素材
 * <p>
 * 公众号可调用本接口来上传图片、语音、视频等文件到微信服务器， 上传后服务器会返回对应的media_id，公众号此后可根据该media_id来获取多媒体
 * </p>
 */
@ReqType("uploadTempMedia")
public class UploadTempMedia extends WeixinUploadParam {

}
