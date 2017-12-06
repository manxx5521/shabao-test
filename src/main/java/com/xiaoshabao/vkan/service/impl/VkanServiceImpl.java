package com.xiaoshabao.vkan.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.service.impl.AbstractServiceImpl;
import com.xiaoshabao.vkan.dto.VkanIndexDto;
import com.xiaoshabao.vkan.service.VkanService;
@Service("vkanServiceImpl")
public class VkanServiceImpl extends AbstractServiceImpl implements VkanService{

	@Override
	public VkanIndexDto getIndexData() {
		return null;
	}

}
