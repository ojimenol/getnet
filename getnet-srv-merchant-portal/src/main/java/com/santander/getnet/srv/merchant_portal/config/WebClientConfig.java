package com.santander.getnet.srv.merchant_portal.config;

import com.santander.getnet.external.client.model.api.NuekApi;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.JacksonJsonDecoder;
import org.springframework.http.codec.json.JacksonJsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper.builder()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .findAndAddModules()
            .build();
    }

    @Bean
    public WebClient webClient(JsonMapper mapper) {

        log.info("Creating webclient with configured pool... ");

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create(ConnectionProvider.builder("custom")
                                        .maxConnections(500)
                                        .maxIdleTime(Duration.ofSeconds(20))
                                        .maxLifeTime(Duration.ofSeconds(60))
                                        .pendingAcquireTimeout(Duration.ofSeconds(60))
                                        .evictInBackground(Duration.ofSeconds(120)).build())
                                .resolver(DefaultAddressResolverGroup.INSTANCE)))
                .exchangeStrategies(ExchangeStrategies.builder()
                    .codecs(clientCodecConfigurer -> {
                        clientCodecConfigurer.defaultCodecs().jacksonJsonEncoder(
                            new JacksonJsonEncoder(mapper, MediaType.APPLICATION_JSON));
                        clientCodecConfigurer.defaultCodecs().jacksonJsonDecoder(
                            new JacksonJsonDecoder(mapper, MediaType.APPLICATION_JSON));
                    })
                        .build())
                .build();
    }
}
