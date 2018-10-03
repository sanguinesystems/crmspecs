package com.primesoft.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class FooTest {

  @Test
  public void myFirst() {
    assertEquals(1, 1);
  }
}
