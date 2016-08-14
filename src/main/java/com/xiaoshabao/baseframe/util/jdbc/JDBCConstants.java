package com.xiaoshabao.baseframe.util.jdbc;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jdbc连接信息存放类<br>
 * 用来存放连接信息，实现单例
 */
public class JDBCConstants {
	private static Logger logger = LoggerFactory.getLogger(JDBCConstants.class);

	private static JDBCConstants instance = null;

	/** 驱动 */
	private static String driver = null;
	/** 连接url */
	private static String url = null;
	/** 数据库连接用户名 */
	private static String username = null;
	/** 连接密码 */
	private static String password = null;

	/**
	 * 私有构造方法，用来初始化变量
	 * @throws XjdbcException 
	 * 
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	private JDBCConstants() throws XjdbcException {
		logger.debug("读取配置文件jdbc.properties");
		init(); //读取配置文件方法
	}
	
	/**
	 * 唯一获得实例的方法
	 * 
	 * @throws Exception
	 */
	public static synchronized JDBCConstants getInstance() throws XjdbcException {
		if (instance == null) {
			logger.debug("获得单例JDBCConstants一次");
			instance = new JDBCConstants();
		}
		return instance;
	}
	
	/**
	 * 初始化读取配置文件方法
	 * @throws XjdbcException
	 */
	private void init() throws XjdbcException{
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("jdbc.properties");
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new XjdbcException("未正常读取jdbc.properties配置文件",e);
		} finally {
			try{
				in.close();
			}catch (Exception e){
				e.printStackTrace();
				throw new XjdbcException("读取jdbc.properties时，InputStream未正常关闭",e);
			}
		}
		
		driver=props.getProperty("jdbc.driver").trim();
		url=props.getProperty("jdbc.url").trim();
		username=props.getProperty("jdbc.username").trim();
		password=props.getProperty("jdbc.password").trim();
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
