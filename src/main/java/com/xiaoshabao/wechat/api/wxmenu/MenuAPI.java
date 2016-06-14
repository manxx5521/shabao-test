package com.xiaoshabao.wechat.api.wxmenu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.wechat.api.core.exception.WexinReqException;
import com.xiaoshabao.wechat.api.core.util.WeiXinReqService;
import com.xiaoshabao.wechat.api.wxmenu.model.Menu;
import com.xiaoshabao.wechat.api.wxmenu.model.MenuCreate;
import com.xiaoshabao.wechat.api.wxmenu.model.MenuDel;
import com.xiaoshabao.wechat.api.wxmenu.model.MenuGet;
import com.xiaoshabao.wechat.api.wxmenu.model.WeixinButton;
import com.xiaoshabao.wechat.api.wxmenu.model.config.CustomWeixinButtonConfig;
import com.xiaoshabao.wechat.api.wxmenu.model.config.MenuArticleConfig;
import com.xiaoshabao.wechat.api.wxmenu.model.config.MenuConfigureGet;
import com.xiaoshabao.wechat.api.wxmenu.model.config.WeixinButtonExtend;
/**
 * 微信菜单API
 */
public class MenuAPI {
	
	/**
	 * 创建自定义菜单<br>
	 * 创建菜单通过子继承的方式
	 * @param accessToken
	 * @param menu
	 * @throws WexinReqException
	 */
	public static void createMenu(String accessToken, Menu menu)
			throws WexinReqException {
		menu.setAccess_token(accessToken);
		WeiXinReqService.getInstance().doWeinxinReqJson(menu);
	}
	
	/**
	 * 创建菜单<br>通过列表方式
	 *  button	是	一级菜单数组，个数应为1~3个
		sub_button	否	二级菜单数组，个数应为1~5个
		type	是	菜单的响应动作类型
		name	是	菜单标题，不超过16个字节，子菜单不超过40个字节
		key	click等点击类型必须	菜单KEY值，用于消息接口推送，不超过128字节
		url	view类型必须	网页链接，用户点击菜单可打开链接，不超过256字节
	 * @param accessToken
	 * @param button  的json字符串
	 * @throws WexinReqException
	 */
	public static void createMenu(String accessToken,List<WeixinButton> button) throws WexinReqException{
		MenuCreate m = new MenuCreate();
		m.setAccess_token(accessToken);
		m.setButton(button);
		WeiXinReqService.getInstance().doWeinxinReqJson(m);
	}
	
	/**
	 * 获取自定义菜单
	 * @param accessToken
	 * @throws WexinReqException
	 */
	public static List<WeixinButton> getMenu(String accessToken)
			throws WexinReqException {
		MenuGet upload=new MenuGet();
		upload.setAccess_token(accessToken);
		JSONObject result =WeiXinReqService.getInstance().doWeinxinReqJson(upload);
		List<WeixinButton> lstButton = null;
		JSONObject menu = result.getJSONObject("menu");
		JSONArray buttons = menu.getJSONArray("button");
		JSONArray subButtons = null;
		lstButton = new ArrayList<WeixinButton>();
		WeixinButton btn = null;
		WeixinButton subBtn = null;
		List<WeixinButton> lstSubButton = null;
		for (int i = 0; i < buttons.size(); i++) {
			btn = JSON.toJavaObject(buttons.getJSONObject(i),
					WeixinButton.class);
			subButtons = buttons.getJSONObject(i).getJSONArray("sub_button");
			if (subButtons != null) {
				lstSubButton = new ArrayList<WeixinButton>();
				for (int j = 0; j < subButtons.size(); j++) {
					subBtn = JSON.toJavaObject(
							subButtons.getJSONObject(j), WeixinButton.class);
					lstSubButton.add(subBtn);
				}
				btn.setSub_button(lstSubButton);
			}
			lstButton.add(btn);
		}
		return lstButton;
	}
	
	/**
	 * 删除所有的菜单
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	public static void deleteMenu(String accessToken) throws WexinReqException{
		MenuDel upload = new MenuDel();
		upload.setAccess_token(accessToken);
		WeiXinReqService.getInstance().doWeinxinReqJson(upload);
	}
	
	/**
	 * 获取自定义接口配置<br>
	 * 包括菜单是否使用，和key信息等
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	public static CustomWeixinButtonConfig getMenuConfigure(String accessToken) throws WexinReqException{
		MenuConfigureGet cmcg = new MenuConfigureGet();
		cmcg.setAccess_token(accessToken);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(cmcg);
		CustomWeixinButtonConfig customWeixinButtonConfig = JSON.toJavaObject(result, CustomWeixinButtonConfig.class);
		
		JSONObject selfmenuInfo = result.getJSONObject("selfmenu_info");
		
		if(selfmenuInfo!=null&&!selfmenuInfo.isEmpty()){ 
			/**处理父类菜单 */
			JSONArray buttons = selfmenuInfo.getJSONArray("button");
			List<WeixinButtonExtend> listButton = new ArrayList<WeixinButtonExtend>();
			for(int i=0;i<buttons.size();i++){
				WeixinButtonExtend weixinButtonExtend = JSON.toJavaObject(buttons.getJSONObject(i),WeixinButtonExtend.class);
				/**处理子类菜单 */
				JSONObject subButtonJsonObj = buttons.getJSONObject(i).getJSONObject("sub_button");
				if(subButtonJsonObj!=null&&!subButtonJsonObj.isEmpty()){
					JSONArray subButtons = subButtonJsonObj.getJSONArray("list");
					if (subButtons != null) {
						List<WeixinButtonExtend> listSubButton = new ArrayList<WeixinButtonExtend>();
						for (int j = 0; j < subButtons.size(); j++) {
							WeixinButtonExtend subBtn = JSON.toJavaObject(subButtons.getJSONObject(j), WeixinButtonExtend.class);
							/**处理菜单关联的图文消息 */
							JSONObject newsInfoJsonObj = subButtons.getJSONObject(j).getJSONObject("news_info");
							if(newsInfoJsonObj!=null&&!newsInfoJsonObj.isEmpty()){
								JSONArray newsInfos = newsInfoJsonObj.getJSONArray("list");
								List<MenuArticleConfig> listNewsInfo = new ArrayList<MenuArticleConfig>();
								for (int k = 0; k < newsInfos.size(); k++) {
									MenuArticleConfig wxArticleConfig = JSON.toJavaObject(newsInfos.getJSONObject(k), MenuArticleConfig.class);
									listNewsInfo.add(wxArticleConfig);
								}
								subBtn.setNews_info(listNewsInfo);
							}
							listSubButton.add(subBtn);
						}
						weixinButtonExtend.setSub_button(listSubButton);
					}
				}
				listButton.add(weixinButtonExtend);
			}
			customWeixinButtonConfig.setSelfmenu_info(listButton);
		}
		return customWeixinButtonConfig;
	}

}
