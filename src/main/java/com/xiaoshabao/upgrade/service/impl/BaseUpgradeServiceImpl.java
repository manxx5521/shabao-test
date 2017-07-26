package com.xiaoshabao.upgrade.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.dao.impl.MybatisBaseDaoImpl;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.upgrade.entity.UpgradeEntity;
import com.xiaoshabao.upgrade.entity.UpgradeFileDetail;
import com.xiaoshabao.upgrade.service.UpgradeService;
import com.xiaoshabao.upgrade.util.UpgradeConstants;
import com.xiaoshabao.webframework.dto.AjaxResult;

public abstract class BaseUpgradeServiceImpl implements UpgradeService {
  protected Logger logger = LoggerFactory.getLogger(BaseUpgradeServiceImpl.class);

  @Resource(name = "mybatisBaseDao")
  protected MybatisBaseDaoImpl baseDao;

  private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  public AjaxResult upgradeApplication(Integer upgradeId) {
    UpgradeEntity upgradeEntity = getUpgradeEntity(upgradeId);
    return upgradeApplication(upgradeId, upgradeEntity);
  }

  protected AjaxResult upgradeApplication(Integer upgradeId, UpgradeEntity upgradeEntity) {
    existsDir(upgradeEntity);// 检查文件夹是否存在
    existsUpgradeFile(upgradeEntity);// 检查升级文件是否再temp目录
    deleteFileTmepDir(upgradeEntity);// 删除解压的错误文件
    unzip(upgradeEntity, UpgradeConstants.getFilePath(upgradeEntity.getServerPath()),
      UpgradeConstants.getFileTempPath(upgradeEntity.getServerPath(), upgradeEntity.getUpgradeFileName()));// 解压文件

    overrideFiles(upgradeEntity);

    exeSql(upgradeEntity);

    clearFileTmep(upgradeEntity);
    return new AjaxResult(true, "成功执行");
  }

  /**
   * 执行sql
   */
  protected void exeSql(UpgradeEntity upgradeEntity) {
    String sqlPath = this.getFilePathRoot(upgradeEntity,
      UpgradeConstants.SQL_FILE_PATH.replace("/", getFileRootSeparator()));
    File sqlFileRoot = new File(sqlPath);
    if (!sqlFileRoot.exists()) {
      throw new MsgErrorException("未找到存放sql文件的文件夹");
    }

    File[] files = sqlFileRoot.listFiles();

    Connection conn = getConnection();
    try {
      ScriptRunner runner = new ScriptRunner(conn);
      Resources.setCharset(Charset.forName("UTF-8")); //设置字符集,不然中文乱码插入错误

      String path = this.getFilePathRoot(upgradeEntity, UpgradeConstants.LOG_PATH, getLogNamePrefix());
      File infoLog = new File("log_" + path + ".txt");
      PrintWriter infoWriter = new PrintWriter(infoLog);
      runner.setLogWriter(infoWriter);

      File errorLog = new File("error_" + path + ".txt");
      PrintWriter errorWriter = new PrintWriter(errorLog);
      runner.setErrorLogWriter(errorWriter);

      exeSql(runner, errorWriter, files);

      runner.closeConnection();
      conn.close();
    } catch (Exception e) {
    }
  }

  protected void exeSql(ScriptRunner runner, PrintWriter log, File[] files) throws FileNotFoundException {
    for (File file : files) {
      if (file.isDirectory()) {
        //如果是文件夹，直接进去找文件
        exeSql(runner, log, file.listFiles());
      } else {
        //文件
        //        runner.runScript(Resources.getResourceAsReader("sql/CC20-01.sql"));
        runner.runScript(new FileReader(file));
      }
    }

  }

