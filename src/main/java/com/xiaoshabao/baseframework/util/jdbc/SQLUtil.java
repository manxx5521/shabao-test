package com.xiaoshabao.baseframework.util.jdbc;


public class SQLUtil extends SQLManager{
	private static SQLUtil instance = null;
	// 定义需要的变量

	private SQLUtil() {

	}

	public synchronized static SQLUtil getInstance() throws XjdbcException {
		if (instance == null) {
			instance = new SQLUtil();
			JDBCConstants.getInstance();//实例化内容
		}
		return instance;

	}
}
