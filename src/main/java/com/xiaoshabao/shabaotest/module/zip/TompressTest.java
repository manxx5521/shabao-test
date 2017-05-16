package com.xiaoshabao.shabaotest.module.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.Test;

/**
 * commons-compress
 * <p>解压文件测试</p>
 */
public class TompressTest {
  /**
   * compress的解压非常强大，可以创建CompressorOutputStream这种制定算法的输出流，
   * 也可以创建ZipArchiveInputStream这种具体实现的输入
   */
  @Test
  public void unzip() {
    String zipFilePath = "D:\\test\\测试解压包.zip";
    String saveFileDir = "D:\\test\\";

    //解压方法（网上的方法）
    unzip1(zipFilePath, saveFileDir);
  }
  /** 
   * 把zip文件解压到指定的文件夹 
   * <p>根据官网自己理解</p>
   * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip" 
   * @param saveFileDir 解压后的文件存放路径, 如"D:/test/" () 
   */
  public void unzip(String zipFilePath, String saveFileDir) {
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
  /** 
   * 把zip文件解压到指定的文件夹 
   * <p>此方法为网上的方法</p>
   * @param zipFilePath zip文件路径, 如 "D:/test/aa.zip" 
   * @param saveFileDir 解压后的文件存放路径, 如"D:/test/" () 
   */
  public void unzip1(String zipFilePath, String saveFileDir) {
    File dir = new File(saveFileDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File file = new File(zipFilePath);
    if (!file.exists()) {
      throw new RuntimeException(String.format("要解压的文件 %s 不存在", zipFilePath));
    }
    InputStream is = null;
    ZipArchiveInputStream zais = null;
    try {
      is = new FileInputStream(file);
      zais = new ZipArchiveInputStream(is);//可以指定格式，默认UTF-8
      ArchiveEntry archiveEntry = null;
      while ((archiveEntry = zais.getNextEntry()) != null) {
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
            while ((len = zais.read(buffer)) != -1) {
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
        if (zais != null) {
          zais.close();
        }
        if (is != null) {
          is.close();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /** 
   * zip压缩文件 
   * @param dir 
   * @param zippath 
   */
  public static void zip(String dir, String zippath) {
    List<String> paths = getFiles(dir);
    compressFilesZip(paths.toArray(new String[paths.size()]), zippath, dir);
  }

  /** 
   * 递归取到当前目录所有文件 
   * @param dir 
   * @return 
   */
  public static List<String> getFiles(String dir) {
    List<String> lstFiles = null;
    if (lstFiles == null) {
      lstFiles = new ArrayList<String>();
    }
    File file = new File(dir);
    File[] files = file.listFiles();
    for (File f : files) {
      if (f.isDirectory()) {
        lstFiles.add(f.getAbsolutePath());
        lstFiles.addAll(getFiles(f.getAbsolutePath()));
      } else {
        String str = f.getAbsolutePath();
        lstFiles.add(str);
      }
    }
    return lstFiles;
  }

  /** 
   * 文件名处理 
   * @param dir 
   * @param path 
   * @return 
   */
  public static String getFilePathName(String dir, String path) {
    String p = path.replace(dir + File.separator, "");
    p = p.replace("\\", "/");
    return p;
  }

  /** 
   * 把文件压缩成zip格式 
   * @param files         需要压缩的文件 
   * @param zipFilePath 压缩后的zip文件路径   ,如"D:/test/aa.zip"; 
   */
  public static void compressFilesZip(String[] files, String zipFilePath, String dir) {
    if (files == null || files.length <= 0) {
      return;
    }
    ZipArchiveOutputStream zaos = null;
    try {
      File zipFile = new File(zipFilePath);
      zaos = new ZipArchiveOutputStream(zipFile);
      zaos.setUseZip64(Zip64Mode.AsNeeded);
      //将每个文件用ZipArchiveEntry封装  
      //再用ZipArchiveOutputStream写到压缩文件中  
      for (String strfile : files) {
        File file = new File(strfile);
        if (file != null) {
          String name = getFilePathName(dir, strfile);
          ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, name);
          zaos.putArchiveEntry(zipArchiveEntry);
          if (file.isDirectory()) {
            continue;
          }
          InputStream is = null;
          try {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
              //把缓冲区的字节写入到ZipArchiveEntry  
              zaos.write(buffer, 0, len);
            }
            zaos.closeArchiveEntry();
          } catch (Exception e) {
            throw new RuntimeException(e);
          } finally {
            if (is != null)
              is.close();
          }

        }
      }
      zaos.finish();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (zaos != null) {
          zaos.close();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }

}
