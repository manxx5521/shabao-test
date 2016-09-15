package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.webframework.component.ContextHolderUtils;
import com.xiaoshabao.webframework.component.SessionManager;
import com.xiaoshabao.webframework.ui.dao.ElementDao;
import com.xiaoshabao.webframework.ui.dto.SelectResultDto;
import com.xiaoshabao.webframework.ui.element.SelectUIElement;
import com.xiaoshabao.webframework.ui.entity.SelectElementDef;
import com.xiaoshabao.webframework.ui.service.WebElementService;
@Service("webElementServiceImpl")
public class WebElementServiceImpl extends UIElementServiceImpl implements WebElementService{
	@Autowired
	private ElementDao elementDao;
	@Resource(name="mybatisBaseDao")
	private BaseDao baseDao;
	@Resource(name="sessionManager")
	private SessionManager sessionManager;
	
	//下拉列表数据获取
	@Override
	public List<SelectResultDto> getSelectValues(Integer elementId) {
		SelectUIElement element=this.getElement(Long.valueOf(elementId),SelectUIElement.class);
		SelectElementDef selectInfo=(SelectElementDef) element.getFormElement().getFormElementDef();
		//获得参数
		HttpServletRequest request=ContextHolderUtils.getRequest();
		Map<String,Object> params=new HashMap<>();
		Enumeration<String> paramnames = request.getParameterNames();
		while (paramnames.hasMoreElements()) {
			String paramname = paramnames.nextElement();
			params.put(paramname,request.getParameter(paramname));
		}
		Map<String,Object> session=sessionManager.getSessionParams();
		params.put("session", session);
		
		SqlMapper sqlMapper=this.baseDao.getSqlMapper();
		List<SelectResultDto> list=null;
		if(selectInfo.getSourcetype()==1){
			list=sqlMapper.selectList("SELECT A.DATA_ID id, A.DATA_NAME text FROM TD_S_STATIC A WHERE A.TYPE_ID ='"+selectInfo.getCondition()+"'", params, SelectResultDto.class);
		}else if(selectInfo.getSourcetype()==2){
			StringBuffer sql=new StringBuffer();
			sql.append("select ");
			sql.append(selectInfo.getCodecolname());
			sql.append(" ID,");
			sql.append(selectInfo.getNamecolname());
			sql.append(" text from ");
			sql.append(selectInfo.getTablename());
			String condition=selectInfo.getCondition();
			if(StringUtils.isNotEmpty(condition)){
				sql.append(" where ");
				sql.append(selectInfo.getCondition());
			}
			list=sqlMapper.selectList(sql.toString(), params, SelectResultDto.class);
		}else if(selectInfo.getSourcetype()==3){
			list=sqlMapper.selectList(selectInfo.getSql(), params, SelectResultDto.class);
		}
		return list;
	}
	
}
