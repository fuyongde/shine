package com.shine.commons.utils;

import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * BeanMapper Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>10/31/2017</pre>
 */
public class OKHttpTest {

  public static final String URL_BAIDU = "http://www.baidu.com";

  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  private OkHttpClient okHttpClient;

  @Before
  public void before() throws Exception {
    okHttpClient = new OkHttpClient();
  }

  @After
  public void after() throws Exception {

  }

  @Test
  public void testGet() throws Exception {
    Request request = new Request.Builder().url(URL_BAIDU).build();
    Response response = okHttpClient.newCall(request).execute();
    assertEquals(200, response.code());
  }

  @Test
  public void testPostJson() throws Exception {
    String json = "{\"bank\":\"江西银行\",\"branch\":\"北京大望路支行\",\"openTime\":\"2017-10-26 12:00:00\"}";
    RequestBody jsonBody = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(URL_BAIDU)
        .post(jsonBody)
        .build();
    Response response = okHttpClient.newCall(request).execute();
    assertEquals(302, response.code());
  }

  @Test
  public void testPostForm() throws Exception {
    RequestBody formBody = new FormBody.Builder().add("name", "fuyongde").build();
    Request request = new Request.Builder()
        .url(URL_BAIDU)
        .post(formBody)
        .build();
    Response response = okHttpClient.newCall(request).execute();
    assertEquals(302, response.code());
  }

  @Test
  public void testBug() throws Exception {
    Request request = new Request.Builder().url("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/11.html").build();
    Response response = okHttpClient.newCall(request).execute();
    String result = new String(response.body().bytes(), "GB2312");
    System.out.println(result);
  }
}
