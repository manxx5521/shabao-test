package com.xiaoshabao.webframework.component;

import java.io.IOException;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
/**
 * freeMarker 模版引擎
 */
@Component("templateEngine")
public class TemplateEngine {
	
	static Logger logger = LoggerFactory.getLogger(TemplateEngine.class);

	private static Configuration templateConfig = new Configuration(Configuration.getVersion()) ;
	
	private static StringTemplateLoader loader = new StringTemplateLoader();
	
	static{
		ClassTemplateLoader deftemplatedir = new ClassTemplateLoader(
				TemplateEngine.class, "/freemarker/");
		MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[] {loader, deftemplatedir});
		templateConfig.setDefaultEncoding("UTF-8");
		templateConfig.setTemplateLoader(mtl);
	}
	
	/**
	 * 渲染模板(传入字符串进行解析)
	 * @param key 模版id
	 * @param template 模版字符串文件
	 * @param params 参数
	 * @return 解析后的文本
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 */
	public static String renderTemplate(String key, String templateSource,
			Object params) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		// 设置成模版
		loader.putTemplate(key, templateSource);
		return processTemplate(key, params);
	}
	
	/**
	 * 渲染模板（从模版配置文件加载）
	 * @param name 文件名，可以带路径
	 * @param params 参数
	 * @return 解析后的文本
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 */
	public static String renderTemplate(String name, Object params)
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		return processTemplate(name, params);
	}
	
	/**
	 * 模版执行（具体执行freeMarker模版）
	 * @param name 模版名
	 * @param params 参数
	 * @return 解析后的文本
	 * @throws IOException 模版读取异常
	 * @throws ParseException 解析异常
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException  模版未发现
	 * @throws TemplateException 
	 */
	private static String processTemplate(String name, Object params)
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		Template template = templateConfig.getTemplate(name);
		StringWriter out = new StringWriter();
		template.process(params, out);
		return out.toString();
	}

}
