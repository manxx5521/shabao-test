package com.xiaoshabao.wechat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.NodeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.impl.AbstractServiceImpl;
import com.xiaoshabao.system.component.ContextHolderSystem;
import com.xiaoshabao.webframe.dto.AjaxResult;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.dao.AccountDao;
import com.xiaoshabao.wechat.dao.ArticleDao;
import com.xiaoshabao.wechat.dto.AccountValue;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;
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
	public AjaxResult addArticle(MediaNewsEntity media) throws ServiceException  {
		String valid=this.validationMedia(media);
		if(valid!=null){
			return new AjaxResult(valid);
		}
		/*
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
		String token=tokenManager.getAccessToken(article.getAccountId()).getAccessToken();*/
		return new AjaxResult(true,"添加成功");
	}
	
	/**
	 * 解析内容将图片单独传输
	 * @param content
	 * @return
	 */
	private String parserContent(String content){
		try {
			Parser parser1 = Parser.createParser(content, "UTF-8");
			NodeFilter[] filters = new NodeFilter[1];
			filters[0] = new NodeClassFilter(BodyTag.class);
			NodeFilter filter = new OrFilter(filters);
			NodeList bodys = parser1.extractAllNodesThatMatch(filter);
			if (bodys == null || bodys.size() == 0) {
				content = "<body>" + content + "</body>";
			}
			Parser parser = Parser.createParser(content, "UTF-8");
			NodeList list = parser.parse(new TagNameFilter("body"));
			Node body = list.elementAt(0);
			body.accept(new NodeVisitor() {
				@Override
				public void visitTag(Tag tag) {
					//解析标签
					if (tag.getTagName().equalsIgnoreCase("img")) {
						String imgpath = ((ImageTag) tag).getAttribute("src");
						if (StringUtils.isNotEmpty(imgpath)&&!imgpath.startsWith("http://mmbiz.qpic.cn")) {
							logger.info("解析图片上传到微信服务器：--"+imgpath);
							//TODO 需要解析内容中图片，上传到微信服务
						}
					}
				}
			});
			NodeList childes = body.getChildren();
			SimpleNodeIterator ss=childes.elements();
			String html="";
			while(ss.hasMoreNodes()){
				Node node=ss.nextNode();
				html=html+node.toHtml();
			}
			return html;
		} catch (Exception e) {
			throw new ServiceException("html解析失败");
		}
		
	}
	
	/**
	 * 验证文章信息
	 * @param article
	 * @return
	 */
	public String validationMedia(MediaNewsEntity media){
		String message=null;
		if(media.getAccountId()==null){
			message="帐号不能为空，请选择帐号";
		}
		if(StringUtils.isEmpty(media.getThumbMediaId())){
			message="封面不能为空，请选择封面";
		}
		if(StringUtils.isEmpty(media.getTitle())){
			message="标题不能为空，请填写标题";
		}
		if(StringUtils.isEmpty(media.getDigest())){
			message="摘要不能为空，请填写摘要";
		}
		if(StringUtils.isEmpty(media.getContent())){
			message="内容不能为空，请填写内容";
		}
		return message;
	}

	

	

}
