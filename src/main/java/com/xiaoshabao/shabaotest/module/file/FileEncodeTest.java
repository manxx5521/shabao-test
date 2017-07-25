package com.xiaoshabao.shabaotest.module.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
/**
 * 文件编码获得
 */
public class FileEncodeTest {
  
  private String filePath="E:\\test\\FileTest.java";
  
  /**
   * jdk获得文件编码(获得的编码错误的)
   */
  @Test
  public void getFileEncode(){
//    String filePath=""; //测试文件名
    String code = null;
    BufferedInputStream bis = null;
    int p = 0;
    try {
        FileInputStream inputStream = new FileInputStream(filePath);
        bis = new BufferedInputStream(inputStream);
        p = (bis.read() << 8) + bis.read();
    } catch (IOException e1) {
        e1.printStackTrace();
    } finally {
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    switch (p) {
        case 0xefbb :
            code = "UTF-8";
            break;
        case 0xfffe :
            code = "Unicode";
            break;
        case 0xfeff :
            code = "UTF-16BE";
            break;
        default :
            code = "GBK";
    }
    
    System.out.println("文件的编码为："+code);
  }
  
  /**
   * commons.io获得文件编码
   */
  @Test
  public void getFileEncodeByIO(){
//    String filePath="";
//    String fileEncode=EncodingDetect.getJavaEncode(filePath);
  }
}
