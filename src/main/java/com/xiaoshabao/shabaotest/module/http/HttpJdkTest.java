package com.xiaoshabao.shabaotest.module.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * JDK发HTTP请求
 */
public class HttpJdkTest {
	
	/**
	 * 发送HTTP请求
	 */
	@Test
	public void doGetHTTP(){
		String urlString="http://www.baidu.com";
		
		StringBuilder sb=new StringBuilder();
		HttpURLConnection httpURLConnection=null;//http链接
		try {
			URL url=new URL(urlString);//url创建
			httpURLConnection=(HttpURLConnection) url.openConnection();//打开链接
			httpURLConnection.setRequestMethod("GET");//设置请求方法。还可以设置其他参数
			httpURLConnection.connect();//正式链接请求
			InputStream in=httpURLConnection.getInputStream();//正确返回结果，获得输入流
			
			//读取内容
			InputStreamReader isr=new InputStreamReader(in);
			BufferedReader br=new BufferedReader(isr);//按行读取
			String temp=null;
			while((temp=br.readLine())!=null){//读取内容
				sb.append(temp);
			}
			br.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭链接
			if(httpURLConnection!=null){
				httpURLConnection.disconnect();
			}
		}
	}

}
