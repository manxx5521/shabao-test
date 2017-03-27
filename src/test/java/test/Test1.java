package test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class Test1 {
	@SuppressWarnings("unused")
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings("unused")
	@Test
	public void test(){
		//ceshi
		Map<String,Object> params = JSON.parseObject("{'name':'wang','age':'12'}");
		ResultSetMetaData a = null;
		try {
			a.getColumnType(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
