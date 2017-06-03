package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.BillEntity;
import com.xiaoshabao.webframework.ui.entity.ListEntity;
import com.xiaoshabao.webframework.ui.entity.ReportEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;

	
public class BillListDto{
	
	private BillEntity billEntity;
	
	private ListEntity list;
	
	private TemplateEntity template;
	
	private ReportEntity report;

	public ListEntity getList() {
		return list;
	}

	public void setList(ListEntity list) {
		this.list = list;
	}

	public TemplateEntity getTemplate() {
		return template;
	}

	public void setTemplate(TemplateEntity template) {
		this.template = template;
	}

	public ReportEntity getReport() {
		return report;
	}

	public void setReport(ReportEntity report) {
		this.report = report;
	}

	public BillEntity getBillEntity() {
		return billEntity;
	}

	public void setBillEntity(BillEntity billEntity) {
		this.billEntity = billEntity;
	}
	
}
