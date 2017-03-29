package com.xiaoshabao.baseframework.component.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class SimpleExcelFactoryTest {
  
  public  static void main(String[] args) throws IOException{
    
//    String json="[{'title': '姓名', 'field':'name' },{'title': '性别','field':'sex' }]";
    String json="[{    'title': '姓名',    'field': 'name', 'colspan': '2', 'node': [{'title': '姓名','field': 'name' }, {'title': '性别',            'field': 'sex' }    ]},{    'title': '性别',    'field': 'sex','rowspan':'2'}]";
    List<ColumnEntity> titles = JSON.parseArray(json,ColumnEntity.class);
    List<Map<String, Object>> datas= new ArrayList<Map<String, Object>>();
    Map<String, Object> person1=new HashMap<String, Object>();
    person1.put("name", "张三");
    person1.put("sex", "男");
    Map<String, Object> person2=new HashMap<String, Object>();
    person2.put("name", "小花");
    person2.put("sex", "女");
    datas.add(person1);
    datas.add(person2);
    ExcelFactory excelFactory=new SimpleExcelFactory(titles,datas);
    excelFactory.exportExcelForPath("a112","D://");
  }

}
