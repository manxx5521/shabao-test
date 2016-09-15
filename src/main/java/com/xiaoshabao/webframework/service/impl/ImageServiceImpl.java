package com.xiaoshabao.webframework.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.webframework.component.ContextHolderUtils;
import com.xiaoshabao.webframework.component.SessionManager;
import com.xiaoshabao.webframework.dto.ImageDto;
import com.xiaoshabao.webframework.entity.ElementEntity;
import com.xiaoshabao.webframework.entity.ImageElement;
import com.xiaoshabao.webframework.service.ImageService;
import com.xiaoshabao.webframework.ui.dao.ElementDao;
@Service("imageServiceImpl")
public class ImageServiceImpl extends AbstractServiceImpl implements ImageService{
	@Autowired
	private ElementDao elementDao;
	@Resource(name="sessionManager")
	private SessionManager sessionManager;
	
	//获得图片的数据列表
	@Override
	public List<ImageDto> getList(Integer elementId) {
		HttpServletRequest request=ContextHolderUtils.getRequest();
		Map<String,Object> params=new HashMap<>();
		Enumeration<String> paramnames = request.getParameterNames();
		while (paramnames.hasMoreElements()) {
			String paramname = paramnames.nextElement();
			params.put(paramname,request.getParameter(paramname));
		}
		ImageElement element=this.validation(elementId);
		String type=element.getType();
		List<ImageDto> list=null;
		SqlMapper sqlMapper=this.baseDao.getSqlMapper();
		Map<String,Object> session=sessionManager.getSessionParams();
		params.put("session", session);
		if(type.equals("1")){
			list=sqlMapper.selectList(element.getSql(), params, ImageDto.class);
		}
		return list;
	}
	
	/**
	 * 对数据进行验证
	 * @param elementId
	 * @return
	 */
	private ImageElement validation(Integer elementId){
		if(elementId==null||elementId==0){
			throw new ServiceException("传入的元素不能为空");
		}
		ElementEntity element=elementDao.getElementById(elementId);
		if(element==null||element.getElementId()==null||element.getElementId()==0){
			throw new ServiceException("没有获取到对应元素");
		}
		String json=element.getParam();
		if(StringUtils.isEmpty(json)){
			throw new ServiceException("没有获得对应参数");
		}
		ImageElement param=JSONObject.parseObject(json, ImageElement.class);
		if(StringUtils.isEmpty(param.getType())){
			throw new ServiceException("参数的类型不能为空");
		}
		if(StringUtils.isEmpty(param.getSql())){
			throw new ServiceException("参数的取数sql不能为空");
		}
		return param;
	}

	

}
