package com.xiaoshabao.shabaotest.module.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * 网页抓取工具
 * <p>相当于浏览器可以执行js</p>
 */
public class HttpUnitTest {
  
  public static void main (String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
    /*final WebClient webClient=new WebClient();
    webClient.getOptions().setCssEnabled(false);
//    webClient.getOptions().setJavaScriptEnabled(false);
    final HtmlPage page=webClient.getPage("http://weibo.com/p/1006051227328177/photos?from=page_100605&mod=TAB#place");
    System.out.println(page.asText());
    webClient.close();*/
    
    
    WebClient webClient = new WebClient();
    // 启用JS解释器，默认为true 
    webClient.getOptions().setJavaScriptEnabled(true); 
    // 禁用css支持
    webClient.getOptions().setCssEnabled(false); 
    // 设置Ajax异步处理控制器即启用Ajax支持
    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    // 当出现Http error时，程序不抛异常继续执行
    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    // js运行错误时，是否抛出异常。 防止js语法错误抛出异常
    webClient.getOptions().setThrowExceptionOnScriptError(false); 
    
    webClient.setHTMLParserListener(HTMLParserListener.LOG_REPORTER);
    System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "error");

    // 拿到这个网页
    HtmlPage page = webClient.getPage("http://tu.fengniao.com/62/");
    String str = page.asText();
    str=page.asXml();
    
    DomElement dom=page.getElementById("container");
    Iterator<DomNode> iterables=dom.getChildren().iterator();
    while(iterables.hasNext()) {
    	DomNode node=iterables.next();
    	List<HtmlAnchor> projectAList=node.getByXPath("//a[@class='pic']");
    	if(projectAList!=null&&projectAList.size()==1) {
    		String urla=projectAList.get(0).getAttributes().getNamedItem("href").getNodeValue();
    		System.out.println();
    	}
    	
    }
    HtmlElement htmlElement=page.getDocumentElement();
    // 填入用户名和密码
    HtmlInput username = (HtmlInput) page.getElementById("userName");
    username.type("yourAccount");
    HtmlInput password = (HtmlInput) page.getElementById("password");
    password.type("yourPassword");

    // 提交
    HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");
    HtmlPage nextPage = submit.click();
    System.out.println(nextPage.asXml());
  }

}
