package com.xiaoshabao.baseframework.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.component.SysConfig;


public class JDBCUtil {
	private static Logger logger = LoggerFactory.getLogger(JDBCUtil.class);

	private volatile static String driver = null;

	private static String url;
	private static String username;
	private static String password;

	private static void init() {
		if (driver == null) {
			synchronized (JDBCUtil.class) {
				if (driver == null) {
					SysConfig sysConfig = ApplicationContextUtil.getBean(
							"sysConfig", SysConfig.class);
					driver=sysConfig.getJdbcDriver();
					url=sysConfig.getJdbcUrl();
					username=sysConfig.getJdbcUsername();
					password=sysConfig.getJdbcPassword();
					// 用来注册驱动
					try {
						Class.forName(driver);
					} catch (ClassNotFoundException e) {
						logger.error("jdbc初始化启动错误", e);
						driver = null;
					}
				}
			}
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws XjdbcException
	 */
	public static Connection getConnection() {
		init();
		return getConnection(url, username, password);
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws XjdbcException
	 */
	public static Connection getConnection(String driver, String url,
			String username, String password) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.error("jdbc初始化启动错误", e);
		}
		return getConnection(url, username, password);
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws XjdbcException
	 */
	public static Connection getConnection(String url, String username,
			String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			logger.error("jdbc获取数据链接错误", e);
		}
		return conn;
	}
	/**
	 * 用来释放资源
	 * 
	 * @param conn
	 * @param st
	 */
	public static void close(Connection conn, Statement st){
		close(conn,st,null);
	}
	/**
	 * 用来释放资源
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		// 如果结果集不为空
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 置为控制，马上会被回收
				rs = null;
			}
		}

		// 如果运行环境Statement不为空
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 置为控制，马上会被回收
				st = null;
			}
		}

		// 如果连接不为空
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 置为控制，马上会被回收
				conn = null;
			}
		}
	}

}
