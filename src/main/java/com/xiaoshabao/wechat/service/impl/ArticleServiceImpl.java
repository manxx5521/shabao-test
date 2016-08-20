package com.xiaoshabao.wechat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.shiro.ContextHolderSystem;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.dao.AccountDao;
import com.xiaoshabao.wechat.dao.ArticleDao;
import com.xiaoshabao.wechat.dto.AccountValue;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.enums.ErrorWechatEnum;
import com.xiaoshabao.wechat.service.ArticleService;

@Service("articleServiceImpl")
public class ArticleServiceImpl extends AbstractServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private AccountDao accountDao;
	@Resource(name="tokenManager")
	private TokenManager tokenManager;
	//获得管理端文章列表
	@Override
	public List<ArticleEntity> getArticleList() {
		String priFrame=ContextHolderSystem.getPriFrame();
		return articleDao.getArticleList(priFrame);
	}
	
	//文章初始化方法
	@Override
	public ArticleDetailDto init() {
		String priFrame=ContextHolderSystem.getPriFrame();
		List<AccountValue> accounts=accountDao.getAccountValues(priFrame);
		ArticleDetailDto result=new ArticleDetailDto();
		result.setAccounts(accounts);
		return result;
	}
	
	@Override
	@Transactional
	public AjaxResult addArticle(ArticleEntity article) throws ServiceException  {
		String valid=this.validationArticle(article);
		if(valid!=null){
			return new AjaxResult(valid);
		}
		Integer userId=ContextHolderSystem.getUserId();
		article.setCreateUser(userId);
		article.setUpdateUser(userId);
		article.setStatus(1);
		int i=articleDao.insertArticle(article);
		if(i<1){
			logger.error(ErrorWechatEnum.ARTICLE_SAVE_ERROR.getMessage());
			return new AjaxResult(ErrorWechatEnum.ARTICLE_SAVE_ERROR);
		}
		//同步到微信
		String token=tokenManager.getAccessToken(article.getAccountId()).getAccessToken();
		return new AjaxResult(true,"添加成功");
	}
	
	/**
	 * 验证文章信息
	 * @param article
	 * @return
	 */
	public String validationArticle(ArticleEntity article){
		String message=null;
		if(article==null){
			message="未正常获得文章信息";
		}
		if(article.getAccountId()==null){
			message="帐号不能为空，请选择帐号";
		}
		if(StringUtils.isEmpty(article.getTitle())){
			message="标题不能为空，请填写标题";
		}
		if(StringUtils.isEmpty(article.getContent())){
			message="内容不能为空，请填写内容";
		}
		return message;
	}

	

	

}
