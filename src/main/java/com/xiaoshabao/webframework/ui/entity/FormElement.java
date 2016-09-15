package com.xiaoshabao.webframework.ui.entity;

import java.util.List;

public class FormElement {
	protected FormElementDef formElementDef;
	protected String templateid;
	protected Long elementid;
	protected String elementcode;
	protected Integer order;
	protected String lable;
	protected String areacode;
	protected String showright;
	protected String modifyright;
	protected Integer formproperty;
	protected String validexpr;
	protected String required;
	protected String initvalue;
	protected String extraparam;
	/**
	 * 行索引
	 */
	protected Integer rowindex;
	/**
	 * 列索引
	 */
	protected Integer colindex;
	/**
	 * 列span
	 */
	protected Integer colspan;
	/**
	 * 监听字段列表
	 */
	protected FiledListenerDef[] listeners;
	/**
	 * 被监听字段列表
	 */
	protected FilterFieldDef[] filterfields;
	/**
	 * 过滤数据字段和值列表
	 */
	protected List<FilterValue> filtervals;
	/**
	 * 字段长度最小长度
	 */
	protected Integer minlength ;
	/**
	 * 字段最大长度
	 */
	protected Integer maxlength ;
	/**
	 * 服务端验证正则表达式
	 */
	protected String serverpattern ;
	/**
	 * 客户端正则表达式
	 */
	protected String clientpattern ;
	 
	/**
	 * @return the formElementDef
	 */
	public FormElementDef getFormElementDef() {
		return formElementDef;
	}

	/**
	 * @param formElementDef
	 *            the formElementDef to set
	 */
	public void setFormElementDef(FormElementDef formElementDef) {
		this.formElementDef = formElementDef;
	}

	/**
	 * @return the templateid
	 */
	public String getTemplateid() {
		return templateid;
	}

	/**
	 * @param templateid
	 *            the templateid to set
	 */
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	/**
	 * @return the elementid
	 */
	public Long getElementid() {
		return elementid;
	}

	/**
	 * @param elementid
	 *            the elementid to set
	 */
	public void setElementid(Long elementid) {
		this.elementid = elementid;
	}

	/**
	 * @return the elementcode
	 */
	public String getElementcode() {
		return elementcode;
	}

	/**
	 * @param elementcode
	 *            the elementcode to set
	 */
	public void setElementcode(String elementcode) {
		this.elementcode = elementcode;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the lable
	 */
	public String getLable() {
		return lable;
	}

	/**
	 * @param lable
	 *            the lable to set
	 */
	public void setLable(String lable) {
		this.lable = lable;
	}

	/**
	 * @return the areacode
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * @param areacode
	 *            the areacode to set
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * @return the showright
	 */
	public String getShowright() {
		return showright;
	}

	/**
	 * @param showright
	 *            the showright to set
	 */
	public void setShowright(String showright) {
		this.showright = showright;
	}

	/**
	 * @return the modifyright
	 */
	public String getModifyright() {
		return modifyright;
	}

	/**
	 * @param modifyright
	 *            the modifyright to set
	 */
	public void setModifyright(String modifyright) {
		this.modifyright = modifyright;
	}

	/**
	 * @return the formproperty
	 */
	public Integer getFormproperty() {
		return formproperty;
	}

	/**
	 * @param formproperty
	 *            the formproperty to set
	 */
	public void setFormproperty(Integer formproperty) {
		this.formproperty = formproperty;
	}

	/**
	 * @return the validexpr
	 */
	public String getValidexpr() {
		return validexpr;
	}

	/**
	 * @param validexpr
	 *            the validexpr to set
	 */
	public void setValidexpr(String validexpr) {
		this.validexpr = validexpr;
	}

	/**
	 * @return the required
	 */
	public String getRequired() {
		return required;
	}

	/**
	 * @param required
	 *            the required to set
	 */
	public void setRequired(String required) {
		this.required = required;
	}

	/**
	 * @return the initvalue
	 */
	public String getInitvalue() {
		return initvalue;
	}

	/**
	 * @param initvalue
	 *            the initvalue to set
	 */
	public void setInitvalue(String initvalue) {
		this.initvalue = initvalue;
	}

	/**
	 * @return the extraparam
	 */
	public String getExtraparam() {
		return extraparam;
	}

	/**
	 * @param extraparam
	 *            the extraparam to set
	 */
	public void setExtraparam(String extraparam) {
		this.extraparam = extraparam;
	}

	/**
	 * 是否流程变量
	 * 
	 * @return
	 */
	public boolean isProcessElement() {
		Integer isprocess=this.getFormproperty()==null?0:1;
		return 1 == isprocess;
	}

	/**
	 * @return the listeners
	 */
	public FiledListenerDef[] getListeners() {
		return listeners;
	}

	/**
	 * @param listeners
	 *            the listeners to set
	 */
	public void setListeners(FiledListenerDef[] listeners) {
		this.listeners = listeners;
	}

	/**
	 * @return the filterfields
	 */
	public FilterFieldDef[] getFilterfields() {
		return filterfields;
	}

	/**
	 * @param filterfields
	 *            the filterfields to set
	 */
	public void setFilterfields(FilterFieldDef[] filterfields) {
		this.filterfields = filterfields;
	}

	/**
	 * @return the filtervals
	 */
	public List<FilterValue> getFiltervals() {
		return filtervals;
	}

	/**
	 * @param filtervals
	 *            the filtervals to set
	 */
	public void setFiltervals(List<FilterValue> filtervals) {
		this.filtervals = filtervals;
	}

	/**
	 * @return the rowindex
	 */
	public Integer getRowindex() {
		return rowindex;
	}

	/**
	 * @param rowindex
	 *            the rowindex to set
	 */
	public void setRowindex(Integer rowindex) {
		this.rowindex = rowindex;
	}

	/**
	 * @return the colindex
	 */
	public Integer getColindex() {
		return colindex;
	}

	/**
	 * @param colindex
	 *            the colindex to set
	 */
	public void setColindex(Integer colindex) {
		this.colindex = colindex;
	}

	/**
	 * @return the colspan
	 */
	public Integer getColspan() {
		return colspan;
	}

	/**
	 * @param colspan
	 *            the colspan to set
	 */
	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	/**
	 * @return the minlength
	 */
	public Integer getMinlength() {
		return minlength;
	}

	/**
	 * @param minlength the minlength to set
	 */
	public void setMinlength(Integer minlength) {
		this.minlength = minlength;
	}

	/**
	 * @return the maxlength
	 */
	public Integer getMaxlength() {
		return maxlength;
	}

	/**
	 * @param maxlength the maxlength to set
	 */
	public void setMaxlength(Integer maxlength) {
		this.maxlength = maxlength;
	}

	/**
	 * @return the serverpattern
	 */
	public String getServerpattern() {
		return serverpattern;
	}

	/**
	 * @param serverpattern the serverpattern to set
	 */
	public void setServerpattern(String serverpattern) {
		this.serverpattern = serverpattern;
	}

	/**
	 * @return the clientpattern
	 */
	public String getClientpattern() {
		return clientpattern;
	}

	/**
	 * @param clientpattern the clientpattern to set
	 */
	public void setClientpattern(String clientpattern) {
		this.clientpattern = clientpattern;
	}

}
