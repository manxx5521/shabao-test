package com.xiaoshabao.webframework.ui.service.impl.button.base;

import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.entity.ButtonEnum;
import com.xiaoshabao.webframework.ui.service.button.ButtonFunction;

/**
 * 通用按钮，不区分视图操作的基类
 */
public abstract class ButtonCommonFunction implements ButtonFunction{

	@Override
	public abstract ButtonFunctionResult execute(ButtonDto buttonDto);

	
	@Override
	public final ButtonFunctionResult execute(ButtonDto buttonDto, ButtonEnum buttonEnum) {
		return execute(buttonDto);
	}

}
