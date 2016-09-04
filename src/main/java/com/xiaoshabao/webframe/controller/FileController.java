package com.xiaoshabao.webframe.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiaoshabao.baseframe.component.SysConfig;
import com.xiaoshabao.baseframe.controller.AbstractController;
/**
 * 文件上传操作
 */
@Controller
@RequestMapping(value="/file")
public class FileController extends AbstractController{
	@Resource(name="sysConfig")
	private SysConfig sysConfig;
	
	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @param dir 选填上传目录  /resources/upload/
	 * @param type image和attached 两种形式默认attached
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="upload",produces="text/html;charset=UTF-8")
	@RequestMapping(value="/upload")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request,HttpServletResponse response,String dir,String type) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String separator=File.separator;
		/** 真实保存目录 */ 
		String savePath = request.getSession().getServletContext().getRealPath("/");
		if(!savePath.endsWith(separator)){
			savePath+=separator;
		} 
		/** 保存URL,基于网站根目录*/
		String saveUrl="/";
		if(StringUtils.isEmpty(dir)){
			savePath=savePath+ "resources"+separator+"upload"+separator;
			saveUrl+="/resources/upload/";
		}else{
			if(dir.startsWith("/")){
				dir=dir.replaceFirst("/", "");
			}
			if(!dir.endsWith("/")){
				dir+="/";
			}
			saveUrl+=dir;
			if(!separator.equals("/")){
				String[] strs=dir.split("/");
				dir="";
				for(String s:strs){
					dir+=s+separator;
				}
			}
			savePath=savePath+dir; 
		}
		
		//设置默认类型
		if(StringUtils.isEmpty(type)){
			type="attached";
		}

		// 定义允许上传的文件扩展名
		Map<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", sysConfig.getFileImage());
		extMap.put("attached", sysConfig.getFileAttached());

		// 最大文件大小
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");

		}
		// 检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");

		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + separator;
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> filenames = multipartRequest.getFileNames();
		while (filenames.hasNext()) {
			String filename = filenames.next();
			List<MultipartFile> fileList = multipartRequest.getFiles(filename);
			for (MultipartFile multipartFile : fileList) {
				if (multipartFile.getSize() > maxSize) {
					return getError("上传文件大小超过限制。");

				}
				String fileName = multipartFile.getOriginalFilename();
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(type).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(type) + "格式。");

				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					OutputStream stram = new FileOutputStream(uploadedFile);
					stram.write(multipartFile.getBytes());
					stram.close();
					returnMap.put("code", 0);
//					returnMap.put("url", request.getContextPath()+saveUrl + newFileName);
					returnMap.put("url", saveUrl + newFileName);
					returnMap.put("name", newFileName);
					returnMap.put("size", multipartFile.getSize());
				} catch (Exception e) {
					return getError("上传文件失败。");
				}
			}
		}
		return returnMap;
//		return JSONObject.fromObject(returnMap).toString();
	}
	
	/**
	 * 错误信息
	 * @param message
	 * @return
	 */
	private Map<String, Object> getError(String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// JSONObject obj = new JSONObject();
		returnMap.put("code", -1);
		returnMap.put("message", message);
//		return JSONObject.fromObject(returnMap).toString();
		return returnMap;
	}

}
