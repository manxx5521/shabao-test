package com.xiaoshabao.baseframework.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	
	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws XjdbcException 
	 */
	public static Connection getConnection() throws XjdbcException {
		Connection conn = null;
		try {
			JDBCConstants constants=JDBCConstants.getInstance();
			
			 // 用来注册驱动
			Class.forName(constants.getDriver());
			conn = DriverManager.getConnection(constants.getUrl(),
					constants.getUsername(), constants.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new XjdbcException("未能连接数据库", e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new XjdbcException("获取连接错误", e);
		}
		return conn;
	}
	
	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws XjdbcException 
	 */
	public static Connection getConnection(String driver,String url,String username,String password) throws XjdbcException {
		Connection conn = null;
		try {
			 // 用来注册驱动
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new XjdbcException("未能连接数据库", e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new XjdbcException("获取连接错误", e);
		}
		return conn;
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
