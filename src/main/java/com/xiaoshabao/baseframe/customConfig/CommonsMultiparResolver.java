package com.xiaoshabao.baseframe.customConfig;
/**
 * 实现自己的spring上传设置
 * <P>如果配置此文件上传解析器，可以对特定的URL进行特殊操作</P>
 */
public class CommonsMultiparResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver{
	
	@Override  
    public boolean isMultipart(javax.servlet.http.HttpServletRequest request) {  
          
        String uri = request.getRequestURI();  
        //过滤使用百度EmEditor的URI  
        if (uri.indexOf("ueditor/dispatch.html") > 0) {   
            return false;  
        } 
        return super.isMultipart(request);  
    }  
}
