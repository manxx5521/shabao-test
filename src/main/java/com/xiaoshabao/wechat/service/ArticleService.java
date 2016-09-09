package com.xiaoshabao.wechat.service;

import java.util.List;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.AbstractService;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.dto.ArticleInfoDto;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;

public interface ArticleService extends AbstractService{
	/**
	 * 获得管理端文章列表
	 * @return
	 */
	public List<ArticleInfoDto> getArticleList();
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
	public AjaxResult addArticle(MediaNewsEntity media);
	/**
	 * 获得文章客户端详情
	 * @return
	 */
	public ArticleDetailDto getDetail(Integer articleId);
	
	/**
	 * 获得文章详情
	 * @return
	 */
	public ArticleDetailDto getArticleById(Integer articleId);
	
	
}
