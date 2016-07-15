package com.xiaoshabao.webframe.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

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
				String contextPath = this.pageContext.getSession().getServletContext().getContextPath();
				StringBuffer sb = new StringBuffer();
				String[] values = value.split(",");
				for(String value:values){
					switch (value) {
					case "bootstrap":
						sb.append("<link href=\"" + contextPath + "/resources/plugins/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" />");
						sb.append("<link href=\"" + contextPath + "/resources/plugins/bootstrap/css/font-awesome.min.css\" rel=\"stylesheet\" />");
						sb.append("<link href=\"" + contextPath + "/resources/plugins/bootstrap/css/animate.min.css\" rel=\"stylesheet\" />");
						break;
					//系统模块基本
					case "system":
						sb.append("<link href=\"" + contextPath + "/resources/system/css/style.min.css\" rel=\"stylesheet\" />");
						sb.append("<link href=\"" + contextPath + "/resources/system/img/favicon.ico\" rel=\"shortcut icon\" />");
						break;
					case "jstree":
						sb.append("<link href=\"" + contextPath + "/resources/plugins/jstree/default/style.min.css\" rel=\"stylesheet\" />");
						sb.append(" <style> .jstree-open>.jstree-anchor>.fa-folder:before{content:\"\f07c\"}.jstree-default .jstree-icon.none{width:0}</style>");
						break;
					case "swiper":
						sb.append("<link href=\"" + contextPath + "/resources/plugins/swiper/swiper.min.css\" rel=\"stylesheet\" />");
						break;
					default:
						break;
					}
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
				String contextPath = this.pageContext.getSession().getServletContext().getContextPath();
				StringBuffer sb = new StringBuffer();
				String[] values= value.split(",");
				for(String value:values){
					switch (value) {
					case "jquery":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/jquery/jquery-1.12.4.min.js\"></script>");
						break;
					case "jqueryui":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/jquery-ui/jquery-ui.min.js\"></script>");
						break;
					case "bootstrap":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/bootstrap/js/bootstrap.min.js\"></script>");
						sb.append("<script src=\"" + contextPath + "/resources/plugins/bootbox/bootbox.js\"></script>");
						break;
					//系统模块基础类
					case "system":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/bootbox/bootbox.js\"></script>");
						sb.append("<script src=\"" + contextPath + "/resources/component/csbox.js\"></script>");
						sb.append("<script src=\"" + contextPath + "/resources/system/js/content.min.js\"></script>");
						break;
						//系统模块基础类
					case "jstree":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/jstree/jstree.min.js\"></script>");
						break;
					case "validate":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/jquery-validation/jquery.validate.min.js\"></script>");
						sb.append("<script src=\"" + contextPath + "/resources/plugins/jquery-validation/localization/messages_zh.min.js\"></script>");
						break;
					//动态表单tree
					case "dtree":
						sb.append("<script src=\"" + contextPath + "/resources/component/jquery.tree.js\"></script>");
						break;
					//灯箱插件
					case "colorbox":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/colorbox/jquery.colorbox-min.js\"></script>");
						break;
					//瀑布流插件
					case "masonry":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/masonry/jquery.masonry.min.js\"></script>");
						break;
						//瀑布流插件
					case "swiper":
						sb.append("<script src=\"" + contextPath + "/resources/plugins/swiper/swiper.jquery.min.js\"></script>");
						break;
					default:
						break;
					}
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
