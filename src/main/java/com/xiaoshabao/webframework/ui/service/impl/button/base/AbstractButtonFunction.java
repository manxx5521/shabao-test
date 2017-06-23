package com.xiaoshabao.webframework.ui.service.impl.button.base;

import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.enums.ViewTypeEnum;
import com.xiaoshabao.webframework.ui.service.button.ButtonFunction;

public abstract class AbstractButtonFunction implements ButtonFunction{

	@Override
	public final ButtonFunctionResult execute(ButtonDto buttonDto) {
		return execute(buttonDto,ViewTypeEnum.LIST);
	}

	@Override
	public final ButtonFunctionResult execute(ButtonDto buttonDto, ViewTypeEnum viewType) {
		ButtonFunctionResult result = null;
		switch (viewType) {
		case LIST:
			result = executeList(buttonDto);
			break;
		case VIEW:
			result = executeView(buttonDto);
			break;
		default:
			result = executeCustom(buttonDto);
			break;
		}
		return result;
	}
	
	/**
	 * 列表视图操作
	 * @param buttonDto
	 * @return
	 */
	protected abstract ButtonFunctionResult executeList(ButtonDto buttonDto);
	
	/**
	 * 页面视图操作
	 * @param buttonDto
	 * @return
	 */
	protected abstract ButtonFunctionResult executeView(ButtonDto buttonDto);
	
	/**
	 * 自定义操作方式
	 * @param buttonDto
	 * @return
	 */
	protected ButtonFunctionResult executeCustom(ButtonDto buttonDto){
		return null;
	}
}
