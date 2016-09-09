package com.xiaoshabao.webframework.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xiaoshabao.webframework.util.ResourceManager;

public class ResourceTag extends TagSupport {
	private static final long serialVersionUID = 6954327847483718507L;
	
	protected String type = "all";
	
	protected String value;
	
	/**
	 * 头标签加CSS
	 */
	public int doStartTag() throws JspException {
		if(this.type.equals("all")||this.type.equals("css")){
			try {
				JspWriter out = this.pageContext.getOut();
				ResourceManager manager=ResourceManager.getInstance();
				StringBuffer sb = new StringBuffer();
				String[] values = value.split(",");
				for(String value:values){
					sb.append(manager.getCssTag(value));
				}
				out.print(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return EVAL_PAGE;
	}
	/**
	 * 尾部签加JS
	 */
	public int doEndTag() throws JspException {
		if(this.type.equals("all")||this.type.equals("js")){
			try {
				JspWriter out = this.pageContext.getOut();
				ResourceManager manager=ResourceManager.getInstance();
				StringBuffer sb = new StringBuffer();
				String[] values= value.split(",");
				for(String value:values){
					sb.append(manager.getJSTag(value));
				}
				out.print(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return EVAL_PAGE;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
