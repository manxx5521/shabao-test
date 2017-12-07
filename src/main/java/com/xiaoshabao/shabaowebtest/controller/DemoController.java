package com.xiaoshabao.shabaowebtest.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.xiaoshabao.baseframework.exception.DaoException;
import com.xiaoshabao.shabaowebtest.dto.DemoDto;
import com.xiaoshabao.shabaowebtest.service.DemoService;
import com.xiaoshabao.webframework.controller.AbstractController;

/**
 * 实例controller
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController extends AbstractController {

	@Resource(name = "demoService")
	private DemoService demoService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public ModelAndView listDemo(ModelMap model) {
		// 重定向设置,详细见http://blog.csdn.net/hermaeuxmora/article/details/51781033
		// return "redirect:*******";
		// return "forward:*******";
		return new ModelAndView("/shabaotest/demo/listDemo");
	}

	/**
	 * 获得详细信息
	 * <p>
	 * \@PathVariable:此注解绑定模版变量，名称相同时可以不填加名称参数。注解可以作用在map上，自动装入。<br>
	 * \@RequestParam(path="id",required=false) 指定请求参数可选<br>
	 * \@RequestBody 可以将请求主题映射到字符串<br>
	 * \@CookieValue 可以获得cookie值<br>
	 * \@RequestHeader 可以获得响应表头数据<br>
	 * </p>
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}", method = { RequestMethod.POST }, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public String getDemoDetail(@PathVariable("id") Integer id) {

		return "成功返回";
	}

	// ----------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/test")
	public ModelAndView reqMap(ModelMap model) throws DaoException {
		DemoDto dto = this.demoService.testSQL();
		model.addAttribute("date", dto.getDate());
		return new ModelAndView("/shabaotest/demo/demo");
	}

	@RequestMapping(value = "/test1")
	public ModelAndView test1(ModelMap model) {
		return new ModelAndView("/system/test/modeltest");
	}

	/************ 高级用法 *******************/

	/**
	 * 自定义绑定，将特定的字符串绑定到特殊类型上
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	/**
	 * 可以访问请求和响应的主体
	 * <p>
	 * BindingResult可以获得绑定结果，可以获得数据绑定时的错误信息。<br>
	 * </>
	 * 
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping("/demo/handle1")
	public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity, BindingResult result) {
		String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader");
		@SuppressWarnings("unused")
		byte[] requestBody = requestEntity.getBody();

		// 操作

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyRequestHeader", requestHeader);
		return new ResponseEntity<String>("Hello world", responseHeaders, HttpStatus.CREATED);
	}

	/**
	 * 异步返回内容
	 * <p>
	 * 异步更高级用法参见：http://blog.csdn.net/hermaeuxmora/article/details/51682234
	 * </p>
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/demo/handle2", method = RequestMethod.POST)
	public Callable<String> processUpload(final MultipartFile file) {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				// 具体操作 一部返回内容，每次返回一部分
				return "someView";
			}
		};

	}

	/**
	 * 异步返回数据流，用来下载文件等
	 * <p>
	 * 异步更高级用法参见：http://blog.csdn.net/hermaeuxmora/article/details/51718792
	 * </p>
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/download")
	public StreamingResponseBody handle() {
		return new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				// write...
			}
		};
	}

	/**
	 * 上传文件，高级用法参考:http://blog.csdn.net/hermaeuxmora/article/details/52084015
	 * 
	 * @param name
	 * @param file
	 * @return
	 */
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			// byte[] bytes = file.getBytes();
			// 把这些字节存入某处
			return "redirect:uploadSuccess";
		}

		return "redirect:uploadFailure";
	}

}
