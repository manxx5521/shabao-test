package com.xiaoshabao.wechat.api.wxuser;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxuser.model.UserBaseInfoGet;
import com.xiaoshabao.wechat.api.wxuser.model.UserBaseInfoGetList;
import com.xiaoshabao.wechat.api.wxuser.model.UserInfoGetBean;
import com.xiaoshabao.wechat.api.wxuser.model.UserListGet;
import com.xiaoshabao.wechat.api.wxuser.model.UserRemarkSet;
import com.xiaoshabao.wechat.api.wxuser.result.UserBaseInfo;
import com.xiaoshabao.wechat.api.wxuser.result.UserOpenIDList;
/**
 * 微信用户API
 */
public class UserAPI {
	
	/**
	 * 获得用户openId列表-拉取10000条数据
	 * @param accessToken
	 * @throws WexinReqException 
	 */
	public static UserOpenIDList getUserOpenIdList(String accessToken) throws WexinReqException{
		return getUserOpenIdList(accessToken,null,false);
	}
	/**
	 * 获得用户openId列表-拉取10000条数据
	 * @param accessToken
	 * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取，有值时从此id的下一个id开始拉取
	 * @throws WexinReqException 
	 */
	public static UserOpenIDList getUserOpenIdList(String accessToken,String next_openid) throws WexinReqException{
		return getUserOpenIdList(accessToken,next_openid,false);
	}
	/**
	 * 获所有用户openId列表
	 * @param accessToken
	 * @throws WexinReqException 
	 */
	public static UserOpenIDList getUserOpenIdListAll(String accessToken) throws WexinReqException{
		return getUserOpenIdList(accessToken,null,true);
	}
	/**
	 * 获得所有用户openId列表
	 * @param accessToken
	 * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取，有值时从此id的下一个id开始拉取
	 * @throws WexinReqException 
	 */
	public static UserOpenIDList getUserOpenIdListAll(String accessToken,String next_openid) throws WexinReqException{
		return getUserOpenIdList(accessToken,next_openid,true);
	}
	/**
	 * 拉取用户列表
	 * @param accessToken 凭证
	 * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取，有值时从此id的下一个id开始拉取
	 * @param isAll 是否拉取全部，true全部
	 * @throws WexinReqException
	 */
	public static UserOpenIDList getUserOpenIdList(String accessToken,String next_openid,boolean isAll) throws WexinReqException{
		UserListGet upload = new UserListGet();
		upload.setNext_openid(next_openid);
		upload.setAccess_token(accessToken);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		int total=result.getIntValue("total");//关注用户总数
		int count=result.getIntValue("count");//拉取用户数
		String strNextOpenId = result.getString("next_openid");
		List<String> openList=null;
		UserOpenIDList userList=new UserOpenIDList();
		userList.setTotal(total);
		userList.setCount(count);
		// 如果没有数据直接返回
		if (count < 1) {
			return userList;
		}
		JSONObject data = result.getJSONObject("data");
		String openStr=data.getString("openid");
		openList=JSONArray.parseArray(openStr, String.class);
		//如果拉取全部
		if(!isAll){
			userList.setOpenidList(openList);
			return userList;
		}
		
		if (strNextOpenId != null) {
			UserOpenIDList currentBean = getUserOpenIdList(accessToken,
					strNextOpenId, true);
			openList.addAll(currentBean.getOpenidList());
			count += currentBean.getCount();
		}
		userList.setCount(count);
		userList.setOpenidList(openList);
		return userList;
	}
	
	/**
	 * 设置用户备注
	 * @param accessToken
	 * @param openid
	 * @param remark 新的备注名，长度必须小于30字符
	 * @throws WexinReqException
	 */
	public static void setUserRemark(String accessToken,String openid,String remark) throws WexinReqException{
		UserRemarkSet upload = new UserRemarkSet();
		upload.setAccess_token(accessToken);
		upload.setOpenid(openid);
		upload.setRemark(remark);
		WeiXinReqService.getInstance().doWeinxinReqJson(upload);
	}
	
	/**
	 * 获取用户基本信息
	 * @param accessToken
	 * @param openid
	 * @param lang	返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 * @throws WexinReqException
	 */
	public static UserBaseInfo getUserBaseInfo(String accessToken,String openid,String lang) throws WexinReqException{
		UserBaseInfoGet upload = new UserBaseInfoGet();
		upload.setAccess_token(accessToken);
		upload.setOpenid(openid);
		upload.setLang(lang);
		JSONObject result=WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		return JSONObject.toJavaObject(result, UserBaseInfo.class);
	}
	
	/**
	 * 获取用户基本信息列表
	 * @param accessToken
	 * @param list 用户列表，最大100
	 * @return
	 * @throws WexinReqException
	 */
	public static List<UserBaseInfo> getUserBaseInfoList(String accessToken,List<UserInfoGetBean> list) throws WexinReqException{
		UserBaseInfoGetList upload = new UserBaseInfoGetList();
		upload.setAccess_token(accessToken);
		upload.setUser_list(list);
		JSONObject result=WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		String str=result.getString("user_info_list");
		return JSONArray.parseArray(str, UserBaseInfo.class);
	}
	

}
