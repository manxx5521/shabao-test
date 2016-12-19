package com.xiaoshabao.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframework.controller.AbstractController;
import com.xiaoshabao.system.service.LoginService;
import com.xiaoshabao.webframework.dto.AjaxResult;

/**
 * 后台登录
 */
@Controller
public class LoginController extends AbstractController {

	@Resource(name = "loginServiceImpl")
	private LoginService loginService;

	/**
	 * 后台登录界面跳转URL
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/admin/login")
	public ModelAndView adminLoginURL(ModelMap model) {
		return new ModelAndView("/system/login", model);
	}

	/**
	 * 后台管理登录(shiro)
	 * 
	 * @param user_id
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录结果
	 */
	@RequestMapping(value = "/admin/loginIN", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> adminLoginByShiro(String user_id, String password) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user_id,
				password);
		try {
			subject.login(token);
			logger.info("\n" + user_id + " 登录成功\n");
			returnMap.put("success", true);
		}catch(ExcessiveAttemptsException ea){
			logger.info("用户被锁定*********");
			ea.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("message", "用户已经被锁定，请稍候重试。或者联系管理员！");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("message", "用户名或密码错误！");
		}
		return returnMap;
	}
	
	/**
	 * 后台管理登录(shiro)
	 * 
	 * @param user_id
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录结果
	 */
	@RequestMapping(value = "/admin/loginIN;jsessionid=*", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> adminLoginByShiroSESSION(String user_id, String password) {
		return this.adminLoginByShiro(user_id, password);
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/admin/logout")
	@ResponseBody
	public AjaxResult logout() {
		try {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return new AjaxResult(true,"安全退出成功！");
		} catch (Exception e) {
			logger.error("安全退出失败");
			e.printStackTrace();
			return new AjaxResult("安全退出失败");
		}
	}
}
