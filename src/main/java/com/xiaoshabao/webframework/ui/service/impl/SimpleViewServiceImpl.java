package com.xiaoshabao.webframework.ui.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.dto.BillDto;
import com.xiaoshabao.webframework.ui.dto.BillViewData;
import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.FormFieldSet;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.FormField;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.entity.ViewEntity;
import com.xiaoshabao.webframework.ui.enums.ViewPositionEnum;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;
import com.xiaoshabao.webframework.ui.service.FormViewService;

@Service("simpleViewService")
public class SimpleViewServiceImpl extends AbstractViewServiceImpl implements FormViewService{
	/*
	 *获得view界面数据
	 */
	@Override
	public BillViewData getView(BillDto billDto, Map<String, Object> data) {
		List<ViewEntity> viewList=this.baseDao.getData(ViewEntity.class, billDto.getBillId());
		
		BillViewData result=new BillViewData();
		if(viewList!=null&&!viewList.isEmpty()){
			for(ViewEntity view:viewList){
				Integer viewType=view.getViewType();
				if(FormConstants.ENGINE_TYPE_TEMPLATE==viewType){
					//表单类型
					TemplateEntity templateEntity=this.baseDao.getDataById(TemplateEntity.class, view.getViewExtId());
					FormTemplateService templateService = ApplicationContextUtil.getBean(
							templateEntity.getTemplateEngine(), FormTemplateService.class);
					
					TemplateData templateData = templateService.getTemplate(
							templateEntity, data);
					if (!templateData.isSuccess()) {
						throw new MsgErrorException(
								templateData.getMessage() == null ? "模版渲染错误"
										: templateData.getMessage());
					}
					
					Set<String> header = templateData.getHeader();
					result.setTemplateHtml(templateData.getContentHtml());

					// 解析头部引用
					String[] headers = this.getHeaderHtml(header);
					result.setHeaderCSS(headers[0]);
					result.setHeaderScript(headers[1]);
					result.setHeaderBeforeScript(headers[2]);
					result.setPagePath("simpleView");
					if(ViewPositionEnum.MAIN_VIEW.equals(view.getViewPosition())){
						//添加按钮
						List<ButtonDto> buttons=this.baseDao.getData("getViewButtonDto", view);
						result.setButtons(buttons);
					}
				}else if(FormConstants.ENGINE_TYPE_REPORT==viewType){
					//表类型
				}
			}
			
		}
		
		
		
		return result;
	}

	@Override
	public FormFieldSet getViewField(BillDto billDto,Map<String, Object> data) {
		List<ViewEntity> viewList=this.baseDao.getData(ViewEntity.class, billDto.getBillId());
		FormFieldSet fromBean=new FormFieldSet();
		if(viewList!=null&&!viewList.isEmpty()){
			for(ViewEntity view:viewList){
				Integer viewType=view.getViewType();
				if(FormConstants.ENGINE_TYPE_TEMPLATE==viewType){
					//表单类型
					TemplateEntity templateEntity=this.baseDao.getDataById(TemplateEntity.class, view.getViewExtId());
					FormTemplateService templateService = ApplicationContextUtil.getBean(
							templateEntity.getTemplateEngine(), FormTemplateService.class);
					TableEntity table=this.baseDao.getDataById(TableEntity.class, templateEntity.getTableId());
					List<FormField> list=templateService.getTemplateField(templateEntity.getTemplateId(), data);
					fromBean.setMainFields(list);
					fromBean.setMainTableName(table.getTableName());
				}else if(FormConstants.ENGINE_TYPE_REPORT==viewType){
					//表类型
				}
			}
			
		}
		return fromBean;
	}

}
