package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillListDto;
import com.xiaoshabao.webframework.ui.dto.DataTablesResult;
import com.xiaoshabao.webframework.ui.dto.ReportData;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.ListEntity;
import com.xiaoshabao.webframework.ui.service.FormListService;
import com.xiaoshabao.webframework.ui.service.FormReportService;
import com.xiaoshabao.webframework.ui.service.FormTableService;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;

//简单列表引擎
@Service("simpleListService")
public class SimpleListServiceImpl extends AbstractFormListServiceImpl
		implements FormListService {
	
	@Resource(name="formTableService")
	private FormTableService formTableService;
	
	private final static String REPORT_ENGINE="dataTableReportService";
	private final static String TEMPLATE_ENGINE="simpleTemplateService";

	// 获得list内容
	@Override
	public BillListData getBillList(BillListDto billListDto,
			Map<String, Object> data) {
		BillListData result = new BillListData();
		// 获得模版内容
		/*String templateEngine = this.getTemplateEngineType(billListDto
				.getList().getListEngine());*/
		String templateEngine=billListDto.getTemplate().getTemplateEngine();
		FormTemplateService templateService = ApplicationContextUtil.getBean(
				templateEngine, FormTemplateService.class);
		
		TemplateData templateData = templateService.getTemplate(
				billListDto.getTemplate(), data);
		if (!templateData.isSuccess()) {
			throw new MsgErrorException(
					templateData.getMessage() == null ? "模版渲染错误"
							: templateData.getMessage());
		}

		Set<String> header = templateData.getHeader();
		result.setTemplateHtml(templateData.getContentHtml());

		// 获得report内容
		FormReportService reportService = ApplicationContextUtil.getBean(
				REPORT_ENGINE, FormReportService.class);
		ReportData reportData = reportService.getReportData(billListDto.getList(),
				billListDto.getReport(), data);

		header.addAll(reportData.getHeader());// 添加report需要的引用
		result.setReportHtml(reportData.getReportHtml());
		result.setReportScript(reportData.getReportScript());

		// 解析头部引用
		String[] headers = this.getHeaderHtml(header);
		result.setHeaderCSS(headers[0]);
		result.setHeaderScript(headers[1]);
		result.setHeaderBeforeScript(headers[2]);
		result.setPagePath("simpleList");
		return result;
	}

	/*
	 * 查询列表
	 * <p>data中会带有{@link com.xiaoshabao.webframework.ui.dto.DataTablesParams}中的参数</p>
	 */
	@Override
	public AjaxResult queryList(String listId, ListEntity listEntity,
			Map<String, Object> data) {
		
		// 获得report引擎类型
		FormReportService reportService = ApplicationContextUtil.getBean(
				REPORT_ENGINE, FormReportService.class);
		String[] reportSql = reportService.getReportQuerySql(
				listEntity.getReportId(), data);
		
		// 查询条件区
		FormTemplateService templateService = ApplicationContextUtil.getBean(
				TEMPLATE_ENGINE, FormTemplateService.class);
		String whereSql = templateService.getTemplateQuerySQL(reportSql[2],
				listEntity.getTemplateId(), data);

		//计算总计记录数
		StringBuilder countSql=new StringBuilder("<script> SELECT COUNT(1) COUNT FROM ");
		countSql.append(reportSql[1]);
		countSql.append(" WHERE ");
		countSql.append(whereSql);
		countSql.append(" </script>");
		Map<String,Object> countResult=this.baseDao.getSqlMapper().selectOne(countSql.toString(), data);
		Object count=countResult.get("COUNT");
		
		//查询值
		List<Map<String, Object>> list = null;
		if(count==null||(Long)count<0){
			//没有值直接返回空记录
			list=new ArrayList<Map<String,Object>>();
			count=Integer.valueOf(0);
		}else{
			//正常查询值
			Integer start=Integer.valueOf(data.get("start").toString()) ;
			Integer length=Integer.valueOf(data.get("length").toString()) ;
			
			StringBuilder sql=new StringBuilder("<script> ");
			
			sql.append(reportSql[0]);
			sql.append("\n FROM ");
			sql.append(reportSql[1]);
			sql.append("\n WHERE ");
			sql.append(whereSql);
			
			sql.append("\n limit ");
			sql.append(start);
			sql.append(",");
			sql.append(length);
			sql.append(" </script>");
			
			list = this.baseDao.getSqlMapper().selectList(sql.toString(),data);
		}
		
		//返回结果
		DataTablesResult result=new DataTablesResult();
		result.setSuccess(true);
		Integer draw=Integer.valueOf(data.get("draw").toString());
		result.setDraw(draw);
		result.setRecordsTotal((Long)count);
		result.setRecordsFiltered(list.size());
		result.setData(list);
		return result;
	}

}
