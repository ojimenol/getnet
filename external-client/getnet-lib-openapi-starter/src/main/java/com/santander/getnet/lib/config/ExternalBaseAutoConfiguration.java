package com.santander.getnet.lib.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.JacksonJsonDecoder;
import org.springframework.http.codec.json.JacksonJsonEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;

public interface ExternalBaseAutoConfiguration {

  Logger log = LoggerFactory.getLogger(ExternalBaseAutoConfiguration.class);

  default WebClient webClient(JsonMapper jsonMapper) {
    ExchangeStrategies strategies = ExchangeStrategies
            .builder()
            .codecs(clientDefaultCodecsConfigurer -> {
              clientDefaultCodecsConfigurer.defaultCodecs()
                      .jackson2JsonEncoder(new JacksonJsonEncoder(jsonMapper, MimeTypeUtils.APPLICATION_JSON));
              clientDefaultCodecsConfigurer.defaultCodecs()
                      .jackson2JsonDecoder(new JacksonJsonDecoder(jsonMapper, MimeTypeUtils.APPLICATION_JSON));
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

  default JsonMapper getDefaultJsonMapper() {
    return JsonMapper.builder()
            .configure(JsonReadFeature.ALLOW_MISSING_VALUES, true)
            .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES, true)
            .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
            //.addModule(new JsonNullableModule());
            //.registerModule(new JavaTimeModule())
            .findAndAddModules()
            .build();
  }

}
