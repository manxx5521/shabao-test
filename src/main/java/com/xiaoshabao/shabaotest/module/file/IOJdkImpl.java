package com.xiaoshabao.shabaotest.module.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;

/**
 * JDK实现方式
 */
public class IOJdkImpl implements IOInterface{

  @Override
  public void toInputStrean() {
  }

  //理论上就是先生成文件
  @Override
  public void toInputStreanByPath(String path) {
    try {

      new FileInputStream(path);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void toInputStrean(File file) {
    try {

      new FileInputStream(file);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void toInputStreanByString(String content) {
     
    new ByteArrayInputStream(content.getBytes()); 
    
  }

}
