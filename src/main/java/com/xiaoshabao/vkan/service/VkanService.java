package com.xiaoshabao.vkan.service;

import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.vkan.dto.VkanIndexDto;

public interface VkanService extends AbstractService{
	
	
	public VkanIndexDto getIndexData();
	
}
