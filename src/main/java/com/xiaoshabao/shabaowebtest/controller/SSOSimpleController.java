package com.xiaoshabao.shabaowebtest.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.webframework.controller.AbstractController;

/**
 * 单点登录
 * <p>(同域)当访问一个需要登录的界面时，先验证是否有cookie票据。有的话跳过登录操作。
 * 没有的话进行验证登录。如果时shiro的话在登录验证之前添加拦截器实现。
 * 不同域的思路是，链接填全路径。有统一验证服务，用来验证cookie，请求时将cookie发到
 * 验证服务器，验证有效性</p>
 */
@Controller
@RequestMapping(value = "/demo/sso")
public class SSOSimpleController extends AbstractController {
	
	private final static String ssoCookieName="ssoCookie";

	/**
	 * 访问的需要授权主页
	 * <p></p>
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index1")
	public ModelAndView index1(ModelMap model,HttpServletRequest request) {
		//验证cookie,如果有票据直接进入。没有就去登录
		if(checkCookie(request)){
			//有票据，sso登录。直接进入
			return new ModelAndView("/shabaotest/sso/loginSSO1");
		}
		
		//未通过，要去登录
		// 设置登录登录后要去的界面,根据不同的登录，跳转不同的主页
		model.put("gotoUrl", "/shabaotest/sso/indexSSO1");
		return new ModelAndView("/shabaotest/sso/login1");
	}
	
	// sso1登录页
	@RequestMapping(value = "/login1")
	public ModelAndView login(ModelMap model,String gotoUrl) {
		//设置登录登录后要去的界面,根据不同的登录，跳转不同的主页
		model.put("gotoUrl", gotoUrl);
		return new ModelAndView("/shabaotest/sso/loginSSO1");
	}

	/**
	 * 登录操作
	 * @param model
	 * @param response
	 * @param userId
	 * @param password
	 * @param gotoUrl 要跳转的url
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public ModelAndView doLogin(ModelMap model,HttpServletResponse response
			,String userId,String password, String gotoUrl){
		//验证用户名
		boolean state= checkLogin(userId,password);
		if(!state){
			//跳转失败
			return new ModelAndView("/shabaotest/sso/失败");
		}
		
		//验证通过后存储票据，存储的内容应该加密
		Cookie cookie=new Cookie(ssoCookieName,"sso");
//		cookie.setDomain(".xxxx.com");//设置到哪个域。在跨域单点时起作用。要设置到父域。进行统一验证
		cookie.setPath("/");//设置票据范围，/是代表顶层 当前域的范围
		response.addCookie(cookie);//添加cookie
		
//		return new ModelAndView("/shabaotest/sso/indexSSO1");
		return new ModelAndView(gotoUrl);
	}
	
	
	
	/**
	 * 验证登录信息
	 * @param userId
	 * @param password
	 * @return
	 */
	private boolean checkLogin(String userId,String password){
		if("user".equals(userId)&&"1".equals(password)){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证cookie中是否有票据
	 */
	public boolean checkCookie(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(ssoCookieName)
						&&cookie.getValue().equals("sso")){
					return true;
				}
			}
		}
		return false;
	}

}
