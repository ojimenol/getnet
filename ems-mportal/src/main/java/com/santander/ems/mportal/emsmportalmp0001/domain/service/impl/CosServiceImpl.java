package com.santander.ems.mportal.emsmportalmp0001.domain.service.impl;


import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.*;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.CosService;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.SasAuthenticateCredentialsService;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.config.CosPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The type Cos service.
 */
@Slf4j
@Service
public class CosServiceImpl implements CosService {

  /**
   * Web Client Service
   */
  private final WebClient webClient;

  /**
   * Cos Properties
   */
  private final CosPropertiesConfig cosProperties;

  /**
   * The Sas authenticate credentials service.
   */
  @Autowired
  private SasAuthenticateCredentialsService sasAuthenticateCredentialsService;

  /**
   * Instantiates a
   * new JweService service.
   *
   * @param webClientBuilder the web client builder
   * @param cosProperties    the properties
   */
  public CosServiceImpl(WebClient.Builder webClientBuilder, CosPropertiesConfig cosProperties) {
    this.cosProperties = cosProperties;
    this.webClient = webClientBuilder
      .baseUrl(this.cosProperties.getJweurl())
      .build();
  }

  /**
   * @param data the data
   * @return JWEEncryptedResponse
   */
  @Override
  public JWEEncryptResponse generateJwe(PayloadCommerceRequest data) {
    SasResponse sasResponse = sasAuthenticateCredentialsService.getCredentials();

    return webClient.post()
      .header("Authorization", "Bearer " + sasResponse.getJwt())
      .header("Authentication", "Bearer " + sasResponse.getJwt())
      .header("Content-Type", "application/json")
      .header("x-santander-client-id", cosProperties.getSantanderClientId())
      .bodyValue(getJWWERequest(data))
      .retrieve()
      .onStatus(HttpStatusCode::is4xxClientError, resp -> Mono.error(new HttpClientErrorException(resp.statusCode())))
      .onStatus(HttpStatusCode::is5xxServerError, resp -> Mono.error(new HttpServerErrorException(resp.statusCode())))
      .bodyToMono(JWEEncryptResponse.class)
      .block();
  }

  /**
   * Gets jwee request.
   *
   * @param data the data
   * @return the jwee request
   */
  private JWEEncryptRequest getJWWERequest(PayloadCommerceRequest data) {

    return JWEEncryptRequest.builder()
      .keyalias(cosProperties.getKeyalias())
      .expiration(60)
      .payload(JWEPayload.builder()
          .personCode(data.getPersonCode())
          .personType(data.getPersonType())
          .order(data.getOrder())
          .billingDateFrom(data.getBillingDateFrom())
          .billingDateTo(data.getBillingDateTo())
          .listDateFrom(data.getListDateFrom())
          .listDateTo(data.getListDateTo())
          .build())
      .build();
  }

}
