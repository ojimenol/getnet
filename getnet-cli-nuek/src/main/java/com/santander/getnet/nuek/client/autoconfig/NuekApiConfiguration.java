package com.santander.getnet.nuek.client.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.nuek.commerces")
public record NuekApiConfiguration(String url, int retries, int retrySeconds) {
}
