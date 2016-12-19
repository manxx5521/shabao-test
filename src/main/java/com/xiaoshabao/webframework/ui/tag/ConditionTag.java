package com.xiaoshabao.webframework.ui.tag;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xiaoshabao.webframework.ui.service.FormService;
/**
 * 条件区标签
 */
public class ConditionTag extends TagSupport {

	private static final long serialVersionUID = -5132755806980503545L;

	@Resource(name = "formServiceImpl")
	FormService formService;

	/** 元素id **/
	protected String id;

	// 头标签加CSS
	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(this.formService.getTemplateData(this.id).getHtml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	// 尾部签加JS
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void setId(String id) {
		this.id = id;
	}
}
