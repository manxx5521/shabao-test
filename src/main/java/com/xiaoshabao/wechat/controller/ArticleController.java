package com.xiaoshabao.wechat.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.dto.ArticleInfoDto;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;
import com.xiaoshabao.wechat.enums.ErrorWechatEnum;
import com.xiaoshabao.wechat.service.ArticleService;
/**
 * 文章
 */
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
		List<ArticleInfoDto> list=this.articleService.getArticleList();
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
	public ModelAndView init (ModelMap map){
		ArticleDetailDto result=articleService.init();
		map.put("data", result);
		return new ModelAndView ("/wechat/article/detail");
	}
	
	/**
	 * 添加文章方法
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@ResponseBody
	@RequestMapping(value="/admin/wechat/article/add")
	public AjaxResult addArticle (MediaNewsEntity media) throws DaoException{
		try {
			AjaxResult result=articleService.addArticle(media);
			return result;
		} catch (ServiceException e) {
			return new AjaxResult(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("未知异常--"+ErrorWechatEnum.ARTICLE_SAVE_ERROR.getMessage());
			return new AjaxResult(ErrorWechatEnum.ARTICLE_SAVE_ERROR);
		}
	}
	/**
	 * 文章初始化方法
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value="/admin/wechat/article/{articleId}/detail")
	public ModelAndView getDetail (ModelMap map,@PathVariable("articleId")Integer articleId){
		ArticleDetailDto result=articleService.getDetail(articleId);
		map.put("data", result);
		return new ModelAndView ("/wechat/article/detail");
	}
	
	/**
	 * 微信端文章显示
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/wechat/article/{articleId}/detail")
	public ModelAndView getWechatArticle (ModelMap model,@PathVariable("articleId")Integer articleId){
		ArticleDetailDto result=articleService.getArticleById(articleId);
		model.put("data", result);
		return new ModelAndView ("/wechat/article/wxdetail");
	}
	
}
