package com.xiaoshabao.upgrade.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

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
	      input = new ArchiveStreamFactory().createArchiveInputStream(is);
	      ArchiveEntry archiveEntry = null;
	      while ((archiveEntry = input.getNextEntry()) != null) {
	        // 获取文件名  
	        String entryFileName = archiveEntry.getName();
	        // 构造解压出来的文件存放路径  
	        String entryFilePath = saveFileDir + entryFileName;
	        OutputStream os = null;
	        try {
	          // 把解压出来的文件写到指定路径  
	          File entryFile = new File(entryFilePath);
	          if (entryFileName.endsWith("/")) {
	            entryFile.mkdirs();
	          } else {
	            os = new BufferedOutputStream(new FileOutputStream(entryFile));
	            byte[] buffer = new byte[1024];
	            int len = -1;
	            while ((len = input.read(buffer)) != -1) {
	              os.write(buffer, 0, len);
	            }
	          }
	        } catch (IOException e) {
	          throw new IOException(e);
	        } finally {
	          if (os != null) {
	            os.flush();
	            os.close();
	          }
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
