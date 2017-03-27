package com.xiaoshabao.baseframework.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLUtil {
	private static Logger logger = LoggerFactory.getLogger(SQLUtil.class);
	// 定义需要的变量
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 执行一个update/delete/insert语句
	 * 
	 * @param sql
	 *            sql语句是带问号的格式
	 * @param parameters
	 *            {"...", "..."...}
	 */
	public int executeUpdate(String sql, String[] parameters) throws Exception {
		int length = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// 执行语句
			length = ps.executeUpdate();
			logger.debug("sql语句执行成功:{}", sql);
		} catch (Exception e) {
			logger.error("sql执行失败{}", sql, e);
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps);
		}
		return length;
	}

	/**
	 * 可以执行多个update、delete、insert语句（考虑事务）<br/>
	 * 根据一个更新sql，插入多条记录
	 * 
	 * @param sql
	 *            传入一个sql
	 * @param parameters
	 *            传入多组参数String[][]
	 */
	public int executeUpdates(String sql, String[][] parameters) {
		Connection conn =null;
		PreparedStatement ps = null;
		int length=0;
		try {
			// 多个sql语句，考虑事务
			conn = getConnection();
			conn.setAutoCommit(false);

			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sql);

					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}

					ps.executeUpdate();
					length++;
				}

			}

			conn.commit();
			
			logger.debug("sql语句执行成功:{}", sql);
		} catch (SQLException e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			logger.error("sql执行失败{}", sql, e);
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps);
		}
		return length;
	}

	/**
	 * 可以执行多个update、delete、insert语句（考虑事务）<br/>
	 * 两个参数的个数必须一一对应
	 * 
	 * @param sqls
	 *            这个需要传入多个sql[]
	 * @param parameters
	 *            传入多组参数String[][]
	 */
	public int executeUpdates(String[] sqls, String[][] parameters) {
		Connection conn = null;
		PreparedStatement ps = null;
		int length=0;
		try {
			// 得到连接
			conn = getConnection();
			// 多个sql语句，考虑事务
			conn.setAutoCommit(false);

			for (int i = 0; i < sqls.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sqls[i]);

					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
					i++;
				}
			}
			conn.commit();
			logger.debug("sql语句批量执行成功");
		} catch (SQLException e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			logger.error("sql批量执行失败{}", e);
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps);
		}
		return length;
	}

	/**
	 * 统一的select语句<br/>
	 * 为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
	 * 
	 * @param sql
	 * @return
	 */
	public ArrayList<Object[]> executeQuery(String sql) {
		ArrayList<Object[]> results = new ArrayList<Object[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn=getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();

			while (rs.next()) {
				Object[] objects = new Object[column];

				for (int i = 0; i < column; i++) {
					objects[i] = rs.getObject(i + 1);
				}

				results.add(objects);
			}
			logger.debug("sql语句执行成功{}",sql);
		} catch (SQLException e) {
			logger.error("sql执行失败{}", sql, e);
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
		return results;
	}

	/**
	 * 统一的select语句<br/>
	 * 为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> executeQuery(String sql, String[] parameters) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Object[]> results = new ArrayList<Object[]>();

		try {
			conn=getConnection();
			ps = conn.prepareStatement(sql);

			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();

			while (rs.next()) {
				Object[] objects = new Object[column];

				for (int i = 0; i < column; i++) {
					objects[i] = rs.getObject(i + 1);
				}

				results.add(objects);
			}
			logger.debug("sql语句执行成功：{}",sql);
		} catch (SQLException e) {
			logger.error("sql执行失败{}", sql, e);
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
		return results;
	}

	private Connection getConnection() {
		return JDBCUtil.getConnection();
	}
	
	public static int toDataType(int i){
		Types a;
		return i;
	}

}
