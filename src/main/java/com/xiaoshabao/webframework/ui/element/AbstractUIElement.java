package com.xiaoshabao.webframework.ui.element;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.component.TemplateEngine;
import com.xiaoshabao.webframework.ui.entity.FormElement;
import com.xiaoshabao.webframework.ui.entity.FormElementDef;
import com.xiaoshabao.webframework.ui.exception.InvalidConfigException;
import com.xiaoshabao.webframework.ui.exception.InvalidValueException;

public abstract class AbstractUIElement<E extends FormElementDef> implements UIElement {
	protected FormElement formElement;
	
	/** element带的参数 */
	private E elementDef;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 给表单元元素设置值
	 * @param formElement
	 * @param clazz
	 */
	public <T> void setFormElement(FormElement formElement, Class<T> clazz) {
		try { 
			FormElementDef formElementDef = formElement.getFormElementDef();
			String params = formElementDef.getParam();
			if(StringUtils.isNotEmpty(params)){
				JSONObject jsnObject = JSONObject.parseObject(params);
				FormElementDef def=(FormElementDef) JSONObject.toJavaObject(jsnObject, this.getdefClass());
				BeanUtils.copyProperties(formElementDef, def);
				formElement.setFormElementDef(def);
			}
			this.formElement=formElement;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public E getElementDef() {
		return elementDef;
	}
	public void setElementDef(E elementDef) {
		this.elementDef = elementDef;
	}
	/****************************/
	
	public AbstractUIElement() {
		
	}
	public AbstractUIElement(FormElement formElement) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElement#getElement(java.lang.String)
	 */
	@Override
	public FormElement getFormElement() {
		return this.formElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asiainfo.webframework.ui.form.UIElement#render(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object render(Map params) throws InvalidConfigException {
		if (params == null) {
			params = new HashMap();
		}
		// 设置元素定义
		setPublicProperties(params);
		// 子类设置个性化参数
		Map cutomparams = getCustomRenderParam(params);
		if (cutomparams != null) {
			params.putAll(cutomparams);
		}
		try {
			return TemplateEngine.renderTemplate(this.getFormElement()
					.getElementid().toString(), this.getFormElement()
					.getFormElementDef().getTempplate(), params);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error("render template {} element {} error {}", this
					.getFormElement().getTemplateid(), this.getFormElement()
					.getElementcode(), e);
			throw new InvalidConfigException("渲染异常");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElement#renderview(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Object renderview(Map params) throws InvalidConfigException {
		if (params == null) {
			params = new HashMap();
		}
		// 设置元素定义
		setPublicProperties(params);
		// 子类设置个性化参数
		Map cutomparams = getCustomRenderParam(params);
		if (cutomparams != null) {
			params.putAll(cutomparams);
		}
		try {
			return TemplateEngine.renderTemplate(this.getFormElement()
					.getElementid().toString(), this.getFormElement()
					.getFormElementDef().getViewtempplate(), params);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error("render template {} element {} error {}", this
					.getFormElement().getTemplateid(), this.getFormElement()
					.getElementcode(), e);
			throw new InvalidConfigException("渲染异常");
		}
	}

	private void setPublicProperties(Map params) {
		// 设置元素定义
		// 为了便于操作把相关内容弄转换为json字符串
		/*
		FiledListenerDef[] listeners = this.getFormElement().getListeners();
		JSONArray jsonObject = JSONArray.fromObject(listeners);
		JSONArray jsonObject1 = JSONArray.fromObject(this.getFormElement()
				.getFilterfields());
		JSONArray jsonObject2 = JSONArray.fromObject(this.getFormElement()
				.getFiltervals());
		// 过滤字段列表
		if (this.getFormElement().getFilterfields() != null) {
			params.put("filterfields", jsonObject1.toString());
		} else {
			params.put("filterfields", "[]");
		}

		// 监听字段列表json
		if (this.getFormElement().getListeners() != null) {
			params.put("listeners", jsonObject.toString());
		} else {
			params.put("listeners", "[]");
		}
		// 过滤值
		if (this.getFormElement().getFiltervals() != null) {
			params.put("filtervals", jsonObject2.toString());
		} else {
			params.put("filtervals", "[]");
		}
		params.put("def", this.getFormElement());
		// 找到对应字段值
		if (this.getFormElement().getElementcode() != null) {
			Object value = params.get(this.getFormElement().getElementcode());
			value = this.reverseConverter(value);
			params.put("value", value);
		}
		if (this.formElement
				.getFormElementDef()
				.getType()
				.equalsIgnoreCase(
						WebStaticConstant.FORM_ELEMENT_TYPE_PLACEHOLDER)) {
			this.formElement.setColspan(this.formElement.getColspan()
					* WebStaticConstant.BOOTSTRAP_COL_UNIT);
		} else {
			this.formElement.setColspan(this.formElement.getColspan()
					* WebStaticConstant.BOOTSTRAP_COL_UNIT
					- WebStaticConstant.BOOTSTRAP_COL_LABLE_LEN);
		}
*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElement#validate(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public void validate(Map formData) throws InvalidValueException {
		// TODO Auto-generated method stub
		/*
		Object value = formData.get(this.getFormElement().getElementcode());
		String svalue = reverseConverter(value);
		if (this.formElement
				.getFormElementDef()
				.getType()
				.equalsIgnoreCase(
						WebStaticConstant.FORM_ELEMENT_TYPE_PLACEHOLDER)) {
			return;
		}
		//
		if (WebStaticConstant.INPUT_FIELD_REQUIRED
				.equalsIgnoreCase(this.formElement.getRequired())
				&& (svalue == null || svalue.equalsIgnoreCase(""))) {
			throw new InvalidValueException("请填写字段"
					+ this.formElement.getLable() + "的值。");
		}
		if (this.formElement.getMinlength() != null
				&& this.formElement.getMinlength().intValue() > svalue.length()) {
			throw new InvalidValueException("字段"
					+ this.formElement.getLable() + "的长度至少为："
					+ this.formElement.getMinlength() + "位。");
		}
		if (this.formElement.getMaxlength() != null
				&& this.formElement.getMaxlength().intValue() < svalue.length()) {
			throw new InvalidValueException("字段"
					+ this.formElement.getLable() + "的长度最长为："
					+ this.formElement.getMinlength() + "位。");
		}
		if (this.getFormElement().getServerpattern() != null
				&& !this.getFormElement().getServerpattern()
						.equalsIgnoreCase("")) {
			Pattern pattern = Pattern.compile(this.getFormElement()
					.getServerpattern());
			Matcher matcher = pattern.matcher(svalue);
			if (!matcher.matches()) {
				throw new InvalidValueException("字段"
						+ this.formElement.getLable() + " 值不符合规范。");
			}

		}*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElement#formater(java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Object formater(Map formData) {
		// TODO Auto-generated method stub
		Object value = formData.get(this.getFormElement().getElementcode());
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElement#forwardConverter(java.lang
	 * .String)
	 */
	@Override
	public Object forwardConverter(String value) {
		// TODO Auto-generated method stub
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElement#reverseConverter(java.lang
	 * .Object)
	 */
	@Override
	public String reverseConverter(Object value) {
		// TODO Auto-generated method stub
		return value == null ? "" : value.toString();
	}

	/**
	 * 子类设置渲染需要的参数
	 * 
	 * @param param
	 */
	protected abstract Map getCustomRenderParam(Map formdata);

}
