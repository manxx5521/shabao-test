package com.xiaoshabao.webframework.ui.dto;

import com.xiaoshabao.webframework.ui.entity.ButtonEntity;
import com.xiaoshabao.webframework.ui.entity.ButtonImageEntity;

public class ButtonDto extends ButtonEntity{
	
	private ButtonImageEntity buttonImage;

	public ButtonImageEntity getButtonImage() {
		return buttonImage;
	}

	public void setButtonImage(ButtonImageEntity buttonImage) {
		this.buttonImage = buttonImage;
	}
	
}
