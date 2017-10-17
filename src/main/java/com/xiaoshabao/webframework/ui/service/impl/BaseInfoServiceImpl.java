package com.xiaoshabao.webframework.ui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BaseInfoDto;
import com.xiaoshabao.webframework.ui.dto.BaseInfoListDto;
import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.enums.FieldAttrEnum;
import com.xiaoshabao.webframework.ui.service.BaseInfoService;

@Service("baseInfoServiceImpl")
public class BaseInfoServiceImpl extends AbstractFormServiceImpl implements BaseInfoService {

  @Override
  public BaseInfoListDto getInfoView(String tableId) {
    TableInfoDto tableInfo=this.getTableInfo(tableId);
    String sql=this.getSelectSql(tableInfo);
    List<BaseInfoDto> list=this.baseDao.getSqlMapper().selectList(sql,BaseInfoDto.class);
    BaseInfoListDto result=new BaseInfoListDto();
    result.setList(list);
    return result;
  }
  
  @Override
  public AjaxResult addBaseInfo(String tableId, BaseInfoDto baseInfo) {
    // TODO Auto-generated method stub
    return null;
  }
  
  protected String getSelectSql(TableInfoDto tableInfo){
    StringBuilder sql=new StringBuilder();
    sql.append("SELECT ");
    for(int i=0,size=tableInfo.getColumns().size();i<size;i++){
      TableColumnEntity columnEntity=tableInfo.getColumns().get(i);
      Integer fieldAttr=columnEntity.getFieldAttr();
      String fieldName=null;
      //配置字段类型
      if(fieldAttr!=null&&fieldAttr.intValue()!=0){
        if(FieldAttrEnum.BASE_CODE.getType()==fieldAttr.intValue()){
          fieldName=columnEntity.getFieldCode()+" baseCode";
        }else if(FieldAttrEnum.BASE_NAME.getType()==fieldAttr.intValue()){
          fieldName=columnEntity.getFieldCode()+" baseName";
        }else if(FieldAttrEnum.PARENT_CODE.getType()==fieldAttr.intValue()){
          fieldName=columnEntity.getFieldCode()+" parentCode";
        }else if(FieldAttrEnum.IS_USED.getType()==fieldAttr.intValue()){
          fieldName=columnEntity.getFieldCode()+" isUsed";
        }else if(FieldAttrEnum.ORDER_NO.getType()==fieldAttr.intValue()){
          fieldName=columnEntity.getFieldCode()+" orderNo";
        }
      }
      
      if(fieldName!=null&&i!=0){
        sql.append(",");
      }
      if(fieldName!=null){
        sql.append(fieldName);
      }
    }
    
    sql.append(" FROM ");
    sql.append(tableInfo.getTable().getTableName());
    return sql.toString();
  }
  
  
  protected TableInfoDto getTableInfo(String tableId){
    TableEntity table=this.baseDao.getDataById(TableEntity.class, tableId);
    if(table==null){
      throw new MsgErrorException("无法根据"+tableId+"获得对应的基础资料表");
    }
    List<TableColumnEntity> tableList=this.baseDao.getData(TableColumnEntity.class, tableId);
    if(tableList==null||tableList.size()<1){
      throw new MsgErrorException("无法根据"+tableId+"获得对应的详细列信息");
    }
    
    TableInfoDto tableInfo=new TableInfoDto();
    tableInfo.setTable(table);
    tableInfo.setColumns(tableList);
    return tableInfo;
  }

  
  
  

}

class TableInfoDto{
  private TableEntity table;
  
  private List<TableColumnEntity> columns;

  public TableEntity getTable() {
    return table;
  }

  public void setTable(TableEntity table) {
    this.table = table;
  }

  public List<TableColumnEntity> getColumns() {
    return columns;
  }

  public void setColumns(List<TableColumnEntity> columns) {
    this.columns = columns;
  }
  
}
