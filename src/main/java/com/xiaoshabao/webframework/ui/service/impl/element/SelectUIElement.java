package com.xiaoshabao.webframework.ui.service.impl.element;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.SelectResultDto;
import com.xiaoshabao.webframework.ui.service.element.WebElement;
import com.xiaoshabao.webframework.ui.service.impl.element.def.SelectElementDef;

/**
 * 下拉列表
 */
@Service("selectUIElement")
public class SelectUIElement extends AbstractUIElement implements WebElement{
	
	//web数据AJAX响应
	@Override
	public AjaxResult getElementResponse(Map<String, Object> params,JSONObject paramJSON) {
		SelectElementDef selectInfo=JSONObject.toJavaObject(paramJSON, SelectElementDef.class);
		SqlMapper sqlMapper=this.baseDao.getSqlMapper();
		List<SelectResultDto> list=null;
		if(selectInfo.getType()==1){
			list=sqlMapper.selectList("SELECT A.DATA_ID id, A.DATA_NAME text FROM TD_S_STATIC A WHERE A.TYPE_ID ='"+selectInfo.getCondition()+"'", params, SelectResultDto.class);
		}else if(selectInfo.getType()==2){
			StringBuffer sql=new StringBuffer();
			sql.append("select ");
			sql.append(selectInfo.getId());
			sql.append(" ID,");
			sql.append(selectInfo.getText());
			sql.append(" text from ");
			sql.append(selectInfo.getTable());
			String condition=selectInfo.getCondition();
			if(StringUtils.isNotEmpty(condition)){
				sql.append(" where ");
				sql.append(selectInfo.getCondition());
			}
			list=sqlMapper.selectList(sql.toString(), params, SelectResultDto.class);
		}else if(selectInfo.getType()==3){
			list=sqlMapper.selectList(selectInfo.getSql(), params, SelectResultDto.class);
		}
		return new AjaxResult(true,"刷新成功",list);
	}

}
