package com.xiaoshabao.wechat.service;

import java.util.List;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.entity.ArticleEntity;

public interface ArticleService extends AbstractService{
	/**
	 * 获得管理端文章列表
	 * @return
	 */
	public List<ArticleEntity> getArticleList();
	/**
	 * 文章初始化方法
	 * @return
	 */
	public ArticleDetailDto init();
	/**
	 * 添加文章
	 * @param article
	 * @param account_ids
	 * @param user_id
	 * @throws ServiceException
	 */
	public AjaxResult addArticle(ArticleEntity article);
	
	
}
