package com.xiaoshabao.webframework.ui.service.impl.element;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
	/** 关联表元素 **/
	private TemplatElementEntity tempalteElement;
	/** 组件元素数据 **/
	private ElementEntity element;
	/** 元素参数json,可以用来转换为子类参数类型 **/
	private JSONObject paramJSON;
	/** 默认模版 **/
	private String defaultTempalte="viewTemplate";
	
	//初始化数据
	@Override
	public void initData(TemplatElementEntity tempalteElement,ElementEntity element){
		this.element=element;
		this.tempalteElement=tempalteElement;
	}
	/**
	 * 设置JOSN参数
	 */
	@Override
	public void setJSONParams(Map<String,Object> params){
		String elementParam=element.getParams();
		if(StringUtils.isNotEmpty(elementParam)){
			JSONObject elementJSON=JSONObject.parseObject(elementParam);
			params.putAll(elementJSON);
		}
		String formParam=tempalteElement.getFromParams();
		if(StringUtils.isNotEmpty(formParam)){
			JSONObject formJSON = JSON.parseObject(formParam);
			if(paramJSON==null){
				paramJSON=formJSON;
			}else{
				paramJSON.putAll(formJSON);
			}
			params.putAll(formJSON);
		}
	}
	
	//默认调用view模版
	@Override
	public String render(Map<String,Object> params) {
		return this.render(params, defaultTempalte);
	}
	
	@Override
	public String render(Map<String,Object> params,String templateTypeName) {
		try {
			String template=BeanUtils.getProperty(this.element, templateTypeName);
			//优先使用数据库配置
			if(template!=null){
				return TemplateEngine.renderTemplate(this.element.getElementId(), template, params);
			}
			//通过配置文件加载
			StringBuffer path=new StringBuffer();
			path.append("form/");
			path.append(this.element.getElementType());
			path.append("_");
			path.append(templateTypeName);
			path.append(".ftl");
			return TemplateEngine.renderTemplate(path.toString(), params);
		}catch (ParseException e) {
			this.getReaderExcepiton("模版解析错误", templateTypeName, e);
		}catch (TemplateNotFoundException e) {
			this.getReaderExcepiton("未发现输入的模版", templateTypeName, e);
		}catch (MalformedTemplateNameException e) {
			this.getReaderExcepiton("模版类型获取错误", templateTypeName, e);
		}catch (IOException e) {
			this.getReaderExcepiton("获取模版是读写异常", templateTypeName, e);
		}catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			this.getReaderExcepiton("未能正常获得模版类型，可能是是配置错误或者获取element属性错误", templateTypeName, e);
		} catch (Exception e) {
			this.getReaderExcepiton("模版解析未知异常", templateTypeName, e);
		}
		return "";
	}
	
	/** 打印reader异常信息 **/
	private void getReaderExcepiton(String message,String templateTypeName,Object e){
		logger.error("render异常,{};模版类型{},参数template {}, element {}",message,templateTypeName,
				this.tempalteElement==null?"null":this.tempalteElement.getTemplateId(),this.element.getElementId(),e);
	}
	@Override
	public void setPublicProperties(Map<String,Object> params){
		
	}
	@Override
	public void setCustomParams(Map<String,Object> params){
		
	}
	
	//清空便于内存回收
	@Override
	public void clear() {
		this.element=null;
		this.tempalteElement=null;
		this.paramJSON=null;
	}
}
