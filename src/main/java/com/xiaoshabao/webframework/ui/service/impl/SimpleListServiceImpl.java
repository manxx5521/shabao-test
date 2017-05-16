package com.xiaoshabao.webframework.ui.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillListDto;
import com.xiaoshabao.webframework.ui.dto.ReportData;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.ListEntity;
import com.xiaoshabao.webframework.ui.service.FormListService;
import com.xiaoshabao.webframework.ui.service.FormReportService;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;
import com.xiaoshabao.webframework.util.ResourceManager;

//简单列表引擎
@Service("simpleListService")
public class SimpleListServiceImpl extends AbstractFormListServiceImpl
		implements FormListService {

	// 获得list内容
	@Override
	public BillListData getBillList(BillListDto billListDto,Map<String, Object> data) {
		BillListData result=new BillListData();
		//获得模版内容
		String templateEngine=this.getTemplateEngineType(billListDto.getList().getListEngine());
		FormTemplateService templateService=ApplicationContextUtil.getBean(templateEngine, FormTemplateService.class);
		boolean isLoadWhere=false;
		if(billListDto.getList().isQuery()){
		  isLoadWhere=true;
		}
		TemplateData templateData=templateService.getTemplate(billListDto.getTemplate(),data,isLoadWhere);
		if(!templateData.isSuccess()){
			throw new MsgErrorException(templateData.getMessage()==null?"模版渲染错误":templateData.getMessage());
		}
		
		Set<String> header=templateData.getHeader();
		result.setTemplateHtml(templateData.getContentHtml());
		
		//获得report内容
		//获得report引擎类型
		String reportEngine=this.getReportEngineType(billListDto.getList().getListEngine());
		FormReportService reportService=ApplicationContextUtil.getBean(reportEngine, FormReportService.class);
		ReportData reportData=reportService.getReportData(billListDto.getReport(), data);
		
		header.addAll(reportData.getHeader());//添加report需要的引用
		result.setReportHtml(reportData.getReportHtml());
		result.setReportScript(reportData.getReportScript());

		//解析头部引用
		String[] headers=this.getHeaderHtml(header);
		result.setHeaderCSS(headers[0]);
		result.setHeaderJS(headers[1]);
		return result;
	}
	/**
	 * 获得头部引用
	 * @param header
	 * @return [css,js]两类
	 */
	public String[] getHeaderHtml(Set<String> header){
		StringBuilder css=new StringBuilder();
		StringBuilder js=new StringBuilder();
		if(header!=null&&header.size()>0){
			ResourceManager manager=ResourceManager.getInstance();
			for(String id:header){
				css.append(manager.getCssTag(id));
				js.append(manager.getJSTag(id));
			}
		}
		return new String[]{css.toString(),js.toString()};
	}
	
	
	/*
	 * 查询列表
	 */
	@Override
	public AjaxResult queryList(String billId,ListEntity listEntity, Map<String, Object> data) {
	  //查询条件区
    String templateEngine=this.getTemplateEngineType(listEntity.getListEngine());
    FormTemplateService templateService=ApplicationContextUtil.getBean(templateEngine, FormTemplateService.class);
    String whereSql=templateService.getTemplateQuerySQL(listEntity.getTemplateId(), data);
    
    //获得report引擎类型
    String reportEngine=this.getReportEngineType(listEntity.getListEngine());
    FormReportService reportService=ApplicationContextUtil.getBean(reportEngine, FormReportService.class);
    String selectSql=reportService.getReportQuerySql(listEntity.getReportId(), data);
    
    List<Map<String, Object>> list=this.baseDao.getSqlMapper().selectList(selectSql+whereSql);
		
		return null;
	}

}
