package com.xiaoshabao.baseframe.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLManager {
	private static Logger logger = LoggerFactory.getLogger(SQLManager.class);
	// 定义需要的变量
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public SQLManager() {

	}

	/**
	 * 传入值，进行数据库连接，每次读取配置
	 * @return
	 * @throws XjdbcException 
	 */
	public SQLManager(String driver,String url,String username,String password) throws XjdbcException {
		conn=JDBCUtil.getConnection(driver, url, username, password);
	}

	/**
	 * 执行一个update/delete/insert语句
	 * 
	 * @param sql
	 *            sql语句是带问号的格式
	 * @param parameters
	 *            {"...", "..."...}
	 * @throws XjdbcException
	 */
	public void executeUpdate(String sql, String[] parameters) throws Exception {

		try {
			this.getConnection();
			ps = conn.prepareStatement(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// 执行语句
			ps.executeUpdate();

			logger.debug("sql语句执行成功");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 可以执行多个update、delete、insert语句（考虑事务）<br/>
	 * 根据一个更新sql，插入多条记录
	 * 
	 * @param sql
	 *            传入一个sql
	 * @param parameters
	 *            传入多组参数String[][]
	 * @throws Exception
	 */
	public void executeUpdates(String sql, String[][] parameters)
			throws Exception {
		try {
			// 得到连接
			this.getConnection();
			// 多个sql语句，考虑事务
			conn.setAutoCommit(false);

			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sql);

					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}

					ps.executeUpdate();
				}

			}

			conn.commit();

			logger.debug("sql语句执行成功");
		} catch (SQLException e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new XjdbcException("执行sql时出错");
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 可以执行多个update、delete、insert语句（考虑事务）<br/>
	 * 两个参数的个数必须一一对应
	 * 
	 * @param sqls
	 *            这个需要传入多个sql[]
	 * @param parameters
	 *            传入多组参数String[][]
	 * @throws Exception
	 */
	public void executeUpdates(String[] sqls, String[][] parameters)
			throws Exception {
		try {
			// 得到连接
			this.getConnection();
			// 多个sql语句，考虑事务
			conn.setAutoCommit(false);

			for (int i = 0; i < sqls.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sqls[i]);

					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}

					ps.executeUpdate();
				}

			}

			conn.commit();

			logger.debug("sql语句执行成功");
		} catch (SQLException e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new XjdbcException("执行sql时出错");
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 统一的select语句<br/>
	 * 为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Object[]> executeQuery(String sql) throws Exception {
		ArrayList<Object[]> results = new ArrayList<Object[]>();

		try {
			this.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();

			while (rs.next()) {
				Object[] objects = new Object[column];

				for (int i = 0; i < column; i++) {
					objects[i] = rs.getObject(i+1);
				}

				results.add(objects);
			}
			logger.debug("sql语句执行成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new XjdbcException("执行sql时出错");
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
	public ArrayList<Object[]> executeQuery(String sql, String[] parameters)
			throws Exception {
		ArrayList<Object[]> results = new ArrayList<Object[]>();

		try {
			this.getConnection();
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
					objects[i] = rs.getObject(i+1);
				}

				results.add(objects);
			}
			logger.debug("sql语句执行成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new XjdbcException("执行sql时出错");
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
		return results;
	}
	
	private void getConnection() throws XjdbcException{
		if(conn==null){
			conn = JDBCUtil.getConnection();
		}
			
	}

}
