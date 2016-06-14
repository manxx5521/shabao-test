package com.xiaoshabao.wechat.api.wxmedia;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxmedia.model.Article;
import com.xiaoshabao.wechat.api.wxmedia.model.DelMedia;
import com.xiaoshabao.wechat.api.wxmedia.model.DownloadMedia;
import com.xiaoshabao.wechat.api.wxmedia.model.DownloadNews;
import com.xiaoshabao.wechat.api.wxmedia.model.DownloadTempMedia;
import com.xiaoshabao.wechat.api.wxmedia.model.MediaCount;
import com.xiaoshabao.wechat.api.wxmedia.model.MediaList;
import com.xiaoshabao.wechat.api.wxmedia.model.UpdateNews;
import com.xiaoshabao.wechat.api.wxmedia.model.UploadMedia;
import com.xiaoshabao.wechat.api.wxmedia.model.UploadNews;
import com.xiaoshabao.wechat.api.wxmedia.model.UploadNewsImg;
import com.xiaoshabao.wechat.api.wxmedia.model.UploadTempMedia;
import com.xiaoshabao.wechat.api.wxmedia.result.DwonloadResult;
import com.xiaoshabao.wechat.api.wxmedia.result.MediaCountResult;
import com.xiaoshabao.wechat.api.wxmedia.result.NewsMediaList;
import com.xiaoshabao.wechat.api.wxmedia.result.NewsResult;
import com.xiaoshabao.wechat.api.wxmedia.result.OthersMediaList;
import com.xiaoshabao.wechat.api.wxmedia.result.UploadMediaResult;
import com.xiaoshabao.wechat.api.wxmedia.result.UploadTempMediaResult;

/**
 * 微信-素材接口
 */
public class MediaAPI {
	// private static Logger logger = LoggerFactory.getLogger(MediaAPI.class);

