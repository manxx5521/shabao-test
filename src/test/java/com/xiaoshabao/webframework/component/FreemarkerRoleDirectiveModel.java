package com.xiaoshabao.webframework.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;
/**
 * role自定义指令
 */
public class FreemarkerRoleDirectiveModel implements TemplateDirectiveModel{

	/*
	 * loopVars 返回参数
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "deprecation" }) 
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		System.out.println("---->进入role指令");
		TemplateScalarModel userid=(TemplateScalarModel) params.get("userid");//转换成不同的类型参数  TemplateScalarModel 是字符串类型
		TemplateScalarModel roleid=(TemplateScalarModel) params.get("roleid");
		
		//如果时123456用户并且是admin角色
		if("123456".equals(userid.getAsString())&&"admin".equals(roleid.getAsString())){
			loopVars[0]=TemplateBooleanModel.TRUE;//第一参数返回真
		}
		
		List<String> rights=new ArrayList<String>();
		rights.add("select");
		rights.add("delete");
		rights.add("update");
		loopVars[1]=new SimpleSequence(rights);//第二个参数返回列表
		
		body.render(env.getOut());//输出变量
	}

}
