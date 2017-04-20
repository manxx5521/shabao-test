package com.xiaoshabao.baseframework.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class FileUtilTest {

  @Test
  public void test() {
    String md5;
    try {
      md5 = FileUtil.getFileMD5("D:\\基金办更新包0411.zip");
      System.out.println(md5);
    } catch (IOException e) {
      e.printStackTrace();
      fail("Not yet implemented");
    }
  }

}
