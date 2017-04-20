package com.xiaoshabao.baseframework.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class FileUtil {
  
  /**
   * 获得文件md5值
   */
  public static String getFileMD5(String path) throws IOException {
    FileInputStream fis = new FileInputStream(path);
    String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
    IOUtils.closeQuietly(fis);
    return md5;
  }

}
