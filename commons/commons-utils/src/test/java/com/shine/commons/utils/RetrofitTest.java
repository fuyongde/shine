package com.shine.commons.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

import static org.junit.Assert.assertEquals;

/**
 * @author fuyongde
 * @desc Retrofit测试类
 * @date 2017/11/6 18:59
 */
public class RetrofitTest {

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {

  }

  @Test
  public void testGet() throws Exception {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BaiduService.URL_BAIDU)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build();
    BaiduService baiduService = retrofit.create(BaiduService.class);
    Response response = baiduService.baidu().execute();
    assertEquals(200, response.code());
  }

  @Test
  public void testPostJson() throws Exception {
  }

  @Test
  public void testPostForm() throws Exception {
  }

  @Test
  public void testBug() throws Exception {
  }

  public interface BaiduService {

    String URL_BAIDU = "http://www.baidu.com/";

    @GET("/")
    Call<String> baidu();
  }
}
