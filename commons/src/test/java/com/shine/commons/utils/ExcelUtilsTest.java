package com.shine.commons.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shine.commons.excel.Title;
import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ExcelUtils Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>11/29/2017</pre>
 */
public class ExcelUtilsTest {

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: createExcel(String directory, String fileName, List<String> sheetNames, List<Title[]> titles, List<List<Map<String, String>>> datas)
   */
  @Test
  public void testCreateExcel() throws Exception {
    String directory = SystemUtils.getUserDir().getPath();
    String fileName = "日报.xlsx";
    String[] sheetNames = new String[]{"投资", "提现"};

    File file = new File(directory, fileName);
    if (file.exists()) {
      file.delete();
    }

    List<Title[]> titles = Lists.newArrayList(
        new Title[]{
            new Title("name", "姓名"),
            new Title("mobile", "电话")
        },
        new Title[]{
            new Title("name", "姓名"),
            new Title("mobile", "电话")
        }
    );


    List<List<Map<String, String>>> allData = Lists.newArrayList();

    List<Map<String, String>> sheet1Data = Lists.newArrayList();

    Map<String, String> sheet1Row1Data = Maps.newHashMap();
    sheet1Row1Data.put("name", "傅永德");
    sheet1Row1Data.put("mobile", "18538182601");

    Map<String, String> sheet1Row2Data = Maps.newHashMap();
    sheet1Row2Data.put("name", "陈德炼");
    sheet1Row2Data.put("mobile", "110");

    List<Map<String, String>> sheet2Data = Lists.newArrayList();

    Map<String, String> sheet2Row1Data = Maps.newHashMap();
    sheet2Row1Data.put("name", "傅永德");
    sheet2Row1Data.put("mobile", "18538182601");

    Map<String, String> sheet2Row2Data = Maps.newHashMap();
    sheet2Row2Data.put("name", "陈德炼");
    sheet2Row2Data.put("mobile", "110");

    sheet1Data.add(sheet1Row1Data);
    sheet1Data.add(sheet1Row2Data);

    sheet2Data.add(sheet2Row1Data);
    sheet2Data.add(sheet2Row2Data);

    allData.add(sheet1Data);
    allData.add(sheet2Data);

    ExcelUtils.createExcel(directory, fileName, Arrays.asList(sheetNames), titles, allData);
  }


} 
