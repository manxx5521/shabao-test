package com.xiaoshabao.webframework.ui.service.impl.element;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.component.TemplateEngine;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplatElementEntity;
import com.xiaoshabao.webframework.ui.service.element.UIElement;
import com.xiaoshabao.webframework.ui.service.impl.AbstractTemplateServiceImpl;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;
/**
 * 元素抽象类<br>
 * 添加表单元素时，继承的类
 */
public abstract class AbstractUIElement extends AbstractTemplateServiceImpl implements UIElement {
	/** 传入到模版的session标识 **/
	private final static String SESSION_TAG_STRING = "session";
	/** def元素标识 **/
	private final static String DEF_STRING="def";
	
	// 初始化数据
	@Override
	public final JSONObject initData(Map<String,Object> params,ElementEntity element) {
		return initData(params,null,element);
	}
	//初始化数据
	@Override
	public final JSONObject initData(Map<String,Object> params,TemplatElementEntity tempalteElement,ElementEntity element){
		try {
			logger.debug("开始解析元素{}",element.getElementId());
			JSONObject paramJSON=null;
			//element表获取
			params.putAll(PropertyUtils.describe(element));
			String elementParam=element.getParams();
			if(StringUtils.isNotEmpty(elementParam)){
				paramJSON=JSONObject.parseObject(elementParam);
			}
			//关联表参数获取向上覆盖
			if(tempalteElement!=null){
				params.putAll(PropertyUtils.describe(tempalteElement));
				String formParam=tempalteElement.getFromParams();
				if(StringUtils.isNotEmpty(formParam)){
					JSONObject formJSON = JSON.parseObject(formParam);
					if(paramJSON==null){
						paramJSON=formJSON;
					}else{
						paramJSON.putAll(formJSON);
					}
				}
			}
			if(paramJSON!=null){
				params.put(DEF_STRING,paramJSON);
			}
			return paramJSON;
		} catch (Exception e) {
			throw new ServiceException(String.format("render设置bean参数异常,获取bean的map时错误;参数template {}, element {}",
					params.get("templateId").toString(),params.get("elementId").toString()),e);
		} 
	}
	
	
	
	//默认调用view模版
	@Override
	public final String render(Map<String,Object> params,ElementEntity element) {
		return this.render(params,element, this.formEngineComponet.getDefaultTemplateType());
	}
	
	@Override
	public final String render(Map<String,Object> params,ElementEntity element,String templateTypeName) {
		try {
			Object template=params.get(templateTypeName);
			//优先使用数据库配置
			if(StringUtils.isNotEmpty(template.toString())){
				logger.debug("在数据库加载模版{}",templateTypeName);
				return TemplateEngine.renderTemplate("elementid"+element.getElementId(), template.toString(), params);
			}
			StringBuffer path=new StringBuffer();
			path.append("/webframework/form/");
			path.append(element.getElementType());
			path.append("_");
			path.append(templateTypeName);
			path.append(".ftl");
			logger.debug("通过配置文件flt加载模版{}，模版文件{}",templateTypeName,path.toString());
			return TemplateEngine.renderTemplate(path.toString(), params);
		}catch (ParseException e) {
			this.getReaderExcepiton("模版解析错误",params,templateTypeName, e);
		}catch (TemplateNotFoundException e) {
			this.getReaderExcepiton("未发现输入的模版",params, templateTypeName, e);
		}catch (MalformedTemplateNameException e) {
			this.getReaderExcepiton("模版类型获取错误",params, templateTypeName, e);
		}catch (IOException e) {
			this.getReaderExcepiton("获取模版时读写异常",params, templateTypeName, e);
		} catch (Exception e) {
			this.getReaderExcepiton("模版解析未知异常", params,templateTypeName, e);
		}
		return "";
	}
	
	/** 打印reader异常信息 **/
	private void getReaderExcepiton(String message,Map<String,Object> params,String templateTypeName,Object e){
		logger.error("render异常,{};模版类型{},参数template {}, element {}",message,templateTypeName,
				params.get("templateId").toString(),params.get("elementId").toString(),e);
	}
	//设置公共参数
	@Override
	public final void setPublicProperties(Map<String,Object> params,TemplatElementEntity tempalteElement,ElementEntity element){
		
	}
	
	//设置session参数
	@Override
	public final void setSessionParams(Map<String, Object> params,ElementEntity element) {
		// 设置session参数
		if (element.getSessionTag() == 1) {
			params.put(SESSION_TAG_STRING,this.formEngineComponet.getSessionObject());
		}
	}
	
	@Override
	public void setCustomParams(Map<String,Object> params,JSONObject paramJSON){
		
	}
	
}
