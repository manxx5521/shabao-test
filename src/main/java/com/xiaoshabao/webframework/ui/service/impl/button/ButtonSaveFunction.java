package com.xiaoshabao.webframework.ui.service.impl.button;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.ui.dto.ButtonDto;
import com.xiaoshabao.webframework.ui.dto.ButtonFunctionResult;
import com.xiaoshabao.webframework.ui.dto.FormFieldSet;
import com.xiaoshabao.webframework.ui.service.button.ButtonSql;
import com.xiaoshabao.webframework.ui.service.impl.button.base.AbstractButtonFunction;

/**
 * 新增按钮
 */
@Component("buttonService_BUTTON_ADD")
public class ButtonSaveFunction extends AbstractButtonFunction implements ButtonSql{
	
	@Resource(name="mybatisBaseDao")
	private BaseDao baseDao;

	@Override
	protected ButtonFunctionResult executeList(ButtonDto buttonDto) {
		ButtonFunctionResult result=new ButtonFunctionResult();
		return result;
	}

	@Override
	@Transactional
	protected ButtonFunctionResult executeView(ButtonDto buttonDto) {
		FormFieldSet fieldSet=buttonDto.getFieldSet();
		String sql=fieldSet.getInsertSql();
		int i=baseDao.getSqlMapper().insert(sql, fieldSet.getData());
		if(i<1){
			throw new MsgErrorException("数据新增失败，未能正常插入到数据库！");
		}
		String idColumn=fieldSet.getMainIdColumn();
		String id=fieldSet.getData().get(idColumn).toString();
		ButtonFunctionResult result=new ButtonFunctionResult();
		result.setMessage("添加成功");
		result.setScript("window.location.href ='./view/"+id+"/detail'");
		return result;
	}

}
