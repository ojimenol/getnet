package com.santander.getnet.nuek.client.autoconfig;

import com.santander.getnet.nuek.client.model.api.NuekApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {NuekClientAutoConfiguration.class, JacksonAutoConfiguration.class})
class NuekClientAutoConfigurationTest {

  @Autowired
  private NuekApi nuekApi;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(nuekApi);
    Assertions.assertNotNull(nuekApi.getApiClient().getBasePath());
  }
}