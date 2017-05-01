package com.xiaoshabao.shabaotest.module.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiaoshabao.shabaotest.bean.PersionBean;

public class FastJSONTest {
	/**
	 * 将指定内容，转换为json的字符串
	 */
	@Test
	public void toJSONString() {
		String text = null;

		// 将javaBean转化为json，javabean是有getter和setter的bean
		PersionBean obj = new PersionBean();
		text = JSON.toJSONString(obj);

		System.out.println("javabean输出：" + text);

		/*
		 * 如果想要将一个属性，指定为一个特定的名字
		 * 可以在指定的javabean的getter方法上添加，注解@JSONField(name="csage")
		 */

		// 转换的json使用''单引号方式输出
		text = JSON.toJSONString(obj, SerializerFeature.UseSingleQuotes);

		System.out.println("单引号输出：" + text);

		// 日期的格式化
		long millis = 1324138987429L;
		Date date = new Date(millis);
		text = JSON.toJSONString(date);

		System.out.println("日期：" + date);
		System.out.println("日期格式化输出：" + text);

		// 基于格式化输出的SerializerFeature
		text = JSON
				.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);

		System.out.println("日期格式化输出：" + text);

		// 自己指定时间格式
		text = JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS");

		System.out.println("自己指定日期格式输出：" + text);

		// 转换时写入类型信息
		text = JSON.toJSONString(obj, SerializerFeature.WriteClassName);

		System.out.println("转换时添加类型信息：" + text);

		// 创建一个兼容浏览器的字符串
		// 比如说在iphone上兼容emoji（绘文字）
		text = JSON.toJSONString(obj, SerializerFeature.BrowserCompatible);

		System.out.println("兼容浏览器的信息：" + text);

	}
	
	/**
	 * 当空的时候的转换
	 */
	@Test
	public void toStringBynull(){
		Map<String,Object> params=new HashMap<String,Object>();
		JSONObject json=(JSONObject) JSONObject.toJSON(params);
		String name=json.getString("name");
		System.out.println(name==null?"this is null":name);//当没有值时输出null
		
	}

	@Test
	public void toStringFormat() {
		Map<String,Object> params=new HashMap<String,Object>();
		
		String[] arrs=new String[]{"arr1","arr2","arr3"};
		params.put("arrs", arrs);//"arrs":["arr1","arr2","arr3"]
		
		List<String> arrList=new ArrayList<String>();
		arrList.add("list1");
		arrList.add("list2");
		arrList.add("list3");
		params.put("arrlist", arrList);//"arrlist":["list1","list2","list3"]
		
		JSONObject json=(JSONObject) JSONObject.toJSON(params);
		json.getJSONArray("");
		//转成map取数组
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(json);
		Object maparr=map.get("arrs");
		JSONArray mapjson=null;
		if (maparr instanceof JSONArray) {
			mapjson= (JSONArray) maparr;
        }
		mapjson.get(0);//此处能直接转换 回来
		
		
		
		
		System.out.println(json.toJSONString());
	}

	/**
	 * 将json串转换为obect等类型<br/>
	 * 实现反序列
	 */
	@Test
	public void toObject() {
		PersionBean p = new PersionBean();
		String text = JSON.toJSONString(p, SerializerFeature.WriteClassName);

		// 反序列化,必需有类的类型信息
		PersionBean n = (PersionBean) JSON.parse(text);

		System.out.println("反序列化输出：" + n.toString());

		// 反序列化,无需字符串中有类型信息。直接转换成指定bean
		PersionBean n2 = JSON.parseObject(text, PersionBean.class);

		System.out.println("反序列化输出：" + n2.toString());

		// 获得JSONObject的方法
		JSONObject b = JSON.parseObject(n2.toString());
		b.getString("name");
	}
}
