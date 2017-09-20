package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.util.List;

public interface MZhuatuService {
	
	public List<MTuInfo> parser(String html,MTuInfo parentInfo,List<String> projects,List<String> downloadURL);
	
	public String nextPage(String html);

}
