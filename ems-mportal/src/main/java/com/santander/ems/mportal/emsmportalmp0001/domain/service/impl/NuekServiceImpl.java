package com.santander.ems.mportal.emsmportalmp0001.domain.service.impl;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.mapper.NuekApiMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.*;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEToken;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.NuekAuthService;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.NuekService;
import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.nuek.client.model.data.*;
import com.santander.getnet.nuek.client.model.data.Commerce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class NuekServiceImpl implements NuekService {

  private static final Logger LOG = LoggerFactory.getLogger(NuekServiceImpl.class);

  private final NuekApi nuekClient;

  private final NuekApiMapper nuekApiMapper;

  private final NuekAuthService nuekAuthService;

  public NuekServiceImpl(NuekApi nuekClient, NuekApiMapper nuekApiMapper, NuekAuthService nuekAuthService) {

    this.nuekClient = nuekClient;
    this.nuekApiMapper = nuekApiMapper;
    this.nuekAuthService = nuekAuthService;
  }

  @Override
  public Commerces getCommerces(NuekRequestDTO request) {

    final var mockCommercesResponse = new CommercesResponse().commerceList(List.of(
            new Commerce().commerceCode("11").commerceContract("123").commerceName("mockCommerce")));

    return Mono.just(request)
        .map(req -> buildHttpHeadersWithAuthHeader(req, nuekAuthService::getJWEToken4Commerces))
        .flatMap(headers ->
            nuekClient.getComercios(headers, request.getPersonCode(), request.getPersonType(),
                    request.getDateFrom(), request.getDateTo(), request.getOrder(),
                    request.getListDateFrom(), request.getListDateTo()))
        .doOnError(th ->
           LOG.error("Error calling NUEK endpoint: /api/Comercios/commerces. Error : {}", th.getMessage()))
        .onErrorReturn(mockCommercesResponse)
        .blockOptional()
        .map(CommercesResponse::getCommerceList)
        .map(items ->
            toDTO(items, nuekApiMapper::toCommerceDTO,
                commerces -> Commerces.builder().commerces(commerces).build()))
        .orElse(null);
  }

  @Override
  public GroupedBillingDTO getGroupedBilling(NuekRequestDTO metadata) {
    var mockGroupedBillingResponse = new GroupedBillingResponse().commerceGroupedBillingList(
            List.of(new GroupedBilling().groupedBilling(123.45).groupedBillingDate("20251010").currency("EUR")));

    return Mono.just(metadata)
        .map(req -> buildHttpHeadersWithAuthHeader(req, nuekAuthService::getJWEToken4GroupedBilling))
        .flatMap(headers -> nuekClient.getComerciosGroupedBilling(headers, metadata.getPersonCode(), metadata.getPersonType(),
                metadata.getOrder(), metadata.getListDateFrom(), metadata.getListDateTo()))
        .doOnError(th -> LOG.error("Error calling NUEK endpoint: /api/Comercios/groupedbilling. Error : {}", th.getMessage()))
        .onErrorReturn(mockGroupedBillingResponse)
        .blockOptional()
        .map(GroupedBillingResponse::getCommerceGroupedBillingList)
        .map(items ->
                toDTO(items, nuekApiMapper::toGroupedBillingDTO, billings -> GroupedBillingDTO.builder().billings(billings).build()))
        .orElse(null);
  }

  @Override
  public CommerceTpvsDTO getCommercesTpv(NuekRequestDTO metadata) {

    var mockCommerceTpvsResponse = new CommerceTpvsResponse().commerceTPVs(
        List.of(new CommerceTpv().commerceCode("11").groupedBilling(123.45).groupedBillingDate("20251011").currency("EUR")));

    return Mono.just(metadata)
        .map(req -> buildHttpHeadersWithAuthHeader(req, nuekAuthService::getJWEToken4CommerceTpvs))
        .flatMap(headers -> nuekClient.getComercioTpvs(headers, metadata.getPersonCode(), metadata.getPersonType(),
                metadata.getCommerceCode(), metadata.getDateFrom(), metadata.getDateTo(),
                metadata.getListDateFrom(), metadata.getListDateTo(), metadata.getOrder()))
        .doOnError(th -> LOG.error("Error calling NUEK endpoint: /api/Comercio/tpvs. Error : {}", th.getMessage()))
        .onErrorReturn(mockCommerceTpvsResponse)
        .blockOptional()
        .map(CommerceTpvsResponse::getCommerceTPVs)
        .map(items ->
                toDTO(items, nuekApiMapper::toCommerceTpvDTO, tpvs -> CommerceTpvsDTO.builder().tpvs(tpvs).build()))
        .orElse(null);
  }

  @Override
  public OperationsTpvDTO getOperationsTpv(NuekRequestDTO metadata) {
    var mockOperationsTpvsResponse = new TpvOperationsResponse()
        .numOperations(1)
        .clearances(
            List.of(new TpvOperation().cardType("VISA").amountDiscounted(123.45).amountSubscribed(234.56).amountTotal(358.01).currency("EUR")));

    return Mono.just(metadata)
        .map(req -> buildHttpHeadersWithAuthHeader(req, nuekAuthService::getJWEToken4TpvOperations))
        .flatMap(headers ->
            nuekClient.getTpvOperations(headers, metadata.getCommerceContract(), metadata.getOrder(),
                metadata.getDateFrom(), metadata.getDateTo()))
        .doOnError(th -> LOG.error("Error calling NUEK endpoint: /api/TPV/tpv_clearance. Error : {}", th.getMessage()))
        .onErrorReturn(mockOperationsTpvsResponse)
        .blockOptional()
        .map(TpvOperationsResponse::getClearances)
        .map(items ->
                toDTO(items, nuekApiMapper::toOperationTpvDTO, ops -> OperationsTpvDTO.builder().operations(ops).build()))
        .orElse(null);
  }

  @Override
  public TransactionsTpvDTO getTransactionsTpv(NuekRequestDTO metadata) {
    var mockTransactionsTpvsResponse = new TpvTransactionsResponse()
        .operationClearances(
            List.of(new TpvTransaction().numOperation("000001").dateOperation("20251025").cardBrand("VISA").totalOrder(54).currency("EUR")));

    return Mono.just(metadata)
            .map(req -> buildHttpHeadersWithAuthHeader(req, nuekAuthService::getJWEToken4TpvTransactions))
            .flatMap(headers -> nuekClient.getTpvTansactions(headers, metadata.getCommerceContract(), metadata.getProperties().get("valueDate").toString(),
                    metadata.getProperties().get("totalDate").toString(), metadata.getProperties().get("totalOrder").toString()))
            .doOnError(th -> LOG.error("Error calling NUEK endpoint: api/CierreCaja/clearance_operations. Error : {}", th.getMessage()))
            .onErrorReturn(mockTransactionsTpvsResponse)
            .blockOptional()
            .map(TpvTransactionsResponse::getOperationClearances)
            .map(items ->
                    toDTO(items, nuekApiMapper::toTransactionTpvDTO, tx -> TransactionsTpvDTO.builder().transactions(tx).build()))
            .orElse(null);
  }

  private HttpHeaders buildHttpHeadersWithAuthHeader(NuekRequestDTO request, Function<NuekRequestDTO, JWEToken> jweGetter) {

    final var httpHeaders = new HttpHeaders();

    Optional.of(request)
            .map(jweGetter)
            .map(JWEToken::getJwe)
            .map(jwe -> "Bearer " + jwe)
            .map(List::of)
            .ifPresent(values -> httpHeaders.put(HttpHeaders.AUTHORIZATION, values));

    return httpHeaders;
  }

  private <T, I, D> D toDTO(List<T> elems, Function<T, I> itemsTransform, Function<List<I>, D> dtoBuilder) {
    var items = elems.stream()
        .map(itemsTransform)
        .toList();
    return dtoBuilder.apply(items);
  }
}
