package com.xiaoshabao.webframework.ui.service.impl.button;

import org.springframework.stereotype.Component;

import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.service.impl.button.base.AbstractButtonFunction;

/**
 * 新增按钮
 */
@Component("buttonService_BUTTON_ADD")
public class ButtonAddFunction extends AbstractButtonFunction{

	@Override
	protected ButtonFunctionResult executeList(ButtonDto buttonDto) {
		ButtonFunctionResult result=new ButtonFunctionResult();
		result.setScript("window.location.href ='./add'");
		return result;
	}

	@Override
	protected ButtonFunctionResult executeView(ButtonDto buttonDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
