package com.xiaoshabao.webframework.ui.service.button;

import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.enums.ButtonEnum;

/**
 * 按钮功能接口
 */
public interface ButtonFunction {
	
	/**
	 * 执行按钮
	 * <p>按钮不区分视图（适用于所有视图操作一样），默认执行LIST按钮。<br>
	 * 按钮实现{@link }
	 * </p>
	 * @param buttonDto
	 * @return
	 */
	public ButtonFunctionResult execute(ButtonDto buttonDto);
	
	
	/**
	 * 执行按钮，按位置执行
	 * @param buttonDto
	 * @param buttonEnum
	 * @return
	 */
	public ButtonFunctionResult execute(ButtonDto buttonDto,ButtonEnum buttonEnum);


}
