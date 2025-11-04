package com.santander.getnet.lib.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

public interface ExternalBaseAutoConfiguration {

  Logger log = LoggerFactory.getLogger(ExternalBaseAutoConfiguration.class);

  default WebClient webClient(ObjectMapper objectMapper) {
    ExchangeStrategies strategies = ExchangeStrategies
            .builder()
            .codecs(clientDefaultCodecsConfigurer -> {
              clientDefaultCodecsConfigurer.defaultCodecs()
                      .jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
              clientDefaultCodecsConfigurer.defaultCodecs()
                      .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
            }).build();

    log.info("Initializing web client with custom connection pool... ");

    return WebClient.create()
            .mutate()
            .clientConnector(new ReactorClientHttpConnector(
                HttpClient.create(ConnectionProvider.builder("custom")
                        .maxConnections(500)
                        .maxIdleTime(Duration.ofSeconds(20))
                        .maxLifeTime(Duration.ofSeconds(60))
                        .pendingAcquireTimeout(Duration.ofSeconds(60))
                        .evictInBackground(Duration.ofSeconds(120)).build())
                      .resolver(DefaultAddressResolverGroup.INSTANCE)))
            .exchangeStrategies(strategies)
            .build();
  }

  default ObjectMapper getDefaultObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();

    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    mapper.registerModule(new JsonNullableModule());
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }

}
