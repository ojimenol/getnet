package com.santander.getnet.external.client.example.autoconfig;

import com.santander.getnet.lib.config.ExternalBaseAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@AutoConfiguration
@EnableConfigurationProperties(value = ExampleApiConfiguration.class)
public class ExampleClientAutoConfiguration implements ExternalBaseAutoConfiguration {
  Logger log = LoggerFactory.getLogger(ExampleClientAutoConfiguration.class);

  @Bean
  @Qualifier("exampleApiClient")
  com.santander.getnet.external.client.model.ApiClient exampleApiClient(ExampleApiConfiguration config) {
    log.info("Creating exampleApiClient");
    ApiClient apiClient = new ApiClient(webClient(getDefaultObjectMapper()), null, null);
    apiClient.setBasePath(config.url());

    return apiClient;
  }
}

