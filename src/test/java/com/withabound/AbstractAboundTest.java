package com.withabound;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.withabound.util.TestUtils;
import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractAboundTest {
  private MockWebServer mockAboundServer;
  private Abound mockAboundClient;

  private Abound aboundClient;

  @BeforeEach
  public void setup() throws IOException {
    initMockAboundServer();
  }

  @AfterEach
  public void tearDown() throws IOException {
    tearDownMockWebServer();
  }

  /** Performs real requests against the Abound Sandbox environment with test credentials */
  protected Abound getV2AboundClient() {
    if (aboundClient == null) {
      final AboundConfig config =
          new AboundConfig(
              TestUtils.TEST_APP_ID,
              TestUtils.TEST_APP_SECRET,
              AboundEnvironment.SANDBOX,
              AboundApiVersion.V2);

      aboundClient = new Abound(config);
    }

    return aboundClient;
  }

  /** Performs real requests against the Abound Sandbox environment with test credentials */
  protected Abound getAboundClient() {
    if (aboundClient == null) {
      final AboundConfig config =
          new AboundConfig(
              TestUtils.TEST_APP_ID,
              TestUtils.TEST_APP_SECRET,
              AboundEnvironment.SANDBOX,
              AboundApiVersion.V3);

      aboundClient = new Abound(config);
    }

    return aboundClient;
  }

  /**
   * Sends requests to a stubbed Abound server — the {@link AbstractAboundTest#mockAboundServer}.
   */
  protected Abound getMockAboundClient() {
    return mockAboundClient;
  }

  /**
   * Returns the mock Abound server. Requests issued using the {@link
   * AbstractAboundTest#mockAboundClient} client are sent to this mock server.
   */
  protected MockWebServer getMockAboundServer() {
    return mockAboundServer;
  }

  /** Inits the mock Abound server and enqueues one mock successful response. */
  private void initMockAboundServer() throws IOException {
    mockAboundServer = new MockWebServer();
    mockAboundServer.start();
    mockAboundServer.enqueue(new MockResponse());

    final AboundConfig mockConfig = mock(AboundConfig.class);
    final String mockWebServerAddress =
        String.format("http://localhost:%s", mockAboundServer.getPort());
    doReturn(mockWebServerAddress).when(mockConfig).getBaseUrl();
    mockAboundClient = new Abound(mockConfig);
  }

  private void tearDownMockWebServer() throws IOException {
    mockAboundServer.shutdown();
  }
}
