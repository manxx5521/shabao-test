package com.xiaoshabao.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.webframework.dto.ImageDto;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.dto.ArticleInfoDto;
import com.xiaoshabao.wechat.entity.ArticleEntity;

public interface ArticleDao {
	/**
	 * 获得管理端文章列表
	 * @param priFrame
	 * @return
	 */
	public List<ArticleInfoDto> getArticleListDto(@Param("priFrame")String priFrame);
	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	public Integer insertArticle(ArticleEntity article);
	
	/**
	 * 现插入临时表
	 * @param article
	 * @return
	 */
	public Integer insertArticleTemp(ArticleEntity article);
	/**
	 * 更新媒体信息---URL和media信息
	 * @return
	 */
	public int updateMedia(ArticleEntity article);
	/**
	 * 获得文章详情-更具id
	 * @param articleId
	 * @return
	 */
	public ArticleDetailDto getArticleById(Integer articleId);
	
	/**
	 * 获得封面图片
	 * @param mediaId
	 * @return
	 */
	public ImageDto getImage(String mediaId);

}
