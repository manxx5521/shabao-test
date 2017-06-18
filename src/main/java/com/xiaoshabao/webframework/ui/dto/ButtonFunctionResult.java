package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.dto.AjaxResult;

public class ButtonFunctionResult extends AjaxResult{
	
	/**
	 * 脚本
	 */
	private String script;
	
	public ButtonFunctionResult(){
		this.success=true;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	
}
