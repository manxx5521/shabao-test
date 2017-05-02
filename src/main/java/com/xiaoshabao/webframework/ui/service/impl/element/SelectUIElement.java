package com.xiaoshabao.webframework.ui.service.impl.element;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.SelectResultDto;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.service.TableColumnService;
import com.xiaoshabao.webframework.ui.service.element.WebElement;
import com.xiaoshabao.webframework.ui.service.impl.element.def.SelectElementDef;
import com.xiaoshabao.webframework.ui.service.impl.element.dto.SelectElementDto;

/**
 * 下拉列表
 */
@Service("selectUIElement")
public class SelectUIElement extends AbstractUIElement implements WebElement{
	
	@Resource(name="tableColumnService")
	private TableColumnService tableColumnService;
	
	//render时的参数
	@Override
	protected void getElementParams(ElementColumnDto elementDto,
			Map<String, Object> data, Map<String, Object> elementParams) {
		if(!elementDto.getColumn().isRef()){
			logger.error("元素{}渲染错误，不是一个引用",elementDto.getElementId());
			throw new MsgErrorException("元素渲染错误");
		}
		
		String tableName=elementDto.getColumn().getRefTable();
		 List<TableColumnEntity> tableColumns=tableColumnService.getTableColumn(tableName);
		 String columnId=null;
		 String columnText=null;
		 for(TableColumnEntity column:tableColumns){
			 Integer fieldAttr=column.getFieldAttr();
			 if(fieldAttr!=null){
				 if(fieldAttr==FormConstants.FIELD_ATTR_KEY){
					 columnId=column.getFieldCode();
				 }
				 if(fieldAttr==FormConstants.FIELD_ATTR_VALUE){
					 columnText=column.getFieldCode();
				 }
			 }
		 }
		 
		 if(columnId==null||columnText==null){
			 logger.error("元素{}数据源{}配置错误，未配置下拉列表所需的列属性",
					 elementDto.getElementId(),tableName);
			 throw new MsgErrorException("数据源配置错误");
		 }
		 elementParams.put("ref_table_name", tableName);
		 elementParams.put("ref_table_id", columnId);
		 elementParams.put("ref_table_text", columnText);
		 
		 List<SelectElementDto> selectList=this.baseDao.getData(SelectElementDto.class, elementParams);
		 
		 elementParams.put("list", selectList);
	}

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
