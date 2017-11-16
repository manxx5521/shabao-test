package com.xiaoshabao.webframework.ui.service.impl.element;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.component.TemplateEngine;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.FormValidateInfo;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;
import com.xiaoshabao.webframework.ui.service.element.UIElement;
import com.xiaoshabao.webframework.ui.service.impl.AbstractFormServiceImpl;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateNotFoundException;

/**
 * 元素抽象类<br>
 * 添加表单元素时，继承的类
 */
public abstract class AbstractUIElement extends AbstractFormServiceImpl
		implements UIElement {
	/*
	 * 获得元素参数
	 * <p>将文本JSON转换成Map,返回个JSONObject的父类</p>
	 */
	@Override
	public final Map<String, Object> getElementParams(String elementParams,
			String extParams) {
		JSONObject json = null;
		if (StringUtils.isNotEmpty(elementParams)) {
			json = JSONObject.parseObject(elementParams);
		}
		if (StringUtils.isNotEmpty(extParams)) {
			if (json == null) {
				json = JSONObject.parseObject(extParams);
			} else {
				JSONObject extJson = JSONObject.parseObject(extParams);
				if (!extJson.isEmpty()) {
					json.putAll(extJson);
				}
			}
		}
		
		if(json==null){
			json=JSONObject.parseObject("{}");;
		}
		return json;
	}

	//
	@Override
	public Object getCustomValue(Map<String, Object> data,
			Map<String, Object> elementParams, String fieldCode, Object value) {
		// 默认返回正常value不做任何处理
		return value;
	}

	// 验证前台数据是否正确
	@Override
	public FormValidateInfo validateData(Map<String, Object> data,
			ElementColumnDto elementDto, Map<String, Object> elementParams) {
		FormValidateInfo info = new FormValidateInfo(false);
		String fieldCode = elementDto.getTableColumn().getFieldCode();
		String label=elementDto.getLabel();
		Object value = data.get(fieldCode);//value类
		
		//验证是否必填
		if(elementDto.isRequired()){
			//验证单据填写的非空
			if(StringUtils.isEmpty(value.toString())){
				info.setMessage(getValidatMessage(label,"必须填写"));
				return info;
			}
				
		}/*else if(elementDto.getTableColumn().isNull()){
			//验证数据库
			if(StringUtils.isEmpty(value.toString())){
				info.setMessage(getValidatMessage(label,"必须填写（数据库层面必填）"));
				return info;
			}
		}*/
		
		if(value!=null&&StringUtils.isNoneEmpty(value.toString())){//有值
			//验证大小
			int size=getValueSize(value);
			if (elementDto.getMaxLength()!=null&&elementDto.getMaxLength()<size) {
				info.setMessage(getValidatMessage(label, "不能大于"+elementDto.getMaxLength()));
				return info;
			} else if(elementDto.getMinLength()!=null&&elementDto.getMinLength()>size) {
				info.setMessage(getValidatMessage(label, "不能小于"+elementDto.getMinLength()));
				return info;
			}/*else if(elementDto.getTableColumn().getFieldLength()!=null&&elementDto.getTableColumn().getFieldLength()<size) {
				info.setMessage(getValidatMessage(label, "不能大于"+elementDto.getTableColumn().getFieldLength()+"（数据库层面）"));
				return info;
			} */
		}
		info.setSuccess(true);
		return info;
	}
	
	//验证初始化参数
	@Override
	public FormValidateInfo validateParams(Map<String, Object> data,
			ElementColumnDto elementDto, Map<String, Object> elementParams) {
		
		return new FormValidateInfo(true);
	}
	
	/**
	 * 统一获得验证显示信息
	 */
	protected final String getValidatMessage(String label,String message){
		StringBuilder msg=new StringBuilder();
		msg.append(label);
		msg.append("的内容");
		msg.append(message);
		return msg.toString();
	}
	
	/**
	 * 获得值的大小
	 * <p>默认时string的大小，具体元素可以自己实现</p>
	 * @param value 值（不能为null）
	 * @return
	 */
	protected int getValueSize(Object value){
		return value.toString().length();
	}

	// 获得元素自定义参数
	@Override
	public final void getCustomParams(ElementColumnDto elementDto,
			Map<String, Object> data, Map<String, Object> elementParams) {
		// 添加共有参数处理（默认值的处理等）

		logger.debug("开始获得{}元素的自定义参数", elementDto.getElementId());
		getElementParams(elementDto, data, elementParams);
		logger.debug("结束获得{}元素的自定义参数", elementDto.getElementId());
	}

	/**
	 * 获得元素个性化参数
	 * <p>
	 * 个性化参数，具体子类重写 <br>
	 * 返回值放到elementParams中
	 * </p>
	 */
	protected void getElementParams(ElementColumnDto elementDto,
			Map<String, Object> data, Map<String, Object> elementParams) {
		// 无默认实现，有需要的重写此参数
	}

	/*
	 * render模版
	 */
	@Override
	public final String render(ElementEntity element,
			Map<String, Object> params, boolean isReadOnly) {
		String templateName = null;
		String templateContent = null;
		try {
			if (isReadOnly) {
				templateName = FormConstants.TEMPLATE_READ;
				templateContent = element.getReadTemplate();
			} else {
				templateName = FormConstants.TEMPLATE_VIEW;
				templateContent = element.getViewTemplate();
			}

			// 如果数据库存储不为空,使用数据库配置
			if (StringUtils.isNotEmpty(templateContent)) {
				logger.debug("在数据库加载模版{}", templateName);
				return TemplateEngine.renderTemplate(
						"elementid" + element.getElementId(), templateContent,
						params);
			}

			StringBuilder path = new StringBuilder();
			path.append("/webframework/form/");
			path.append(element.getElementType());
			path.append("_");
			path.append(templateName);
			path.append(".ftl");
			logger.debug("通过配置文件flt加载模版{}，模版文件{}", templateName,
					path.toString());
			return TemplateEngine.renderTemplate(path.toString(), params);
		} catch (ParseException e) {
			this.getReaderExcepiton("模版解析错误", params, templateName, e);
		} catch (TemplateNotFoundException e) {
			this.getReaderExcepiton("未发现输入的模版", params, templateName, e);
		} catch (MalformedTemplateNameException e) {
			this.getReaderExcepiton("模版类型获取错误", params, templateName, e);
		} catch (IOException e) {
			this.getReaderExcepiton("获取模版时读写异常", params, templateName, e);
		} catch (Exception e) {
			this.getReaderExcepiton("模版解析未知异常", params, templateName, e);
		}
		logger.debug("加载模版元素{},类型{}时出错，未能正常解析返回空", element.getElementId(),
				element.getElementType());
		return "";
	}

	/** 打印reader异常信息 **/
	private void getReaderExcepiton(String message, Map<String, Object> params,
			String templateTypeName, Object e) {
		logger.error("render异常,{};模版类型{},参数template {}, element {}", message,
				templateTypeName, params.get(FormConstants.ELEMENT_TEMPLATE_ID)
						.toString(),
				params.get(FormConstants.ELEMENT_TEMPLATE_ID).toString(), e);
	}

	// -------------------------------------------------------------
	/** 传入到模版的session标识 **/
	private final static String SESSION_TAG_STRING = "session";
	/** def元素标识 **/
	private final static String DEF_STRING = "def";

	// 初始化数据
	@Override
	public final JSONObject initData(Map<String, Object> params,
			ElementEntity element) {
		return initData(params, null, element);
	}

	// 初始化数据
	@Override
	public final JSONObject initData(Map<String, Object> params,
			TemplateElementEntity tempalteElement, ElementEntity element) {
		try {
			logger.debug("开始解析元素{}", element.getElementId());
			JSONObject paramJSON = null;
			// element表获取
			params.putAll(PropertyUtils.describe(element));
			String elementParam = element.getParams();
			if (StringUtils.isNotEmpty(elementParam)) {
				paramJSON = JSONObject.parseObject(elementParam);
			}
			// 关联表参数获取向上覆盖
			if (tempalteElement != null) {
				params.putAll(PropertyUtils.describe(tempalteElement));
				String formParam = tempalteElement.getFromParams();
				if (StringUtils.isNotEmpty(formParam)) {
					JSONObject formJSON = JSON.parseObject(formParam);
					if (paramJSON == null) {
						paramJSON = formJSON;
					} else {
						paramJSON.putAll(formJSON);
					}
				}
			}
			if (paramJSON != null) {
				params.put(DEF_STRING, paramJSON);
			}
			return paramJSON;
		} catch (Exception e) {
			throw new ServiceException(String.format(
					"render设置bean参数异常,获取bean的map时错误;参数template {}, element {}",
					params.get("templateId").toString(), params
							.get("elementId").toString()), e);
		}
	}

	// 默认调用view模版
	@Override
	public final String render(Map<String, Object> params, ElementEntity element) {
		return this.render(params, element,
				this.formEngineComponet.getDefaultTemplateType());
	}

	@Override
	public final String render(Map<String, Object> params,
			ElementEntity element, String templateTypeName) {
		try {
			Object template = params.get(templateTypeName);
			// 优先使用数据库配置
			if (StringUtils.isNotEmpty(template.toString())) {
				logger.debug("在数据库加载模版{}", templateTypeName);
				return TemplateEngine.renderTemplate(
						"elementid" + element.getElementId(),
						template.toString(), params);
			}
			StringBuffer path = new StringBuffer();
			path.append("/webframework/form/");
			path.append(element.getElementType());
			path.append("_");
			path.append(templateTypeName);
			path.append(".ftl");
			logger.debug("通过配置文件flt加载模版{}，模版文件{}", templateTypeName,
					path.toString());
			return TemplateEngine.renderTemplate(path.toString(), params);
		} catch (ParseException e) {
			this.getReaderExcepiton("模版解析错误", params, templateTypeName, e);
		} catch (TemplateNotFoundException e) {
			this.getReaderExcepiton("未发现输入的模版", params, templateTypeName, e);
		} catch (MalformedTemplateNameException e) {
			this.getReaderExcepiton("模版类型获取错误", params, templateTypeName, e);
		} catch (IOException e) {
			this.getReaderExcepiton("获取模版时读写异常", params, templateTypeName, e);
		} catch (Exception e) {
			this.getReaderExcepiton("模版解析未知异常", params, templateTypeName, e);
		}
		return "";
	}

	// 设置公共参数
	@Override
	public final void setPublicProperties(Map<String, Object> params,
			TemplateElementEntity tempalteElement, ElementEntity element) {

	}

	// 设置session参数
	@Override
	public final void setSessionParams(Map<String, Object> params,
			ElementEntity element) {
		// 设置session参数
		if (element.getSessionTag() == 1) {
			params.put(SESSION_TAG_STRING,
					this.formEngineComponet.getSessionObject());
		}
	}

	@Override
	public void setCustomParams(Map<String, Object> params, JSONObject paramJSON) {

	}

}
