package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.xiaoshabao.webframework.ui.service.FormTemplateService;
import com.xiaoshabao.webframework.util.ResourceManager;

//简单列表引擎
@Service("simpleListService")
public class SimpleListServiceImpl extends AbstractFormListServiceImpl
		implements FormListService {

	// 获得list内容
	@Override
	public BillListData getBillList(BillListDto billListDto,
			Map<String, Object> data) {
		BillListData result = new BillListData();
		// 获得模版内容
		String templateEngine = this.getTemplateEngineType(billListDto
				.getList().getListEngine());
		FormTemplateService templateService = ApplicationContextUtil.getBean(
				templateEngine, FormTemplateService.class);
		boolean isLoadWhere = false;
		if (billListDto.getList().isQuery()) {
			isLoadWhere = true;
		}
		TemplateData templateData = templateService.getTemplate(
				billListDto.getTemplate(), data, isLoadWhere);
		if (!templateData.isSuccess()) {
			throw new MsgErrorException(
					templateData.getMessage() == null ? "模版渲染错误"
							: templateData.getMessage());
		}

		Set<String> header = templateData.getHeader();
		result.setTemplateHtml(templateData.getContentHtml());

		// 获得report内容
		// 获得report引擎类型
		String reportEngine = this.getReportEngineType(billListDto.getList()
				.getListEngine());
		FormReportService reportService = ApplicationContextUtil.getBean(
				reportEngine, FormReportService.class);
		ReportData reportData = reportService.getReportData(
				billListDto.getReport(), data);

		header.addAll(reportData.getHeader());// 添加report需要的引用
		result.setReportHtml(reportData.getReportHtml());
		result.setReportScript(reportData.getReportScript());

		// 解析头部引用
		String[] headers = this.getHeaderHtml(header);
		result.setHeaderCSS(headers[0]);
		result.setHeaderJS(headers[1]);
		return result;
	}

	/**
	 * 获得头部引用
	 * 
	 * @param header
	 * @return [css,js]两类
	 */
	public String[] getHeaderHtml(Set<String> header) {
		StringBuilder css = new StringBuilder();
		StringBuilder js = new StringBuilder();
		if (header != null && header.size() > 0) {
			ResourceManager manager = ResourceManager.getInstance();
			for (String id : header) {
				css.append(manager.getCssTag(id));
				js.append(manager.getJSTag(id));
			}
		}
		return new String[] { css.toString(), js.toString() };
	}

	/*
	 * 查询列表
	 * <p>data中会带有{@link com.xiaoshabao.webframework.ui.dto.DataTablesParams}中的参数</p>
	 */
	@Override
	public AjaxResult queryList(String billId, ListEntity listEntity,
			Map<String, Object> data) {
		// 查询条件区
		String templateEngine = this.getTemplateEngineType(listEntity
				.getListEngine());
		FormTemplateService templateService = ApplicationContextUtil.getBean(
				templateEngine, FormTemplateService.class);
		String whereSql = templateService.getTemplateQuerySQL(
				listEntity.getTemplateId(), data);

		// 获得report引擎类型
		String reportEngine = this.getReportEngineType(listEntity
				.getListEngine());
		FormReportService reportService = ApplicationContextUtil.getBean(
				reportEngine, FormReportService.class);
		String[] reportSql = reportService.getReportQuerySql(
				listEntity.getReportId(), data);
		
		//计算总计记录数
		String countSql="SELECT COUNT(1) COUNT "+reportSql[1]+whereSql;
		Map<String,Object> countResult=this.baseDao.getSqlMapper().selectOne(countSql, data);
		Object count=countResult.get("COUNT");
		
		//查询值
		List<Map<String, Object>> list = null;
		if(count==null||(Integer)count>0){
			//没有值直接返回空记录
			list=new ArrayList<Map<String,Object>>();
			count=Integer.valueOf(0);
		}else{
			//正常查询值
			Integer start=(Integer) data.get("start");
			Integer length=(Integer) data.get("length");
			
			StringBuilder sql=new StringBuilder(" SELECT * FROM (SELECT ROW_.*, ROWNUM ROWNUM_ FROM (");
			
			sql.append(reportSql[0]);
			sql.append(reportSql[1]);
			sql.append(whereSql);
			
			sql.append(" ) ROW_ WHERE ROWNUM <=");
			sql.append(start*length);
			sql.append(" ) WHERE ROWNUM_ >");
			sql.append((start-1)*length);
			
			list = this.baseDao.getSqlMapper().selectList(sql.toString(),data);
		}
		
		//返回结果
		DataTablesResult result=new DataTablesResult();
		Integer draw=(Integer) data.get("draw");
		result.setDraw(draw);
		result.setRecordsTotal((Integer)count);
		result.setRecordsFiltered(list.size());
		result.setData(list);
		return result;
	}

}
