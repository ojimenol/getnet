package com.santander.getnet.nuek.auth.client.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.nuek.auth")
public record NuekAuthApiConfiguration(String url, int retries, int retrySeconds) {
}
