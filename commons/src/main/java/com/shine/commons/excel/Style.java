package com.shine.commons.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author fuyongde
 * @desc Excel单元格样式
 * @date 2017/11/29 18:53
 */
public class Style {

  public static final short defaultTitleRowHeight = 20;
  public static final short defaultCellWidth = 16 * 256;

  public static CellStyle defaultTitleCellStyle(SXSSFWorkbook workbook) {
    // 创建单元格样式对象
    CellStyle cellStyle = workbook.createCellStyle();

    //设置字体
    cellStyle.setFont(defaultTitleFont(workbook));
    cellStyle.setAlignment(HorizontalAlignment.LEFT);
    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

    cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    cellStyle.setFillPattern(FillPatternType.FINE_DOTS);
    return cellStyle;
  }

  public static Font defaultTitleFont(SXSSFWorkbook workbook) {
    // 创建字体对象
    Font font = workbook.createFont();
    // 字体颜色
    font.setColor(Font.COLOR_NORMAL);
    // 将字体大小设置为10px
    short fontSize = 12;
    font.setFontHeightInPoints(fontSize);
    return font;
  }
}
