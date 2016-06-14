package com.xiaoshabao.wechat.api.wxuser;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxuser.model.Group;
import com.xiaoshabao.wechat.api.wxuser.model.GroupCreate;
import com.xiaoshabao.wechat.api.wxuser.model.GroupDelete;
import com.xiaoshabao.wechat.api.wxuser.model.GroupGet;
import com.xiaoshabao.wechat.api.wxuser.model.GroupGetId;
import com.xiaoshabao.wechat.api.wxuser.model.GroupMoveUser;
import com.xiaoshabao.wechat.api.wxuser.model.GroupMoveUserById;
import com.xiaoshabao.wechat.api.wxuser.model.GroupUpdate;

/**
 * 用户分组
 */
public class GroupAPI {
	
	/**
	 * 创建分组信息
	 * <p>一个公众账号，最多支持创建100个分组</p>
	 * @param accessToken
	 * @param groupName 分组名字(30个字符以内)
	 * @return GroupCreate 返回分组的id和name
	 * @throws WexinReqException
	 */
	public static GroupCreate createGroup(String accessToken ,String groupName ) throws WexinReqException{
		GroupCreate upload = new GroupCreate();
		upload.setAccess_token(accessToken);
		Group group = new Group();
		group.setName(groupName);
		upload.setGroup(group);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		return JSONObject.toJavaObject(result, GroupCreate.class);
	}
	
	/**
	 * 获取所有的分组信息
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	public static List<Group> getGroup(String accessToken) throws WexinReqException{
		GroupGet upload = new GroupGet();
		upload.setAccess_token(accessToken);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		List<Group> lstGroup = null;
		JSONArray infoArray = result.getJSONArray("groups");
		lstGroup = new ArrayList<Group>(infoArray.size());
		for(int i=0;i<infoArray.size();i++){
			Group group =JSONObject.toJavaObject(infoArray.getJSONObject(i), Group.class);
			if(!"未分组".equals(group.getName())){
				lstGroup.add(group);
			}
		}
		return lstGroup;
	}
	
	/**
	 * 获取用户分组id
	 * @param accessToken
	 * @param openid
	 * @return String 用户所在组的id
	 * @throws WexinReqException
	 */
	public static String getUserGroup(String accessToken,String openid) throws WexinReqException{
		GroupGetId g = new GroupGetId();
		g.setAccess_token(accessToken);
		g.setOpenid(openid);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(g);
		String groupId = "";
		groupId = result.getString("groupid");
		return groupId;
	}
	
	/**
	 * 更新分组名
	 * @param accessToken
	 * @param groupId
	 * @param groupNewName 分组名(30个字符以内)
	 * @throws WexinReqException
	 */
	public static void updateGroup(String accessToken,String groupId,String groupNewName) throws WexinReqException{
		GroupUpdate groupUpdate = new GroupUpdate();
		groupUpdate.setAccess_token(accessToken);
		Group g = new Group();
		g.setId(groupId);
		g.setName(groupNewName);
		groupUpdate.setGroup(g);
		WeiXinReqService.getInstance().doWeinxinReqJson(groupUpdate);
	}
	
	/**
	 * 移动用户分组
	 * @param accessToken
	 * @param openid
	 * @param to_groupid
	 * @throws WexinReqException
	 */
	public static void moveUserGroup(String accessToken,String openid,String to_groupid) throws WexinReqException{
		GroupMoveUserById upload = new GroupMoveUserById();
		upload.setAccess_token(accessToken);
		upload.setOpenid(openid);
		upload.setTo_groupid(to_groupid);
		WeiXinReqService.getInstance().doWeinxinReqJson(upload);
	}
	
	/**
	 * 批量移动用户分组
	 * @param accessToken
	 * @param openid_list
	 * @param to_groupid
	 * @throws WexinReqException
	 */
	public static void moveUserGroup(String accessToken,List<String> openid_list,String to_groupid) throws WexinReqException{
		GroupMoveUser upload = new GroupMoveUser();
		upload.setAccess_token(accessToken);
		upload.setOpenid_list(openid_list);
		upload.setTo_groupid(to_groupid);
		WeiXinReqService.getInstance().doWeinxinReqJson(upload);
	}
	
	/**
	 * 删除用户分组
	 * @param accessToken 访问凭证
	 * @param deletegGroupid 删除的分组ID
	 */
	public static void deleteGroup(String accessToken,String deletegGroupid) throws WexinReqException{
		GroupDelete group = new GroupDelete();
		Group g = new Group();
		g.setId(deletegGroupid);
		group.setAccess_token(accessToken);
		group.setGroup(g);
		WeiXinReqService.getInstance().doWeinxinReqJson(group);
	}
}
