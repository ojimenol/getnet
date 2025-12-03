package com.santander.getnet.nuek.auth.client.autoconfig;

import com.santander.getnet.nuek.auth.client.model.ApiClient;
import com.santander.getnet.nuek.auth.client.model.api.NuekAuthApi;
import com.santander.getnet.lib.config.ExternalBaseAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@AutoConfiguration
@EnableConfigurationProperties(value = NuekAuthApiConfiguration.class)
public class NuekAuthClientAutoConfiguration implements ExternalBaseAutoConfiguration {
  Logger log = LoggerFactory.getLogger(NuekAuthClientAutoConfiguration.class);

  @Bean
  @Qualifier("nuekAuthApiClient")
  ApiClient nuekAuthApiClient(NuekAuthApiConfiguration config) {
    log.info("Creating exampleApiClient");
    ApiClient apiClient = new ApiClient(webClient(getDefaultObjectMapper()), null, null);
    apiClient.setBasePath(config.url());

    return apiClient;
  }

  @Bean
  NuekAuthApi nuekAuthApi(@Qualifier("nuekAuthApiClient") ApiClient apiClient) {
    return new NuekAuthApi(apiClient);
  }
}

