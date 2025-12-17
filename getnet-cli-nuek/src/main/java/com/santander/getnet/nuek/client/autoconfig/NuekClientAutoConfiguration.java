package com.santander.getnet.nuek.client.autoconfig;

import com.santander.getnet.nuek.client.model.ApiClient;

import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.lib.config.ExternalBaseAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@AutoConfiguration
@EnableConfigurationProperties(value = NuekApiConfiguration.class)
public class NuekClientAutoConfiguration implements ExternalBaseAutoConfiguration {
  Logger log = LoggerFactory.getLogger(NuekClientAutoConfiguration.class);

  @Bean
  @Qualifier("nuekApiClient")
  ApiClient nuekApiClient(NuekApiConfiguration config) {
    log.info("Creating exampleApiClient");
    ApiClient apiClient = new ApiClient(webClient(getDefaultJsonMapper()), null, null);
    apiClient.setBasePath(config.url());
    apiClient.setRetries(config.retries());
    apiClient.setRetrySeconds(config.retrySeconds());

    return apiClient;
  }

  @Bean
  NuekApi nuekApi(@Qualifier("nuekApiClient") ApiClient apiClient) {
    return new NuekApi(apiClient);
  }
}

