package com.xiaoshabao.system.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.system.dao.SeqStringDao;
import com.xiaoshabao.system.entity.SeqStringEntity;

/**
 * 用来生成不重复的字符串
 */
public class SeqStringUtil {
	
	@Autowired
	SeqStringDao stringDao;
	
	static String words[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	/**
	 * 生成固定长度不重复字符串
	 * @param id 元素id
	 * @return
	 */
	public String getLegString(String[] words,Integer id){
		if(id==null||id<1){
			throw new ServiceException("未获得唯一字符串参数id");
		}
		//要返回的字符串
		StringBuffer reslut=new StringBuffer();
		SeqStringEntity entity=stringDao.getSeqString(id);
		entity.getElements();
		
		
		
		return reslut.toString();
	}

}
