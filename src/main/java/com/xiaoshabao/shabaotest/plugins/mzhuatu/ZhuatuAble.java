package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import java.util.List;

import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.ZhuatuService;

public interface ZhuatuAble {
	public void start(String url,List<ZhuatuService> zhuatuServices,String savePath);
	public void start(String url,List<ZhuatuService> zhuatuServices,String savePath,String charset);
	
	public void start(String url,List<ZhuatuService> zhuatuServices,ZhuatuConfig config);

}
