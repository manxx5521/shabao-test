package com.xiaoshabao.upgrade.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 压缩
 */
public class ZipUpgradeUtil {
	
	/** 
	   * 把zip文件解压到指定的文件夹 
	   * <p>根据官网自己理解</p>
	   * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip" 
	   * @param saveFileDir 解压后的文件存放路径, 如"D:/test/" () 
	   */
	  public static void unzip(String zipFilePath, String saveFileDir) {
	    File dir = new File(saveFileDir);
	    if (!dir.exists()) {
	      dir.mkdirs();
	    }
	    File file = new File(zipFilePath);
	    if (!file.exists()) {
	      throw new RuntimeException(String.format("要解压的文件 %s 不存在", zipFilePath));
	    }
	    InputStream is = null;
	    ArchiveInputStream input = null;
	    try {
	      is = new FileInputStream(file);
	      //根据工厂创建自定义输入流，工厂无法指定字符编码
	      input = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR,is);
	      ArchiveEntry archiveEntry = null;
	      while ((archiveEntry = input.getNextEntry()) != null) {
	        // 获取文件名  
	        String entryFileName = archiveEntry.getName();
	        // 构造解压出来的文件存放路径  
	        String entryFilePath = saveFileDir+entryFileName;
	        try {
	          // 把解压出来的文件写到指定路径  
	          File entryFile = new File(entryFilePath);
	          if (entryFileName.endsWith("/")) {
	            entryFile.mkdirs();
	          } else {
	        	  //输出文件流  
                  OutputStream out = FileUtils.openOutputStream(entryFile);  
                  IOUtils.copy(input, out);  
                  //关闭输出文件流  
                  out.close(); 
	          }
	        } catch (IOException e) {
	          throw new IOException(e);
	        } 
	      }
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    } finally {
	      try {
	        if (input != null) {
	          input.close();
	        }
	        if (is != null) {
	          is.close();
	        }
	      } catch (IOException e) {
	        throw new RuntimeException(e);
	      }
	    }
	  }

}
