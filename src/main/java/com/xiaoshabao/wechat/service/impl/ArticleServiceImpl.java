package com.xiaoshabao.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.xiaoshabao.system.entity.SessionUserInfo;
import com.xiaoshabao.webframework.component.ContextHolderUtils;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.dto.ImageDto;
import com.xiaoshabao.wechat.api.wxmedia.MediaAPI;
import com.xiaoshabao.wechat.api.wxmedia.result.ArticleOneResult;
import com.xiaoshabao.wechat.component.TokenManager;
import com.xiaoshabao.wechat.component.WechatConfig;
import com.xiaoshabao.wechat.dao.AccountDao;
import com.xiaoshabao.wechat.dao.ArticleDao;
import com.xiaoshabao.wechat.dao.MediaNewsDao;
import com.xiaoshabao.wechat.dto.AccountValue;
import com.xiaoshabao.wechat.dto.ArticleDetailDto;
import com.xiaoshabao.wechat.dto.ArticleInfoDto;
import com.xiaoshabao.wechat.entity.AccountEntity;
import com.xiaoshabao.wechat.entity.ArticleEntity;
import com.xiaoshabao.wechat.entity.MediaNewsEntity;
import com.xiaoshabao.wechat.enums.ErrorWechatEnum;
import com.xiaoshabao.wechat.service.ArticleService;
import com.xiaoshabao.wechat.util.MediaUtil;

@Service("articleServiceImpl")
public class ArticleServiceImpl extends AbstractServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private AccountDao accountDao;
	@Resource(name="tokenManager")
	private TokenManager tokenManager;
	@Resource(name="wechatConfig")
	private WechatConfig wechatConfig;
	@Autowired
	private MediaNewsDao newsDao;
	
	private String realPath;
	private String contextpath;
	private String token;
	
	//获得管理端文章列表
	@Override
	public List<ArticleInfoDto> getArticleList() {
		String priFrame=ContextHolderSystem.getPriFrame();
		return articleDao.getArticleListDto(priFrame);
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
	
	//添加文章了
	@Override
	@Transactional
	public AjaxResult addArticle(MediaNewsEntity media) throws ServiceException  {
		String valid=this.validationMedia(media);
		if(valid!=null){
			return new AjaxResult(valid);
		}
		SessionUserInfo sessionInfo=ContextHolderSystem.getSeesionInfo();
		Integer userId=sessionInfo.getUserId();
		String name=sessionInfo.getUserName();
		media.setAuthor(name);
		//先暂时插入文章信息
		ArticleEntity article=new ArticleEntity();
		article.setAccountId(media.getAccountId());
		article.setMediaId("0");//先暂时存入0
		article.setUrl("0");//先暂时存入0
		article.setType(1);
		article.setCreateUser(userId);
		article.setUpdateUser(userId);
		article.setStatus(1);
		int i=articleDao.insertArticleTemp(article);
		if(i<1){
			logger.error(ErrorWechatEnum.ARTICLE_SAVE_ERROR.getMessage());
			throw new ServiceException(ErrorWechatEnum.ARTICLE_SAVE_ERROR);
		}
		//将文章内容中的图片同步到微信
		HttpSession session=ContextHolderUtils.getRequest().getSession();
		String realPath = session.getServletContext().getRealPath("/");
		this.realPath=realPath;
		String contextpath=session.getServletContext().getContextPath();
		this.contextpath=contextpath;
		String token=tokenManager.getAccessToken(article.getAccountId()).getAccessToken();
		this.token=token;
		String content=this.parserContentImg(media.getContent());
		media.setContent(content);
		//将图文消息同步到微信
		StringBuffer sourceUrl=new StringBuffer(wechatConfig.getDomain());//阅读原文的URL
		sourceUrl.append("/wechat/article/");
		sourceUrl.append(article.getArticleId());
		sourceUrl.append("/detail");
		media.setContentSourceUrl(sourceUrl.toString());
		/** 微信端URL */
		ArticleOneResult art=MediaUtil.uploadNews(media,token);
		article.setUrl(art.getUrl());
		article.setMediaId(art.getMedia_id());
//		i=articleDao.updateMedia(article);
		i=articleDao.insertArticle(article);
		if(i<1){
			logger.error(ErrorWechatEnum.ARTICLE_SAVE_ERROR.getMessage());
			throw new ServiceException(ErrorWechatEnum.ARTICLE_SAVE_ERROR);
		}
		media.setMediaId(art.getMedia_id());
		media.setType(1);;
		media.setShowCoverPic(art.getShow_cover_pic());
		i=newsDao.insertMediaNews(media);
		if(i<1){
			logger.error(ErrorWechatEnum.ARTICLE_SAVE_ERROR.getMessage());
			throw new ServiceException(ErrorWechatEnum.ARTICLE_SAVE_ERROR);
		}
		return new AjaxResult(true,"添加成功");
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
		if(StringUtils.isEmpty(media.getShowCoverPic())){
			media.setShowCoverPic("0");
		}
		return message;
	}
	//获得文章客户端详情
	@Override
	public ArticleDetailDto getDetail(Integer articleId) {
		ArticleDetailDto result=articleDao.getArticleById(articleId);
		List<AccountValue> accounts=new ArrayList<AccountValue>();
		AccountEntity accountEntity=accountDao.getAccountById(result.getAccountId());
		accounts.add(new AccountValue(accountEntity.getAccountId(),accountEntity.getAppName()));
		result.setAccounts(accounts);
		ImageDto img=articleDao.getImage(result.getNews().getThumbMediaId());
		result.setImage(img);
		return result;
	}
	
	/**
	 * 解析内容将图片单独传输
	 * @param content 要解析的内容
	 * @return 返回解析后的HTML
	 */
	private String parserContentImg(String content){
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
							if(imgpath.startsWith(contextpath)){
								String path=realPath+imgpath.replaceFirst(contextpath,"");
								String url=MediaAPI.uploadNewsImg(token, path);
								((ImageTag) tag).setAttribute("src", url);
							}
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
	
	//获得文章微信详情
	@Override
	public ArticleDetailDto getArticleById(Integer articleId) {
		return articleDao.getArticleById(articleId);
	}

}
