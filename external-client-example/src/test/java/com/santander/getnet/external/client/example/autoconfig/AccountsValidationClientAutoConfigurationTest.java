package com.santander.getnet.external.client.example.autoconfig;

import com.santander.pagonxt.gpp.external.client.accountsvalidation.api.AccountsApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ExampleClientAutoConfiguration.class, JacksonAutoConfiguration.class})
class AccountsValidationClientAutoConfigurationTest {

  @Autowired
  private AccountsApi accountsValidationApi;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(accountsValidationApi);
    Assertions.assertNotNull(accountsValidationApi.getApiClient().getBasePath());
  }
}