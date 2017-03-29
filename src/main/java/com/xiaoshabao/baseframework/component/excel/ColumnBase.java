package com.xiaoshabao.baseframework.component.excel;

public class ColumnBase {
  /** 标题 */
  private String title;
  /** 字段 */
  private String field;
  /** 长度 */
  private int length=20;
  /** 类型 */
  private String type;
  /** 格式 **/
  private String format;
  /** 跨列 */
  private int colspan=1;
  /** 跨行 */
  private int rowspan=1;
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getField() {
    return field;
  }
  public void setField(String field) {
    this.field = field;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public int getLength() {
    return length;
  }
  public void setLength(int length) {
    this.length = length;
  }
  public int getColspan() {
    return colspan;
  }
  public void setColspan(int colspan) {
    this.colspan = colspan;
  }
  public int getRowspan() {
    return rowspan;
  }
  public void setRowspan(int rowspan) {
    this.rowspan = rowspan;
  }
  public String getFormat() {
    return format;
  }
  public void setFormat(String format) {
    this.format = format;
  }

}
