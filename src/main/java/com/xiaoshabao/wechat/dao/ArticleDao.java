package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.wechat.entity.ArticleEntity;

public interface ArticleDao {
	/**
	 * 获得管理端文章列表
	 * @param priFrame
	 * @return
	 */
	public List<ArticleEntity> getArticleList(@Param("priFrame")String priFrame);
	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	public int insertArticle(ArticleEntity article);

}
