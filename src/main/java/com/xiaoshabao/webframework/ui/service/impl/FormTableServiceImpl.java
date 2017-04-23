package com.xiaoshabao.webframework.ui.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.baseframework.util.jdbc.JDBCUtil;
import com.xiaoshabao.baseframework.util.jdbc.SQLUtil;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dao.TableDao;
import com.xiaoshabao.webframework.ui.dto.TableDto;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.service.FormTableService;
/**
 * 表单服务
 */
@Service("formTableService")
public class FormTableServiceImpl extends AbstractTemplateServiceImpl3 implements FormTableService {
  
  private TableDao tableDao; 

  //获得表单详情
  @Override
  public AjaxResult getTableInfo(String tableId) {
   
    return null;
  }
  
  //添加数据源
  @Override
  @Transactional
  public AjaxResult addTable(TableDto table) {
    int i=0;
    i=tableDao.addTableDesc(table);
    if(i<1){
      throw new MsgErrorException("添加表信息失败！");
    }
    List<TableColumnEntity> elements=getTableDesc(table.getTableId());
    int size=elements.size();
    if(size>0){
      i=tableDao.addTableElements(elements);
      if(i!=size){
        throw new MsgErrorException("添加数据表元素失败！");
      }
    }
    
    
    return new AjaxResult(true,"数据源添加成功");
  }
  
  /**
   * 获得数据表详情
   * @param tableName
   * @return
   */
  private List<TableColumnEntity> getTableDesc(String tableName) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ArrayList<TableColumnEntity> results = new ArrayList<TableColumnEntity>();
    String sql = "select * from " + tableName+ " where 1<>1";
    try {
      conn = JDBCUtil.getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      ResultSetMetaData rsmd = rs.getMetaData();
      int column = rsmd.getColumnCount();
      TableColumnEntity element = new TableColumnEntity();
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
      logger.error("sql执行失败{}", sql, e);//不做处理后期判断
    } finally {
      // 关闭资源
      JDBCUtil.close(conn, ps, rs);
    }
    return results;
  }

  
}
