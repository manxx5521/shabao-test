package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.component.TemplateEngine;
import com.xiaoshabao.webframework.ui.dto.DisplayColumnBean;
import com.xiaoshabao.webframework.ui.dto.ReportColumnDto;
import com.xiaoshabao.webframework.ui.dto.ReportData;
import com.xiaoshabao.webframework.ui.entity.ListEntity;
import com.xiaoshabao.webframework.ui.entity.ReportEntity;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.service.FormReportService;
import com.xiaoshabao.webframework.ui.service.FormTableService;
import com.xiaoshabao.webframework.ui.service.element.ReportElement;

@Service("dataTableReportService")
public class DataTableReportServiceImpl extends AbstractReportServiceImpl
		implements FormReportService {
	
	@Resource(name="formTableService")
	private FormTableService formTableService;
	/*
	 * 获得Report内容
	 */
	@Override
	public ReportData getReportData(ListEntity listEntity,ReportEntity report,
			Map<String, Object> data) {
		if(report==null){
			throw new ServiceException("未能正常获得report");
		}
		logger.info("开始处理report:{}"+report.getReportId());
		
		TableEntity table=formTableService.getTable(report.getTableId());
		List<ReportColumnDto> reportColumns=getReportColumns(report.getReportId());
		
		List<String> title=new ArrayList<String>();
		List<DisplayColumnBean> displayColumn=new ArrayList<DisplayColumnBean>();
		StringBuilder selectSql=new StringBuilder();
		StringBuilder formSql=new StringBuilder();
		selectSql.append("SELECT 1");
		formSql.append(table.getTableName());
		formSql.append(" ");
		String pkColumn=null;
		DisplayColumnBean columnBean=null;
		
		for(ReportColumnDto reportColumn: reportColumns){
			String elementServiceType = formEngineComponet
					.getElementSerivceType(reportColumn.getElement()
							.getElementType());

			ReportElement element = ApplicationContextUtil.getBean(
					elementServiceType, ReportElement.class);
			Map<String, Object> params=element.getElementParams(reportColumn.getElement().getParams(), reportColumn.getExtParams());
			String[] reprotSql=element.getReportSql(reportColumn, table.getTableName(), params);
			
			selectSql.append(",");
			selectSql.append(reprotSql[0]);
			
			formSql.append(reprotSql[2]);
			formSql.append(" ");
			
			if(reportColumn.isDisplay()){
				title.add(reportColumn.getTitle());//头部标题
				columnBean=new DisplayColumnBean();
				columnBean.setColumn(reprotSql[1]);
				columnBean.setHref(reportColumn.isHref());
				displayColumn.add(columnBean);
			}
			
			if(reportColumn.getTableColumn().isKey()){
				pkColumn=reportColumn.getTableColumn().getFieldCode();
			}
		}
		
		//返回结果
		ReportData result=new ReportData();
		//添加script引用
		result.getHeader().add("dataTables");
		
		data.put("titleList", title);
		data.put("pkColumn", pkColumn);
		data.put("columnList", displayColumn);
		data.put("dataTablesName", "dataTables-"+report.getReportId());
		data.put("dataTablesQueryListId", listEntity.getListId());
		String reportHtml=null;
		String reportScript=null;
		String renderTempalte=null;
		//获得html
		try {
			renderTempalte="/webframework/form/dataTablesSimple.ftl";
			reportHtml= TemplateEngine.renderTemplate(renderTempalte, data);
			
			renderTempalte="/webframework/form/dataTablesSimpleScript.ftl";
			reportScript= TemplateEngine.renderTemplate(renderTempalte, data);
		}catch (Exception e) {
			logger.error("解析report的html时，错误。解析的reportId为{},解析的模版为{}",report.getReportId(),renderTempalte,e);
			reportHtml="";
			reportScript="";
		}
		result.setReportHtml(reportHtml);
		result.setReportScript(reportScript);
		return result;
	}
	
	/**
	 * 获得所有report的列集合
	 * <p>本类会进行缓存,会返回一个不为 空，不为0的结果</p>
	 * @param reoportId
	 * @return
	 */
	protected List<ReportColumnDto> getReportColumns(String reportId){
		if(reportId==null){
			throw new ServiceException(" Report获取列集合时错误，传入的reportId为空");
		}
		List<ReportColumnDto> list=this.baseDao.getData(ReportColumnDto.class, reportId);
		if(list==null||list.size()<1){
			throw new ServiceException(reportId+" Report获取列时错误，未能取出任何数据");
		}
		return list;
	}

  @Override
  public String[] getReportQuerySql(String reportId, Map<String, Object> data) {
    logger.debug("start-→开始组装“数据区”查询那数据sql。数据reportId:{}",reportId);
    
    ReportEntity reportEntity=this.baseDao.getDataById(ReportEntity.class, reportId);
    String tableId=reportEntity.getTableId();
    TableEntity table=formTableService.getTable(tableId);
    List<ReportColumnDto> reportColumns=getReportColumns(tableId);
    
    StringBuilder selectSql=new StringBuilder();
    StringBuilder fromSql=new StringBuilder();
    selectSql.append("SELECT 1");
    fromSql.append(table.getTableName());
    fromSql.append(" ");
    
    for(ReportColumnDto reportColumn: reportColumns){
      String elementServiceType = formEngineComponet
          .getElementSerivceType(reportColumn.getElement()
              .getElementType());

      ReportElement element = ApplicationContextUtil.getBean(
          elementServiceType, ReportElement.class);
      
      Map<String, Object> params=element.getElementParams(reportColumn.getElement().getParams(), reportColumn.getExtParams());
      String[] reprotSql=element.getReportSql(reportColumn, table.getTableName(), params);
      
      selectSql.append(",");
      selectSql.append(reprotSql[0]);
      
      fromSql.append(reprotSql[2]);
      fromSql.append(" ");
    }
    
    logger.debug("end-→结束组装“数据区”查询那数据sql。数据reportId:{}",tableId);
    return new String[]{selectSql.toString(),fromSql.toString(),table.getTableName()};
  }

	

}