  /**
   * 获得日志名字前缀
   * @return
   */
  protected final String getLogNamePrefix() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      //		e.printStackTrace();
    }
    return new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSSS").format(new Date());
  }

  /**
   * 获得数据连接（多数源需重写）
   * @return
   */
  public Connection getConnection() {
    return this.baseDao.getSqlSession().getConnection();
  }

  /**
   * 覆盖文件
   */
  protected void overrideFiles(UpgradeEntity upgradeEntity) {
    try {
      File configFile = new File(getSpecialConfigPath(upgradeEntity, UpgradeConstants.SPECIAL_CONFIG_PATH));
      if (configFile.exists()) {
        String zipDir = UpgradeConstants.getFileTempPath(upgradeEntity.getServerPath(),
          upgradeEntity.getUpgradeFileName())
          + UpgradeConstants.separator + FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName());
        SAXReader reader = new SAXReader();
        InputStream is = new FileInputStream(configFile);
        Document document = reader.read(is);
        Element root = document.getRootElement();
        Iterator<?> it = root.elementIterator();
        while (it.hasNext()) {
          Element element = (Element) it.next();
          if (UpgradeConstants.XML_EXCLUDE_NAME.equals(element.getName())) {
            String src = element.attributeValue(UpgradeConstants.XML_ATTR_SRC);
            if (StringUtils.isNotEmpty(src)) {
              deleteServerFile(upgradeEntity, zipDir + src);
            }

          }

          if (UpgradeConstants.XML_OVERRIDE_NAME.equals(element.getName())) {
            String src = element.attributeValue(UpgradeConstants.XML_ATTR_SRC);
            if (StringUtils.isNotEmpty(src)) {
              String to = element.attributeValue(UpgradeConstants.XML_ATTR_TO);
              copyServerFileToFile(upgradeEntity, zipDir + src, zipDir + to);
            }
          }
        }
      }
    } catch (Exception e) {
      throw new MsgErrorException("解析特殊文件时出现异常", e);
    }
  }

  protected UpgradeEntity getUpgradeEntity(Integer upgradeId) {
    UpgradeEntity upgradeEntity = this.baseDao.getDataById(UpgradeEntity.class, upgradeId);
    if (upgradeEntity == null) {
      throw new MsgErrorException("未找到要升级的项目信息");
    }
    return upgradeEntity;
  }

  /**
   * 获得特殊文件配置
   * @param specialPath 特殊文件目录
   * @return
   */
  protected abstract String getSpecialConfigPath(UpgradeEntity upgradeEntity, String specialPath);

  /**
   * 获得文件路径根目录（--/--/upgrade）
   * @param specialPath 特殊文件目录
   * @return
   */
  protected abstract String getFilePathRoot(UpgradeEntity upgradeEntity);

  /**
   * 获得文件路径根目录（--/--/upgrade）
   * @param specialPath 特殊文件目录
   * @return
   */
  protected String getFilePathRoot(UpgradeEntity upgradeEntity, String... path) {
    StringBuilder sb = new StringBuilder(getFilePathRoot(upgradeEntity));
    for (String temp : path) {
      sb.append(getFileRootSeparator());
      sb.append(temp);
    }
    return sb.toString();
  }

  /**
   * 获得文件路径的分割符号（如果子类特殊可复写）
   * @return
   */
  protected String getFileRootSeparator() {
    return UpgradeConstants.separator;
  }

  /**
   * 获得服务端路径根目录（--/--/upgrade）
   * @param specialPath 特殊文件目录
   * @return
   */
  protected abstract String getServerPathRoot(UpgradeEntity upgradeEntity);

  /**
   * 获得服务端路径根目录（--/--/upgrade）
   * @param specialPath 特殊文件目录
   * @return
   */
  protected String getServerPathRoot(UpgradeEntity upgradeEntity, String... path) {
    StringBuilder sb = new StringBuilder(getServerPathRoot(upgradeEntity));
    for (String temp : path) {
      sb.append(UpgradeConstants.separator);
      sb.append(temp);
    }
    return sb.toString();
  }

  /**
   * 判断所需目录是否存在
   * @param upgradeEntity
   */
  protected abstract void existsDir(UpgradeEntity upgradeEntity);

  /**
   * 判断所需升级文件是否存在
   * @param upgradeEntity
   */
  protected abstract void existsUpgradeFile(UpgradeEntity upgradeEntity);

  /**
   * 把zip文件解压到指定的文件夹
   * 
   * @param zipFilePath
   *            zip文件路径, 如 "D:/test/aa.zip"
   * @param saveFileDir
   *            解压后的文件存放路径, 如"D:/test/" ()
   */
  protected abstract void unzip(UpgradeEntity upgradeEntity, String zipFilePath, String saveFileDir);

  /**
   * 删除文件临时目录
   * @param upgradeEntity
   */
  protected abstract void deleteFileTmepDir(UpgradeEntity upgradeEntity);

  /**
   * 清空文件文件
   * @param upgradeEntity
   */
  protected void clearFileTmep(UpgradeEntity upgradeEntity) {
    deleteFileTmepDir(upgradeEntity);//删除解压目录
  }

  /**
   * 删除文件临时
   * @param upgradeEntity
   */
  protected abstract void deleteFileTmep(UpgradeEntity upgradeEntity);

  /**
   * 删除服务端文件
   * @param upgradeEntity
   * @param zipDir 解压目录
   * @param path 目录相对于 解压目录
   */
  protected abstract void deleteServerFile(UpgradeEntity upgradeEntity, String path);

  protected abstract void copyServerFileToFile(UpgradeEntity upgradeEntity, String srcPath, String destPath);

  /**
   * 获得升级列表
   */
  @Override
  public AjaxResult getUpgradeList(Integer upgradeId) {
    UpgradeEntity upgradeEntity = getUpgradeEntity(upgradeId);
    return new AjaxResult(true, getUpgradeList(upgradeEntity));
  }

  protected List<UpgradeFileDetail> getUpgradeList(UpgradeEntity upgradeEntity) {
    String sqlPath = this.getFilePathRoot(upgradeEntity,
      UpgradeConstants.SQL_FILE_PATH.replace("/", getFileRootSeparator()));
    File sqlFileRoot = new File(sqlPath);
    List<UpgradeFileDetail> fileList = new ArrayList<UpgradeFileDetail>();
    if (!sqlFileRoot.exists()) {
      logger.error("未找到存放sql文件的文件夹!!!!!!!!!!");
      return fileList;
    }
    //获得升级日期
    try {
      Date upgradeDate = DATE_FORMAT.parse(upgradeEntity.getUpgardeDate());
      File[] files = sqlFileRoot.listFiles();
      getUpgradeList(fileList, files, upgradeDate);
    } catch (ParseException e) {
      throw new MsgErrorException("日期解析异常", e);
    }
    return fileList;
  }

  protected void getUpgradeList(List<UpgradeFileDetail> fileList, File[] files, Date upgradeDate) {
    UpgradeFileDetail fileDetail=new UpgradeFileDetail();
    for (File file : files) {
      if (file.isDirectory()) {
        //如果是文件夹，直接进去找文件
        getUpgradeList(fileList, file.listFiles(), upgradeDate);
      } else {
        String name=file.getName();
        String path=file.getAbsolutePath();
//        path.
//        fileDetail.setDate(date);
      }
    }

  }

  @Override
  public AjaxResult getLogList(Integer upgradeId) {
    // TODO Auto-generated method stub
    return null;
  }

}
