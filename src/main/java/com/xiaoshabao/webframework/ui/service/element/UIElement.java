package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.FormValidateInfo;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;

public interface UIElement extends AbstractElement {

	/**
	 * 验证前台数据是否正确(元素的特殊验证)
	 * <p>
	 * 使用于增删改，如果有验证不通过的参数向reslut中填充提示。
	 * </p>
	 * 
	 * @param result
	 *            返回结果
	 * @param data
	 *            前台数据
	 * @param elementDto
	 * @param elementParams
	 *            元素参数
	 */
	public FormValidateInfo validateData(Map<String, Object> data,
			ElementColumnDto elementDto, Map<String, Object> elementParams);

	/**
	 * 获得元素自定义参数
	 * <p>
	 * 结果写到elementParams中
	 * </p>
	 * 
	 * @param elementDto
	 * @param elementParams
	 *            元素参数
	 * @return
	 */
	public void getCustomParams(ElementColumnDto elementDto,
			Map<String, Object> data, Map<String, Object> elementParams);

	/**
	 * 获得元素参数（个性化参数，具体子类重写）
	 * 
	 * @param elementDto
	 * @param data
	 * @param elementParams
	 */
	public String render(ElementEntity element, Map<String, Object> params,
			boolean isReadOnly);

	// -----------------------
	/**
	 * 无参数render
	 */
	public String render(Map<String, Object> params, ElementEntity element);

	/**
	 * render,按类型
	 * 
	 * @param params
	 * @param templateTypeName
	 * @return
	 */
	public String render(Map<String, Object> params, ElementEntity element,
			String templateTypeName);

	/**
	 * 设置公共参数
	 * 
	 * @param params
	 */
	public void setPublicProperties(Map<String, Object> params,
			TemplateElementEntity tempalteElement, ElementEntity element);

	/**
	 * 设置元素自定义参数
	 * 
	 * @param params
	 */
	public void setCustomParams(Map<String, Object> params, JSONObject paramJSON);

}
