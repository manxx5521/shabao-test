package com.xiaoshabao.wechat.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.shiro.SessionManager;
import com.xiaoshabao.wechat.dao.ArticleDao;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.service.ArticleService;

@Service("articleServiceImpl")
public class ArticleServiceImpl extends AbstractServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	//获得管理端文章列表
	@Override
	public List<ArticleEntity> getArticleList() {
		String priFrame=SessionManager.getInstance().getPriFrame();
		return articleDao.getArticleList(priFrame);
	}
	
	//文章初始化方法
	@Override
	public ArticleDetailDto init() {
		String priFrame=SessionManager.getInstance().getPriFrame();
		return null;
	}
	
	@Override
	public void addArticle(ArticleEntity article, Integer[] account_ids,
			Integer user_id) throws ServiceException  {
		if(account_ids==null||account_ids.length<1)
			throw new ServiceException("帐号为空");
		if(StringUtils.isEmpty(article.getTitle()))
			throw new ServiceException("标题不能为空");
		if(StringUtils.isEmpty(article.getContent()))
			throw new ServiceException("内容不能为空");
		
		for (Integer account_id:account_ids){
			article.setAccount_id(account_id);
			article.setCreate_staff(user_id);
			article.setUpdate_staff(user_id);
			this.baseDao.insert(ArticleEntity.class,article);
		}
	}

	

	

}
