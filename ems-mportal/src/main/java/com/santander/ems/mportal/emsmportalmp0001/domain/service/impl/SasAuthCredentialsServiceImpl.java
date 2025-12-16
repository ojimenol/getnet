package com.santander.ems.mportal.emsmportalmp0001.domain.service.impl;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.IdAttributes;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasRequest;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.SasAuthenticateCredentialsService;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.config.SasPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sas authenticate credentials service.
 */
@Slf4j
@Service
public class SasAuthCredentialsServiceImpl implements SasAuthenticateCredentialsService {

  /**
   * Web Client Service
   */
  private final WebClient webClient;

  /**
   * Properties
   */
  private final SasPropertiesConfig properties;

  /**
   * Instantiates a new Sas authenticate credentials service.
   *
   * @param webClientBuilder the web client builder
   * @param properties       the properties
   */
  public SasAuthCredentialsServiceImpl(WebClient.Builder webClientBuilder, SasPropertiesConfig properties) {
    this.properties = properties;
    this.webClient = webClientBuilder
      .baseUrl(this.properties.getAuthUrl())
      .build();
  }

  /**
   * Get credentials
   * for user
   * in properties
   *
   * @return
   */
  @Override
  public SasResponse getCredentials() {
    return webClient.post()
      .bodyValue(getSasRequest())
      .retrieve()
      .onStatus(HttpStatusCode::is4xxClientError, resp -> Mono.error(new HttpClientErrorException(resp.statusCode())))
      .onStatus(HttpStatusCode::is5xxServerError, resp -> Mono.error(new HttpServerErrorException(resp.statusCode())))
      .bodyToMono(SasResponse.class)
      .block();
  }

  /**
   * Gets sas request.
   *
   * @return the sas request
   */
  private SasRequest getSasRequest() {

    List<String> credentialTypeList = new ArrayList<>();
    credentialTypeList.add(properties.getCredentialTypeJWT());
    credentialTypeList.add(properties.getCredentialTypeTokenCorp());

    return SasRequest.builder()
      .credentialType(credentialTypeList)
      .idAttributes(IdAttributes.builder().uid(properties.getUid()).build())
      .password(properties.getPassword())
      .realm(properties.getRealm())
      .build();
  }

}