package com.xiaoshabao.webframework.ui.service.impl.button;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.service.button.ButtonSql;
import com.xiaoshabao.webframework.ui.service.impl.button.base.AbstractButtonFunction;

/**
 * 新增按钮
 */
@Component("buttonService_BUTTON_UPDATE")
public class ButtonUpdateFunction extends AbstractButtonFunction implements
		ButtonSql {

	@Resource(name = "mybatisBaseDao")
	private BaseDao baseDao;

	@Override
	protected ButtonFunctionResult executeList(ButtonDto buttonDto) {
		ButtonFunctionResult result = new ButtonFunctionResult();
		return result;
	}

	@Override
	protected ButtonFunctionResult executeView(ButtonDto buttonDto) {
		ButtonFunctionResult result = new ButtonFunctionResult();
		result.setScript("window.location.href ='./update'");
		return result;
	}

}
