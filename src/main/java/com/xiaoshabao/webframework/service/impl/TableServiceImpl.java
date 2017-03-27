package com.xiaoshabao.webframework.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.util.jdbc.JDBCUtil;
import com.xiaoshabao.baseframework.util.jdbc.SQLUtil;
import com.xiaoshabao.webframework.entity.TableElement;
import com.xiaoshabao.webframework.service.TableService;

@Service("tableServiceImpl")
public class TableServiceImpl implements TableService {
	private static Logger logger = LoggerFactory
			.getLogger(TableServiceImpl.class);

	@Override
	public void getTableInfo() {
		String tableName = "td_ui_table";
		// 获取表结构
		getTableDesc(tableName);

	}

	public List<TableElement> getTableDesc(String tableName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TableElement> results = new ArrayList<TableElement>();
		String sql = "select * from " + tableName+ " where 1<>1";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			TableElement element = new TableElement();
			for (int i = 0; i < column; i++) {
				element.setFieldCode(rsmd.getColumnName(i));
				element.setFieldName(rsmd.getColumnLabel(i));
				element.setFieldType(SQLUtil.toDataType(rsmd.getColumnType(i)));
				element.setFieldLength(rsmd.getPrecision(i));
				element.setFieldDecimal(rsmd.getScale(i));
				element.setIsKey(0);
				element.setIsNull(rsmd.isNullable(i));
			}
			results.add(element);
			logger.debug("sql语句执行成功：{}", sql);
		} catch (SQLException e) {
			logger.error("sql执行失败{}", sql, e);
		} finally {
			// 关闭资源
			JDBCUtil.close(conn, ps, rs);
		}
		return results;
	}

}
