package com.shine.commons.utils;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * PaginationUtils Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>11/17/2017</pre>
 */
public class PaginationUtilsTest {

  private List<Integer> list;

  @Before
  public void before() throws Exception {
    list = Lists.newArrayList();
    for (int i = 1; i < 110; i++) {
      list.add(i);
    }
  }

  @After
  public void after() throws Exception {
    list = null;
  }

  /**
   * Method: getByPage(List<T> list, int pageIndex, int pageSize)
   */
  @Test
  public void testGetByPage() throws Exception {
    int pageIndex = 11;
    int pageSize = 10;
    List<Integer> currentPage = PaginationUtils.getByPage(list, pageIndex, pageSize);
    currentPage.forEach(System.out::println);

    boolean hasMore = PaginationUtils.hasMore(list.size(), 10, 10);
    assertFalse(hasMore);

    hasMore = PaginationUtils.hasMore(list.size(), 9, 10);
    assertTrue(hasMore);
  }


} 
