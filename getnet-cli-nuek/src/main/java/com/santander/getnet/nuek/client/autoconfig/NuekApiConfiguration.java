package com.santander.getnet.nuek.client.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "getnet.client.nuek")
public record NuekApiConfiguration(String url, int retries, int retrySeconds) {
}
