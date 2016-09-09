package com.xiaoshabao.webframework.component;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframe.exception.ServiceException;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Component("templateEngine")
public class TemplateEngine {
	
	static Logger logger = LoggerFactory.getLogger(TemplateEngine.class);

	private static Configuration templateConfig = new Configuration(Configuration.getVersion()) ;
	
	private static StringTemplateLoader loader = new StringTemplateLoader();
	
	static{
		templateConfig.setTemplateLoader(loader);
	}
	
	/**
	 * 渲染模板
	 * @param key
	 * @param template
	 * @param params
	 * @return
	 */
	public static String renderTemplate(String key,String templateSource,Object params) throws ServiceException{
		loader.putTemplate(key, templateSource);
		try {
		Template template = templateConfig.getTemplate(key);
		StringWriter out = new StringWriter();
		
		template.process(params, out);
		
		return out.toString();
		} catch (Exception e) {
			logger.error("render template for {} error:",key,e);
			e.printStackTrace();
			throw new ServiceException(key+"模板渲染异常"+e.getLocalizedMessage());
		}
	}

}
