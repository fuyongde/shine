package com.shine.leaf.server.service;

import com.shine.leaf.api.service.SequenceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SequenceServiceInner Tester.
 *
 * @author fuyongde
 * @since 05/26/2018
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceServiceImplTest {

  @Autowired private SequenceService sequenceService;

  @Before
  public void before() throws Exception {}

  @After
  public void after() throws Exception {}

  /** Method: nextLong() */
  @Test
  public void testNextLong() throws Exception {
    sequenceService.nextLong();
  }

  /** Method: nextString() */
  @Test
  public void testNextString() throws Exception {
    sequenceService.nextString();
  }

  /** Method: nextUUID() */
  @Test
  public void testNextUUID() throws Exception {
    sequenceService.nextUUID();
  }
}
