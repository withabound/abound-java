package com.withabound;

import com.withabound.util.TestUtils;

public abstract class AbstractAboundTest {
  private Abound abound;

  protected Abound getAboundClient() {
    if (abound == null) {
      final AboundConfig config =
          new AboundConfig(
              TestUtils.TEST_APP_ID,
              TestUtils.TEST_APP_SECRET,
              AboundEnvironment.SANDBOX,
              AboundApiVersion.V2);

      abound = new Abound(config);
    }

    return abound;
  }
}
