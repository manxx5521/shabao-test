package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.ButtonEntity;
import com.xiaoshabao.webframework.ui.entity.ButtonImageEntity;

public class ButtonDto extends ButtonEntity{
	
	/**
	 * 界面上显示的按钮名称
	 */
	private String displayName;
	
	private ButtonImageEntity buttonImage;

	public ButtonImageEntity getButtonImage() {
		return buttonImage;
	}

	public void setButtonImage(ButtonImageEntity buttonImage) {
		this.buttonImage = buttonImage;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
