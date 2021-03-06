package com.shine.commons.utils;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * IdcardChecker Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>11/10/2017</pre>
 */
public class IdcardCheckerTest {

  private static final Logger logger = LoggerFactory.getLogger(IdcardCheckerTest.class);

  private List<String> idCardList = Lists.newArrayList();

  private List<String> notIDCardList = Lists.newArrayList();

  @Before
  public void before() throws Exception {
    idCardList.add("410184199006013372");
    idCardList.add("110101199901018960");
    idCardList.add("110101199901012083");
    idCardList.add("11010119990101832x");
    idCardList.add("110101199901015487");

    notIDCardList.add("410184199006013371");
    notIDCardList.add("110101199901018961");
    notIDCardList.add("110101199901012084");
    notIDCardList.add("110101199901018321");
    notIDCardList.add("110101199901015488");
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: isSecondIDCard(String idCard)
   */
  @Test
  public void testIsSecondIDCard() throws Exception {

    logger.info("-----{}-----", "正确的身份证");

    for (String idCard : idCardList) {
      boolean isIDCard = IdcardChecker.isIdcard(idCard);
      logger.info("{} is IDCard : {}", idCard, isIDCard);
      assertTrue(isIDCard);
    }

    logger.info("-----{}-----", "错误的身份证");

    for (String idCard : notIDCardList) {
      boolean isIDCard = IdcardChecker.isIdcard(idCard);
      logger.info("{} is IDCard : {}", idCard, isIDCard);
      assertFalse(isIDCard);
    }

    String idcard = "310108800902023";
    String idcard18 = IdcardChecker.convertIdcardBy15bit(idcard);
    assertEquals("310108198009020231", idcard18);
  }


} 
