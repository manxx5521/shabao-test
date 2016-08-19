package com.xiaoshabao.wechat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.system.shiro.SessionManager;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.entity.AccountEntity;
import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.service.ArticleService;

@Controller
public class ArticleController extends AbstractController{
	
	@Resource(name="articleServiceImpl")
	private ArticleService articleService;
	
	/**
	 * 文章管理端列表界面列表
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value="/admin/wechat/article/list")
	public ModelAndView getArticleList (ModelMap model){
		List<ArticleEntity> list=this.articleService.getArticleList();
		model.put("dataList", list);
		return new ModelAndView ("/wechat/article/articleList");
	}
	/**
	 * 文章初始化方法
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value="/admin/wechat/article/init")
	public ModelAndView init (ModelMap map,String article_id){
		ArticleDetailDto result=articleService.init();
		map.put("data", result);
		return new ModelAndView ("/wechat/article/detail");
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param map
	 * @param article_id
	 * @return
	 */
	@RequestMapping(value="/admin/article")
	public ModelAndView initArticleCode (ModelMap map,String article_id){
		
		return new ModelAndView ("/admin/article/articleCode");
	}
	
	/**
	 * 添加文章方法
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@ResponseBody
	@RequestMapping(value="/admin/articleAdd")
	public  Map<String, Object> addArticle (ArticleEntity article,Integer[] account_ids) throws DaoException{
		Integer user_id=SessionManager.getInstance().getUserId();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			this.articleService.addArticle(article,account_ids,user_id);
			returnMap.put("success", true);
		} catch (ServiceException e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("message", e.getMessage());
		}
		return returnMap;
	}
	
	/**
	 * 微信端文章显示
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 * @throws ServiceException 
	 */
	@RequestMapping(value="/wechat/article")
	public ModelAndView getWechatArticle (ModelMap model,Integer article_id ) throws DaoException, ServiceException{
		if(article_id==null||article_id==0)
			throw new ServiceException("文章id未识别");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("article_id", article_id);
		ArticleEntity article=this.articleService.getDataSingle(ArticleEntity.class, params);
		AccountEntity pentity=new AccountEntity();
		pentity.setAccountId(article.getAccount_id());
		AccountEntity account=this.articleService.getDataSingle(AccountEntity.class, pentity);
		model.put("appName", account.getAppName());
		model.put("article", article);
		return new ModelAndView ("/wechat/article/article");
	}
	
	/**
	 * 添加文章初始化方法UE方式
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value="/admin/articleAddUe")
	public ModelAndView addArticleUeInit (ModelMap map,String article_id) throws DaoException{
		Integer user_id=SessionManager.getInstance().getUserId();
		/*UserAccountValue param=new UserAccountValue();
		param.setUser_id(user_id);
		List<UserAccountValue> list=this.articleService.getData(UserAccountValue.class, param);*/
		map.put("dataList", null);
		return new ModelAndView ("/admin/article/articleAddByUe");
	}
}
