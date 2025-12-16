package com.santander.ems.mportal.emsmportalmp0001.domain.service.impl;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerce;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.CommerceListResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEEncryptResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.PayloadCommerceRequest;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.CommerceService;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.CosService;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.config.NuekProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CommerceServiceImpl implements CommerceService {

  private final WebClient webClient;

  private final NuekProperties properties;

  private CosService cosService;

  public CommerceServiceImpl(WebClient.Builder webClientBuilder, NuekProperties nuekProperties, CosService cosService) {
    this.properties = nuekProperties;
    this.cosService = cosService;
    this.webClient = webClientBuilder
      .baseUrl(this.properties.getCommerces())
      .build();
  }


  @Override
  public List<Commerce> getCommerces(
          String personCode, String personType,
          String billingDateFrom, String billingDateTo, String order,
          String listDateFrom, String listDateTo) {

    JWEEncryptResponse encryptResponse =
        cosService.generateJwe(
            buildCommerceRequest(
                personCode, personType, billingDateFrom,
                    billingDateTo, order, listDateFrom, listDateTo));

    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .queryParam("personCode", personCode)
        .queryParam("personType", personType)
        .queryParam("billingDateFrom", billingDateFrom)
        .queryParam("billingDateTo", billingDateTo)
        .queryParam("order", order)
        .queryParam("listDateFrom", listDateFrom)
        .queryParam("listDateTo", listDateTo)
        .build())
      .header(HttpHeaders.AUTHORIZATION, encryptResponse.getJwe())
      .retrieve()
      .onStatus(HttpStatusCode::is4xxClientError,
          resp -> Mono.error(new HttpClientErrorException(resp.statusCode())))
      .onStatus(HttpStatusCode::is5xxServerError,
          resp -> Mono.error(new HttpServerErrorException(resp.statusCode())))
      .bodyToMono(CommerceListResponse.class)
      .map(CommerceListResponse::getCommerceList)
      .block();
  }

  private PayloadCommerceRequest buildCommerceRequest(
          String personCode, String personType, String billingDateFrom,
          String billingDateTo, String order, String listDateFrom, String listDateTo) {
    return PayloadCommerceRequest.builder()
      .personCode(personCode)
      .personType(personType)
      .billingDateFrom(billingDateFrom)
      .billingDateTo(billingDateTo)
      .order(order)
      .listDateFrom(listDateFrom)
      .listDateTo(listDateTo)
      .build();
  }
}
