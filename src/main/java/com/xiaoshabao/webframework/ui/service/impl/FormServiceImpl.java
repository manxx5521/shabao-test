package com.xiaoshabao.webframework.ui.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dao.ElementDao;
import com.xiaoshabao.webframework.ui.dto.BillDto;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillListDto;
import com.xiaoshabao.webframework.ui.dto.BillViewData;
import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.dto.FormFieldSet;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.ListEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.enums.ButtonEnum;
import com.xiaoshabao.webframework.ui.service.FormListService;
import com.xiaoshabao.webframework.ui.service.FormService;
import com.xiaoshabao.webframework.ui.service.FormViewService;
import com.xiaoshabao.webframework.ui.service.TemplateFactory;
import com.xiaoshabao.webframework.ui.service.button.ButtonAddSql;
import com.xiaoshabao.webframework.ui.service.button.ButtonFunction;
import com.xiaoshabao.webframework.ui.service.button.ButtonSql;

/**
 * 表单服务
 */
@Service("formServiceImpl")
public class FormServiceImpl extends AbstractFormServiceImpl implements
		FormService {

	// 获得list界面数据
	@Override
	public BillListData getList(String billId, Map<String, Object> data) {
		
		BillDto billDto=getBillDtoById(billId);
		
		BillListDto billListDto = getBillListDto(billId,data);
		billListDto.setBillEntity(billDto);

		//选定处理列表界面的引擎
		FormListService formListService = ApplicationContextUtil.getBean(
				billDto.getBillEngineEntity().getListEngine(), FormListService.class);
		BillListData billListData = formListService.getBillList(billListDto,
				data);
		//设置单据统一属性
		billListData.setTitle(billDto.getBillName());
		
		//添加按钮
		List<ButtonDto> buttons=this.baseDao.getData("getListButtonDto", billListDto.getList());
		billListData.setButtons(buttons);
		return billListData;
	}

	/*
	 * 查询列表
	 */
	@Override
	public AjaxResult queryList(String listId, Map<String, Object> data) {
		
		ListEntity listEntity=this.baseDao.getDataById(ListEntity.class, listId);
		
		BillDto billDto=getBillDtoById(listEntity.getBillId());
		
		//选定处理列表界面的引擎
		FormListService formListService = ApplicationContextUtil.getBean(
				billDto.getBillEngineEntity().getListEngine(), FormListService.class);
		return formListService.queryList(listId, listEntity, data);
	}

	
	
	/*
	 * 列表界面按钮功能操作
	 */
	@Override
	public AjaxResult doButtonList(String buttonId, Map<String, Object> data) {
		AjaxResult result=new AjaxResult();
		ButtonDto buttonDto=this.baseDao.getDataById(ButtonDto.class, buttonId);
		
		if(buttonDto==null){
			logger.error("按钮{}无法在数据库中查询到。",buttonId);
			return result.setErrorInfo("按钮操作错误（不存在）");
		}
		
		ButtonFunction buttonFunction=ApplicationContextUtil.getBean("buttonService_"+buttonDto.getButtonValue(), ButtonFunction.class);
		
		
		if(buttonFunction instanceof ButtonAddSql){
			System.out.println();
		}
		Object exeResult=buttonFunction.execute(buttonDto, ButtonEnum.LIST);
		result.setData(exeResult);
		result.setSuccess(true);
		return result;
	}
	
	/*
	 * 获得view界面数据
	 */
	@Override
	public BillViewData getView(String billId, Map<String, Object> data) {
		
		BillDto billDto=getBillDtoById(billId);
		
		FormViewService formViewService=ApplicationContextUtil.getBean(billDto.getBillEngineEntity().getViewEngine(), FormViewService.class);
		
		return formViewService.getView(billDto, data);
	}
	
	/*
	 * 视图界面执行按钮
	 */
	@Override
	public AjaxResult doButtonView(String billId,String buttonId, Map<String, Object> data) {
		ButtonDto buttonDto=this.baseDao.getDataById(ButtonDto.class, buttonId);
		
		if(buttonDto==null){
			logger.error("按钮{}无法在数据库中查询到。",buttonId);
			return new AjaxResult("按钮操作错误（不存在）");
		}
		
		ButtonFunction buttonFunction=ApplicationContextUtil.getBean("buttonService_"+buttonDto.getButtonValue(), ButtonFunction.class);
		
		if(buttonFunction instanceof ButtonSql){
			BillDto billDto=getBillDtoById(billId);
			FormViewService formViewService=ApplicationContextUtil.getBean(billDto.getBillEngineEntity().getViewEngine(), FormViewService.class);
			
			FormFieldSet fieldSet=formViewService.getViewField(billDto,data);
			fieldSet.setData(data);
			buttonDto.setFieldSet(fieldSet);
		}
		
		ButtonFunctionResult exeResult=buttonFunction.execute(buttonDto, ButtonEnum.VIEW);
		return exeResult;
	}
	
	/**
	 * 根据billId获得列表界面信息
	 * <p>可能的话，可以进行缓存</p>
	 * @param billId
	 * @param data
	 * @return
	 */
	private BillListDto getBillListDto(String billId, Map<String, Object> data){
		List<BillListDto> billList = this.baseDao.getData(BillListDto.class,
				billId);
		if (billList == null || billList.size() != 1) {
			logger.info("单据获取失败，未根据billId获得对应单据或者获得的方案数不为1，失败单据id为{}", billId);
			throw new MsgErrorException("单据获取失败");
		}
		BillListDto billListDto = billList.get(0);
		return billListDto;
	}
	
	/**
	 * 根据id获得bill相关信息（缓存）
	 * <p>返回一个正确的结果。</p>
	 * @return
	 */
	private BillDto getBillDtoById(String billId){
		BillDto billDto=this.baseDao.getDataById(BillDto.class, billId);
		if (billDto == null) {
			logger.info("单据获取失败，未根据billId获得对应单据，失败单据id为{}", billId);
			throw new MsgErrorException("单据打开失败,未获得对应单据！");
		}
		if (billDto.getBillEngineEntity() == null) {
			logger.info("单据获取失败，失败单据id为{}。未能正确获得引擎类型，引擎类型获得为空", billId);
			throw new MsgErrorException("单据打开失败,单据引擎错误！");
		}
		return billDto;
	}
	
	
	
	
	
	
	
	
	
	
	// ----------------------------------------------

	@Autowired
	protected ElementDao elementDao;

	// 获得模版数据
	@Override
	public TemplateData getTemplateData(String templateId) {
		TemplateEntity templateEntity = this.elementDao
				.getTemplateByid(templateId);
		if (templateEntity == null) {
			logger.info("模版渲染失败，未根据模版id获得模版，失败模版id为{}", templateId);
			return null;
		}
		if (StringUtils.isEmpty(templateEntity.getEngineType())) {
			templateEntity.setEngineType(this.formEngineComponet
					.getDefaultEngineType());
		}
		String engineType = formEngineComponet.getEngineType(templateEntity
				.getEngineType());
		if (StringUtils.isEmpty(engineType)) {
			logger.info("模版渲染失败，未摸得渲染引擎类型，失败模版id为{}", templateId);
			return null;
		}
		TemplateFactory templateFactory = ApplicationContextUtil.getBean(
				engineType, TemplateFactory.class);
		TemplateData templateData = new TemplateData();
		Map<String, Object> params = new HashMap<String, Object>();
		this.formEngineComponet.putTemplateData(params, templateEntity);
		templateData.setHtml(templateFactory.getTemplateElements(templateId,
				params));
		return templateData;
	}

	// 相应元素web请求
	@Override
	public AjaxResult getElementResponse(String engineType, String elementId,
			Map<String, Object> params) {
		ElementEntity element = this.elementDao.getElementById(elementId);
		if (element == null) {
			logger.error("未找到元素id对应的元素，请查看元素id填写是否正确；元素id{}。", elementId);
			return new AjaxResult("元素类型错误");
		}

		engineType = formEngineComponet.getEngineType(engineType);
		if (StringUtils.isEmpty(engineType)) {
			logger.info("模版渲染失败，未摸得渲染引擎类型，失败模版id为{}", elementId);
			return null;
		}
		TemplateFactory templateFactory = ApplicationContextUtil.getBean(
				engineType, TemplateFactory.class);
		return templateFactory.getElementResponse(element, params);
	}



}
