package com.xiaoshabao.wechat.component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.wechat.api.core.http.HttpClientManager;
import com.xiaoshabao.wechat.bean.WechatSession;
import com.xiaoshabao.wechat.dao.SubscriberDao;
import com.xiaoshabao.wechat.entity.SubscriberEntity;
/**
 * 微信工具类
 */
@Component("wechatComponent")
public class WechatComponent{
	private static Logger logger = LoggerFactory.getLogger(WechatComponent.class);
	
	@Resource(name="wechatConfig")
	private WechatConfig wechatConfig;
	@Autowired
	private SubscriberDao subscriberDao;
	/**
	 * 保存微信 个人信息
	 * @param session
	 */
	public void savaSessionInfo(){
		logger.debug("保存个人信息--begin");
		HttpServletRequest request=ContextHolderWechat.getRequest();
		HttpSession session = request.getSession();
		Object obj=session.getAttribute(ContextHolderWechat.WECHAT_SESSION);
		if(obj==null){
			throw new ServiceException("保存微信个人信息时，未能找到session中存放的信息");
		}
		WechatSession wechatSession=(WechatSession)obj;
		SubscriberEntity wechatInfo=wechatSession.getInfo();
		if(wechatInfo==null){
			throw new ServiceException("保存微信个人信息时，未能找到session中存放的微信个人信息");
		}
		//保存头像信息
		if(StringUtils.isNotEmpty(wechatInfo.getHeadimgurl())){
			String separator=File.separator;
			/** 真实保存目录 */ 
			String savePath = request.getSession().getServletContext().getRealPath("/");
			if(!savePath.endsWith(separator)){
				savePath+=separator;
			}
			StringBuffer path=new StringBuffer(savePath);
			path.append("resources");
			path.append(separator);
			path.append("wechat");
			path.append(separator);
			path.append("upload");
			path.append(separator);
			path.append("portrait");
			path.append(separator);
			// 检查目录
			File uploadDir = new File(path.toString());
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			if (!uploadDir.isDirectory()) {
				throw new ServiceException("保存微信个人信息时错误 ，上传目录不存在。");
			}
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				throw new ServiceException("保存微信个人信息时错误 ，上传目录没有写权限。。");
			}
			//创建日期目录
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path.append(ymd);
			path.append(separator);
			File dirFile = new File(path.toString());
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			//自定义头像名字
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = df.format(new Date()) + "_"+ new Random().nextInt(1000) + ".jpg";
			//微信端获得头像
			HttpClientManager http=HttpClientManager.getInstance();
			Map<String ,Object> params=new HashMap<String ,Object>();
			http.getFileGetSSL(wechatInfo.getHeadimgurl(),params,path.toString() ,fileName);
			path.append(fileName);
			wechatInfo.setPortrait("/resources/wechat/upload/portrait/"+ymd+"/"+fileName);
		}
		int i=subscriberDao.update(wechatInfo);
		if(i<1){
			throw new ServiceException("保存微信个人信息时错误 ，表数据更新失败");
		}
	}
}
