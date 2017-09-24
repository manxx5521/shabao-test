package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xiaoshabao.shabaotest.plugins.zhuatu.service.MZhuatuService;


/**
 * 抓图工具
 * <p>对URL去重</p>
 */
public class MZhuatuToHeavy extends BaseMZhuatu{
	
	
	private Map<Integer,List<String>> pageMap=new HashMap<Integer,List<String>>();

	@Override
	protected void parserPage(MTuInfo pageInfo,MZhuatuService zhuatuService, int idx,boolean newProject) {
		List<String> pages=pageMap.get(idx);
		String url=pageInfo.getUrl();
		if(pages==null){
			pages=new LinkedList<String>();
			pages.add(url);
			pageMap.put(idx, pages);
		}else if(!pages.contains(url)){
			pages.add(url);
		}else{
			logger.info("项目{}已经解析过了",pageInfo.getTitle()==null?"":pageInfo.getTitle());
			return;
		}
		super.parserPage(pageInfo, zhuatuService, idx, newProject);
	}
	
	

	//如果是下一层任务，把存储的临时信息清空，避免内存浪费
	@Override
	protected void parserPageNextIdx(MTuInfo pageInfo,
			MZhuatuService zhuatuService, int idx) {
		pageMap.put(idx, new LinkedList<String>());
		super.parserPageNextIdx(pageInfo, zhuatuService, idx);
	}


}
