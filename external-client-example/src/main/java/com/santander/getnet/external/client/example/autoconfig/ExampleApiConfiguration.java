package com.santander.getnet.external.client.example.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "getnet.external-client.example")
public record ExampleApiConfiguration(String url) {
}
