package com.santander.getnet.nuek.auth.client.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "getnet.client.nuek-auth")
public record NuekAuthApiConfiguration(String url) {
}
