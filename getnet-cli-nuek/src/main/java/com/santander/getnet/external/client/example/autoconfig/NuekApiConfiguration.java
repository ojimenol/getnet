package com.santander.getnet.external.client.example.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "getnet.client.nuek")
public record NuekApiConfiguration(String url) {
}
