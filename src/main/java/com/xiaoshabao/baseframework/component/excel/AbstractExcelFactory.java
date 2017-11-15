package com.xiaoshabao.baseframework.component.excel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public abstract class AbstractExcelFactory implements ExcelFactory {

  /**输出流**/
  protected OutputStream out;

  protected HSSFCellStyle titleStyle;

  protected HSSFCellStyle dataStyle;

  protected WorkBookInfo bookInfo;

  /** 生成的文件名 */
  protected String fileName;

  private static DateFormat ddFormat = new SimpleDateFormat("yyyy-MM-dd");

  private static Pattern pattern = Pattern.compile("[0-9]*");

  /**
   * 创建sheet页
   * @param sheet
   * @param sheetInfo
   */
  protected void createSheet(HSSFSheet sheet, SheetInfo sheetInfo) {
    // 设置表格默认列宽度为15个字节
    sheet.setDefaultColumnWidth(15);
    int rownum = 0;
    List<ColumnBase> fields = new ArrayList<ColumnBase>();
    rownum = createSheetTitle(sheet, fields, rownum, sheetInfo.getColumns(), rownum, 0);//+1变成操作行
    rownum = createSheetData(sheet, fields, rownum, sheetInfo.getDataset());
  }

  /**
   * 创建单元格表头
   * @param sheet
   * @param fields 需要返回的字段
   * @param rownum 当前操作行
   * @param columns 列内容
   * @param maxrow 创建的最大行
   * @param maxcol 创建的最大列（便于合并单元格计算）
   * @return 最大行数
   */
  protected int createSheetTitle(HSSFSheet sheet, List<ColumnBase> fields, int rownum, List<ColumnEntity> columns,
    int maxrow, int maxcol) {
    for (int i = 0; i < columns.size(); i++) {
      ColumnEntity column = columns.get(i);
      //扩展行
      int rowspan = column.getRowspan();
      int colspan = column.getColspan();
      List<ColumnEntity> nodes = column.getNode();
      // 产生需要的行
      if (maxrow <= rownum + rowspan) {
        for (int j = 0; j < rownum + rowspan - maxrow; j++) {
          sheet.createRow(rownum);
          maxrow++;
        }
      }
      //产生单元格
      HSSFRow row = sheet.getRow(rownum);
      //合并单元格
      if (rowspan > 1 || colspan > 1) {
        this.addMergedRegion(sheet, rownum, maxcol, rownum+rowspan - 1, maxcol+colspan - 1);//范围
      }
      HSSFCell cell = row.createCell(maxcol);
      cell.setCellStyle(titleStyle);
      HSSFRichTextString text = new HSSFRichTextString(column.getTitle());
      cell.setCellValue(text);

      //递归查看是否有下级
      if (nodes != null && !nodes.isEmpty()) {
        maxrow = createSheetTitle(sheet, fields, rownum+rowspan, column.getNode(), maxrow, maxcol);
      } else {
        //插入字段
        String field = column.getField();
        if (field != null && !field.equals("")) {
          fields.add(column);
        }
      }
      maxcol += colspan;
    }
    return maxrow;
  }

  /**
   * 合并单元格（参数是范围）
   * @param sheet
   * @param startRow
   * @param startCol
   * @param expandRows
   * @param expandCols
   */
  protected void addMergedRegion(HSSFSheet sheet, int startRow, int startCol, int expandRows, int expandCols) {
    CellRangeAddress cra = new CellRangeAddress(startRow, expandRows, startCol, expandCols);
    //在sheet里增加合并单元格  
    sheet.addMergedRegion(cra);
    //sheet.getRow(startRow).getCell(startCol).setCellStyle(titleStyle);
  }

  /**
   * 创建数据
   * @param sheet
   * @param fields
   * @param rownum
   * @param dataset
   * @return
   */
  @SuppressWarnings("unchecked")
  protected int createSheetData(HSSFSheet sheet, List<ColumnBase> fields, int rownum, List<?> dataset) {
    if (fields.size() < 1)
      return rownum;
    for (int i = 0; i < dataset.size(); i++) {
      //      BeanMap map=new BeanMap(list.get(0));
      Map<String, Object> map = null;
      if (dataset.get(i) instanceof Map) {
        map = (Map<String, Object>) dataset.get(i);
      } else {
        try {
          map = PropertyUtils.describe(dataset.get(i));
        } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException("单元格属性设置异常异常");
        }
      }
      HSSFRow row = sheet.createRow(rownum);
      for (int j = 0; j < fields.size(); j++) {
        ColumnBase column = fields.get(j);
        HSSFCell cell = row.createCell(j);
        cell.setCellStyle(dataStyle);
        Object obj = map.get(column.getField());
        HSSFRichTextString text = new HSSFRichTextString(parsingObject(obj, column));
        cell.setCellValue(text);
      }
      rownum++;
    }
    return rownum;
  }

  /**
   * 解析字符串(对类型进行可能的转换)
   * @param obj 数值对象
   * @return
   */
  protected String parsingObject(Object obj, ColumnBase column) {
    String type = column.getType();
    String rs = null;
    try {
      if (type == null || type.equals("")) {
        //自动判断类型
        if (obj instanceof String) {
          rs = obj.toString();
        } else if (obj instanceof Date) {
          rs = ddFormat.format(obj);
        } else {
          rs = obj.toString();
        }
      } else {
        //根据输入类型输出格式(比对小写即可，上边已经转换)
        type = type.toLowerCase();
        switch (type) {
        case "string":
          rs = obj.toString();
          break;
        case "number":
          rs = obj.toString();
          if (!pattern.matcher(rs).matches()) {
            rs = "";
          }
          break;
        case "date":
          String format = column.getFormat();
          if (format == null || format.equals("")) {
            rs = ddFormat.format(obj);
          } else {
            rs=new SimpleDateFormat(format).format(obj);
          }
          break;
        default:
          rs = obj.toString();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      rs = "";
    }

    return rs;
  }

  /**
   * 目前入口
   * @param params
   * @param out
   * @throws IOException 
   */
  protected void createWorkBook(OutputStream out) throws IOException {
    // 声明一个工作薄
    HSSFWorkbook workbook = new HSSFWorkbook();
    setStyle(workbook);

    List<SheetInfo> sheetList = bookInfo.getSheetList();
    for (SheetInfo sheetInfo : sheetList) {
      // 生成一个sheet
      HSSFSheet sheet = null;
      if (sheetInfo.getSheetName() != null) {
        sheet = workbook.createSheet(sheetInfo.getSheetName());
      } else {
        sheet = workbook.createSheet();
      }
      createSheet(sheet, sheetInfo);
    }
    workbook.write(out);
  }

  /**
   * 复写这个传入固定信息
   */
  protected abstract void exportExcel();

  /**
   * 导出到文件
   */
  @Override
  public boolean exportExcelForPath(String path) throws IOException {
    if (fileName == null) {
      fileName = String.valueOf(System.currentTimeMillis() + new Random(9).nextInt() % 10000);
    }
    return false;
  }

  /**
   * 导出到文件
   */
  @Override
  public final boolean exportExcelForPath(String fileName, String path) throws IOException {
    try {
      this.exportExcel();
      if (out == null) {
        this.out = new FileOutputStream(path + fileName + ".xls");
      }
      this.createWorkBook(out);
    } catch (FileNotFoundException e) {
      throw new IOException("创建导出文件失败！！！", e);
    }
    return true;
  }

  /**
   * 导出到输出流
   */
  @Override
  public final OutputStream exportExcelForOutputStream() throws IOException {
    if (out == null) {
      this.out = new ByteArrayOutputStream();
    }
    this.exportExcel();
    this.createWorkBook(out);
    return out;
  }

  /**
   * 创建样式信息
   * @param workbook
   */
  protected void setStyle(HSSFWorkbook workbook) {
    // 生成一个样式 title样式
    HSSFCellStyle titleStyle = workbook.createCellStyle();
    titleStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    titleStyle.setBorderBottom(BorderStyle.THIN);
    titleStyle.setBorderLeft(BorderStyle.THIN);
    titleStyle.setBorderRight(BorderStyle.THIN);
    titleStyle.setBorderTop(BorderStyle.THIN);
    titleStyle.setAlignment(HorizontalAlignment.CENTER);
    // 生成一个字体
    HSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.VIOLET.index);
    font.setFontHeightInPoints((short) 12);
    font.setBold(true);
    // 把字体应用到当前的样式
    titleStyle.setFont(font);
    this.titleStyle = titleStyle;

    // 生成数据样式
    HSSFCellStyle dataStyle = workbook.createCellStyle();
    dataStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    dataStyle.setBorderBottom(BorderStyle.THIN);
    dataStyle.setBorderLeft(BorderStyle.THIN);
    dataStyle.setBorderRight(BorderStyle.THIN);
    dataStyle.setBorderTop(BorderStyle.THIN);
    dataStyle.setAlignment(HorizontalAlignment.CENTER);
    dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    // 生成另一个字体
    HSSFFont font2 = workbook.createFont();
    font2.setBold(false);
    // 把字体应用到当前的样式
    dataStyle.setFont(font2);
    this.dataStyle = dataStyle;

  }

  /**
   * 设置输出流
   * @param out
   */
  public void setOut(OutputStream out) {
    this.out = out;
  }

}