	/**
	 * 微信--上传临时文件接口
	 * 
	 * @param accessToken
	 * @param type
	 *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb） 。<br>
	 *            可以使用
	 *            {@link com.xiaoshabao.wechat.api.wxmedia.MediaType}
	 *            提供的类型
	 * @param fileNamePath
	 *            上传的文件目录
	 * @return
	 * @throws WexinReqException
	 */
	public static UploadTempMediaResult uploadTempMedia(String accessToken, String type,
			String fileNamePath) throws WexinReqException {
		UploadTempMedia uploadMedia = new UploadTempMedia();
		uploadMedia.setAccess_token(accessToken);
		uploadMedia.addFile(fileNamePath);
		uploadMedia.setType(type);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				uploadMedia);
		return JSON.toJavaObject(result, UploadTempMediaResult.class);
	}

	/**
	 * 下载-临时多媒体
	 * 
	 * @param accessToken
	 * @param media_id
	 * @param filePath
	 *            路径格式为 E:\\test 无需加最后一个分割符号
	 * @return
	 * @throws WexinReqException
	 */
	public static DwonloadResult downTempMedia(String accessToken,
			String media_id, String filePath) throws WexinReqException {
		DownloadTempMedia downloadMedia = new DownloadTempMedia();
		downloadMedia.setAccess_token(accessToken);
		downloadMedia.setFilePath(filePath);
		downloadMedia.setMedia_id(media_id);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				downloadMedia);
		return JSON.toJavaObject(result, DwonloadResult.class);
	}
	
	/**
	 * 永久素材 上传文件接口-其他类
	 * 
	 * @param accessToken
	 * @param type
	 *            媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）可以使用
	 *            {@link com.xiaoshabao.wechat.api.wxmedia.MediaType}
	 *            提供的类型
	 * @param fileNamePath
	 *            上传的文件目录
	 * @return
	 * @throws WexinReqException
	 */
	public static UploadMediaResult uploadMedia(String accessToken, String type,
			String fileNamePath) throws WexinReqException {
		UploadMedia upload = new UploadMedia();
		upload.setAccess_token(accessToken);
		upload.addFile(fileNamePath);
		upload.setType(type);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				upload);
		return JSON.toJavaObject(result, UploadMediaResult.class);
	}
	/**
	 * 上传永久图文-图片接口
	 * <P>本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下</P>
	 * @param accessToken
	 * @param filePath
	 *            上传文件路径
	 * @return 图片URL地址
	 * @throws WexinReqException
	 */
	public static String uploadNewsImg(String accessToken, String filePath)
			throws WexinReqException {
		UploadNewsImg upload = new UploadNewsImg();
		upload.setAccess_token(accessToken);
		upload.addFile(filePath);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				upload);
		return result.getString("url");
	}
	/**
	 * 上传永久图文消息素材
	 * @param accessToken 
	 * @param articles 上传的文本列表
	 * @return
	 * @throws WexinReqException
	 */
	public static String uploadNews(String accessToken, List<Article> articles)
			throws WexinReqException {
		UploadNews upload = new UploadNews();
		upload.setAccess_token(accessToken);
		upload.setArticles(articles);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				upload);
		return result.getString("media_id");
	}
	
	/**
	 * 下载永久图文消息素材
	 * @param accessToken 凭证
	 * @param media_id 素材id
	 * @return
	 * @throws WexinReqException
	 */
	public static NewsResult downloadNews(String accessToken, String media_id)
			throws WexinReqException {
		DownloadNews upload = new DownloadNews();
		upload.setAccess_token(accessToken);
		upload.setMedia_id(media_id);
		JSONObject result=WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		return JSON.toJavaObject(result, NewsResult.class);
	}
	
	/**
	 * 获取永久其他素材-不包括视频
	 * @param accessToken
	 * @param media_id
	 * @param filePath 响应文件后放到的目录
	 * @return
	 * @throws WexinReqException
	 */
	public static DwonloadResult downloadMeida(String accessToken, String media_id ,String filePath)
			throws WexinReqException {
		DownloadMedia downloadMedia = new DownloadMedia();
		downloadMedia.setAccess_token(accessToken);
		downloadMedia.setFilePath(filePath);
		downloadMedia.setMedia_id(media_id);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				downloadMedia);
		return JSON.toJavaObject(result, DwonloadResult.class);
	}
	
	/**
	 * 删除永久素材
	 * @param accessToken
	 * @param media_id
	 * @throws WexinReqException
	 */
	public static void delMeida(String accessToken, String media_id)
			throws WexinReqException {
		DelMedia upload = new DelMedia();
		upload.setAccess_token(accessToken);
		upload.setMedia_id(media_id);
		WeiXinReqService.getInstance().doWeinxinReqJson(
				upload);
	}
	
	/**
	 * 修改永久图文素材
	 * @param accessToken
	 * @param media_id 素材id
	 * @param index 第几篇文章，第一篇为0
	 * @param news
	 * @throws WexinReqException
	 */
	public static void updateNewsMeida(String accessToken,String media_id,Integer index,Article articles)
			throws WexinReqException {
		UpdateNews upload = new UpdateNews();
		upload.setAccess_token(accessToken);
		upload.setIndex(index);
		upload.setArticles(articles);
		upload.setMedia_id(media_id);
		WeiXinReqService.getInstance().doWeinxinReqJson(upload);
	}
	
	/**
	 * 获取永久数量
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	public static MediaCountResult getMediaCount(String accessToken)
			throws WexinReqException {
		MediaCount upload = new MediaCount();
		upload.setAccess_token(accessToken);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(
				upload);
		return JSONObject.toJavaObject(result, MediaCountResult.class);
	}
	
	/**
	 * 获取永久素材列表-获得JSONObject
	 * @param accessToken
	 * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * 			{@link com.xiaoshabao.wechat.api.wxmedia.MediaType}类型
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return
	 * @throws WexinReqException
	 */
	public static JSONObject getMediaList(String accessToken,String type,Integer offset,Integer count)
			throws WexinReqException {
		MediaList upload = new MediaList();
		upload.setAccess_token(accessToken);
		upload.setType(type);
		upload.setOffset(offset);
		upload.setCount(count);
		return WeiXinReqService.getInstance().doWeinxinReqJson(upload);
	}
	
	/**
	 * 获取永久素材列表-获得图文素材列表
	 * 
	 * @param accessToken
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return NewsMediaList 素材列表
	 * @throws WexinReqException
	 */
	public static NewsMediaList getMediaListByNews(String accessToken,Integer offset,Integer count)
			throws WexinReqException {
		JSONObject result=getMediaList(accessToken,"news",offset,count);
		return JSONObject.toJavaObject(result, NewsMediaList.class);
	}
	
	/**
	 * 获取永久素材列表-获得其他素材列表
	 * 
	 * @param accessToken
	 * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）
	 * 	{@link com.xiaoshabao.wechat.api.wxmedia.MediaType}类型
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return OthersMediaList 素材列表
	 * @throws WexinReqException
	 */
	public static OthersMediaList getMediaListByOthers(String accessToken,String type,Integer offset,Integer count)
			throws WexinReqException {
		JSONObject result=getMediaList(accessToken,type,offset,count);
		return JSONObject.toJavaObject(result, OthersMediaList.class);
	}
	
	

}
