package com.xiaoshabao.webframework.ui.component;


/**
 * 静态变量存储
 */
public class FormConstants {
	/**请求session表示名称**/
	public final static String REQ_SESSION_TAG="form_session_key";
	/**请求id表示名称**/
	public final static String REQ_ID_TAG="form_id_key";
	/**请求视图类型标识**/
	public final static String REQ_VIEW_TYPE_ENUM="form_viewTypeEnum";
	
	public final static String ELEMENT_TEMPLATE_ID="templateId";
	public final static String ELEMENT_ELEMENT_ID="elementId";
	public final static String ELEMENT_FIELD_CODE="fieldCode";
	public final static String ELEMENT_LABEL="label";
	public final static String ELEMENT_VALUE="value";
	
	/** 元素模版-展示 **/
	public final static String TEMPLATE_VIEW="viewTemplate";
	/** 元素模版-只读 **/
	public final static String TEMPLATE_READ="readTemplate";
	
	/** 引擎类型-表单 **/
	public final static Integer ENGINE_TYPE_TEMPLATE=1;
	/** 引擎类型-表型 **/
	public final static Integer ENGINE_TYPE_REPORT=2;
	
	
	/** 头部引用字符串，包括全部模版 **/
	public final static String HEADER_STR_ALL="header";
	/** 头部引用字符串，展现模版 **/
	public final static String HEADER_STR_VIEW="viewHear";
	/** 头部引用字符串，只读模版 **/
	public final static String HEADER_STR_READ="readHear";
	
	/** 字段属性key **/
	public final static int FIELD_ATTR_KEY=1;
	/** 字段属性value **/
	public final static int FIELD_ATTR_VALUE=2;
	

}
