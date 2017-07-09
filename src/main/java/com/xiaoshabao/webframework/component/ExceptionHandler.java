package com.xiaoshabao.webframework.component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.baseframework.exception.MsgInfoException;
import com.xiaoshabao.baseframework.exception.ServiceException;

/**
 * 统一异常处理
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	protected Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		response.setHeader("Content-Type", "text/html;charset=UTF-8");// 解决输出中文乱码
		// 是否异步请求
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 根据不同错误转向不同页面
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("ex", ex);
			response.setStatus(1000);// 其他异常返回 1000
			return new ModelAndView("WEB-INF/error", model);
		} else {
			//http://blog.csdn.net/chwshuang/article/details/48089203 具体方法文档
			ModelAndView mv = new ModelAndView();
			/* 使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常 */
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("success", false);
			if (ex instanceof MsgInfoException) {
			}
			if (ex instanceof MsgErrorException) {
			}
			if (ex instanceof ServiceException) {
				logger.info("ajax捕获业务异常", ex);
			} else {
				logger.info("ajax捕获未知异常", ex);
			}
			attributes.put("message", ex.getMessage());
			view.setAttributesMap(attributes);
			mv.setView(view);
			return mv;
		}
	}
}
