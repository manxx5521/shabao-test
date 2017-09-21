package com.xiaoshabao.shabaotest.plugins.zhuatu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MZhuatuHttpUtil {

  public static void download(String url, String pathName) throws ClientProtocolException, IOException {
    HttpGet httpGet = new HttpGet(url);// 创建get请求
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    //自定义超时时间等
    /* RequestConfig requestConfig = RequestConfig.custom()
    .setCookieSpec("easy").setSocketTimeout(5000) // socket超时
    .setConnectTimeout(5000) // connect超时
    .build();
    httpGet.setConfig(requestConfig);*/
    httpGet.setHeader("Accept", "text/html, */*; q=0.01");
    httpGet.setHeader("Accept-Encoding", "gzip, deflate,sdch");
    httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpGet.setHeader("Connection", "keep-alive");
    httpGet.setHeader("User-Agent",
      "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36)");

    try {
      httpClient = HttpClients.createDefault();

      response = httpClient.execute(httpGet);
      entity = response.getEntity();
      if (entity != null) {
        InputStream instream = entity.getContent();
        byte[] image = IOUtils.toByteArray(instream);
        FileUtils.writeByteArrayToFile(new File(pathName), image);
        IOUtils.closeQuietly(instream);
      }
      /*} catch (Exception e) {
        logger.error("文件下载失败:" + url, e);*/
    } finally {
      if (response != null) {
        response.close();
      }
      if (httpClient != null) {
        httpClient.close();
      }
    }
  }

  public static String doGet(String url) throws ClientProtocolException, IOException {
    return doGet(url, "UTF-8");
  }

  /**
   * get请求
   * @param url
   * @param charset
   * @throws ClientProtocolException  发送http get请求未能正常建立连接或者访问资源
   * @throws IOException 发送http get请求时资源未能正常关闭！！
   */
  public static String doGet(String url, String charset) throws ClientProtocolException, IOException {
    HttpGet httpGet = new HttpGet(url);
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    String responseContent = null;

    httpGet.setHeader("Accept", "text/html, */*; q=0.01");
    httpGet.setHeader("Accept-Encoding", "gzip, deflate,sdch");
    httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpGet.setHeader("Connection", "keep-alive");
    httpGet.setHeader("User-Agent",
      "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36)");

    try {
      // 创建默认的httpClient实例.
      httpClient = HttpClients.createDefault();
      // 执行请求
      response = httpClient.execute(httpGet);
      entity = response.getEntity();
      responseContent = EntityUtils.toString(entity, charset);// 获得响应内容
    } finally {
      if (response != null) {
        response.close();
      }
      if (httpClient != null) {
        httpClient.close();
      }
    }
    return responseContent;

  }

}
