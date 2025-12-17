package com.santander.getnet.nuek.auth.client.autoconfig;

import com.santander.getnet.nuek.auth.client.model.api.NuekAuthApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {NuekAuthClientAutoConfiguration.class})
class NuekAuthClientAutoConfigurationTest {

  @Autowired
  private NuekAuthApi nuekAuthApi;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(nuekAuthApi);
    Assertions.assertNotNull(nuekAuthApi.getApiClient());
  }

  @Test
  void contextPropertiesCheck() {
    Assertions.assertNotNull(nuekAuthApi);
    Assertions.assertNotNull(nuekAuthApi.getApiClient());
    Assertions.assertNotNull(nuekAuthApi.getApiClient().getBasePath());
    Assertions.assertTrue(nuekAuthApi.getApiClient().getRetries() > 0);
    Assertions.assertTrue(nuekAuthApi.getApiClient().getRetrySeconds() > 0);
  }
}