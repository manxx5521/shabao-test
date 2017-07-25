package com.xiaoshabao.upgrade.util;

import org.apache.commons.io.FilenameUtils;


public class UpgradeConstants {
  
  /**
   * 文件存放的文件夹
   */
  public final static String DIR_PATH="upgrade";
  /**
   * 上传文件放到的文件夹名
   */
  public final static String FILE_PATH="temp";
  /**
   * 日志目录名
   */
  public final static String LOG_PATH="logs";
  /**
   * 执行成功后的文件备份目录名
   */
  public final static String BACKUP_PATH="backup";

  /** 服务器文件分割符号 **/
  public final static String separator="/";
  
  /**
   * 相对于解压目录，的项目目录
   */
  public final static String SPECIAL_CONFIG_PATH="/WEB-INF/classes/upgrade/file/config.properties";
    public final static String SQL_FILE_PATH = "shabao-test/WEB-INF/classes/upgrade/sql";


  
  public final static String XML_EXCLUDE_NAME="exclude";
  public final static String XML_OVERRIDE_NAME="override";
  
  public final static String XML_ATTR_SRC="src";
  public final static String XML_ATTR_TO="to";
  
  
  
  /**
   * 获得文件在服务端缓存目录
   * @param serverPath
   * @return
   */
  public static String getUpgradeDirPath(String serverPath){
    StringBuilder sb=new StringBuilder(serverPath);
    sb.append(separator);
    sb.append(DIR_PATH);
    return sb.toString();
  } 
  /**
   * 获得文件在服务端缓存目录
   * @param serverPath
   * @return
   */
  public static String getFilePath(String serverPath){
    StringBuilder sb=new StringBuilder(serverPath);
    getFilePath(sb);
    return sb.toString();
  }
  
  /**
   * 获得文件在服务端缓存目录
   * @param path
   * @return
   */
  public static void getFilePath(StringBuilder serverPath){
    serverPath.append(separator);
    serverPath.append(DIR_PATH);
    serverPath.append(separator);
    serverPath.append(FILE_PATH);
  }
  /**
   * 获得服务端日志目录
   * @param serverPath
   * @return
   */
  public static String getLogPath(String serverPath){
    StringBuilder sb=new StringBuilder(serverPath);
    sb.append(separator);
    sb.append(DIR_PATH);
    sb.append(separator);
    sb.append(LOG_PATH);
    return sb.toString();
  }
  /**
   * 获得服务端备份目录
   * @param serverPath
   * @return
   */
  public static String getBackupPath(String serverPath){
    StringBuilder sb=new StringBuilder(serverPath);
    sb.append(separator);
    sb.append(DIR_PATH);
    sb.append(separator);
    sb.append(BACKUP_PATH);
    return sb.toString();
  }
  /**
   * 获得文件在服务端缓存目录
   * @param serverPath
   * @param fileName 文件名（不能为空）
   * @return
   */
  public static String getFileTempPath(String serverPath,String fileName){
    StringBuilder sb=new StringBuilder(serverPath);
    getFilePath(sb);
    sb.append(separator);
    sb.append(FilenameUtils.getBaseName(fileName));
    return sb.toString();
  }

  /**
   * 获得特殊文件配置路径
   */
  public static String getSqlFilePath(String serverPath) {
    StringBuilder sb = new StringBuilder(serverPath);
    sb.append(separator);
    sb.append(SQL_FILE_PATH);
    return sb.toString();
  }

}
