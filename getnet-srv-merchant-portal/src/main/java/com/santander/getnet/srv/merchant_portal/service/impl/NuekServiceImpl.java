package com.santander.getnet.srv.merchant_portal.service.impl;

import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.nuek.client.model.data.*;
import com.santander.getnet.srv.merchant_portal.dto.*;
import com.santander.getnet.srv.merchant_portal.mapper.NuekApiMapper;
import com.santander.getnet.srv.merchant_portal.service.NuekAuthService;
import com.santander.getnet.srv.merchant_portal.service.NuekService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class NuekServiceImpl implements NuekService {

  private static final Logger log = LoggerFactory.getLogger(NuekServiceImpl.class);

  private final NuekApi nuekClient;

  private final NuekApiMapper nuekApiMapper;

  private final NuekAuthService nuekAuthService;

  public NuekServiceImpl(NuekApi nuekClient, NuekApiMapper nuekApiMapper, NuekAuthService nuekAuthService) {

    this.nuekClient = nuekClient;
    this.nuekApiMapper = nuekApiMapper;
    this.nuekAuthService = nuekAuthService;
  }

  @Override
  public CommercesDTO getCommerces(NuekRequestDTO metadata) {

    final var httpHeaders = new HttpHeaders();
    httpHeaders.put(HttpHeaders.AUTHORIZATION, List.of("Bearer" + nuekAuthService.getJWEToken4Commerces(metadata)));

    return nuekClient.getComercios(httpHeaders, metadata.getPersonCode(), metadata.getPersonType(),
            metadata.getDateFrom(), metadata.getDateTo(), metadata.getOrder(), metadata.getListDateFrom(), metadata.getListDateTo())
        .blockOptional()
        .map(CommercesResponse::getCommerceList)
        .map(items ->
            toDTO(items, nuekApiMapper::toCommerceDTO, commerces -> CommercesDTO.builder().commerces(commerces).build()))
        .orElse(null);
  }

  @Override
  public GroupedBillingDTO getGroupedBilling(NuekRequestDTO metadata) {
    return nuekClient.getComerciosGroupedBilling(new HttpHeaders(), metadata.getPersonCode(), metadata.getPersonType(),
            metadata.getOrder(), metadata.getListDateFrom(), metadata.getListDateTo())
        .blockOptional()
        .map(GroupedBillingResponse::getCommerceGroupedBillingList)
        .map(items ->
            toDTO(items, nuekApiMapper::toGroupedBillingDTO, billings -> GroupedBillingDTO.builder().billings(billings).build()))
        .orElse(null);
  }

  @Override
  public CommerceTpvsDTO getCommercesTpv(NuekRequestDTO metadata) {

    return nuekClient.getComercioTpvs(new HttpHeaders(), metadata.getPersonCode(), metadata.getPersonType(),
            metadata.getCommerceCode(), metadata.getDateFrom(), metadata.getDateTo(),
            metadata.getListDateFrom(), metadata.getListDateTo(), metadata.getOrder())
        .blockOptional()
        .map(CommerceTpvsResponse::getCommerceTPVs)
        .map(items ->
            toDTO(items, nuekApiMapper::toCommerceTpvDTO, elems -> CommerceTpvsDTO.builder().tpvs(elems).build()))
        .orElse(null);
  }

  @Override
  public OperationsTpvDTO getOperationsTpv(NuekRequestDTO metadata) {
    return nuekClient.getTpvOperations(new HttpHeaders(), metadata.getCommerceContract(), metadata.getOrder(), metadata.getDateFrom(), metadata.getDateTo())
        .blockOptional()
        .map(TpvOperationsResponse::getClearances)
        .map(items ->
            toDTO(items, nuekApiMapper::toOperationTpvDTO, elems -> OperationsTpvDTO.builder().operations(elems).build()))
        .orElse(null);
  }

  @Override
  public TransactionsTpvDTO getTransactionsTpv(NuekRequestDTO metadata) {
    return nuekClient.getTpvTansactions(new HttpHeaders(), metadata.getCommerceContract(), metadata.getOrder(), metadata.getDateFrom(), metadata.getDateTo())
            .blockOptional()
            .map(TpvTransactionsResponse::getOperationClearances)
            .map(items ->
                    toDTO(items, nuekApiMapper::toTransactionTpvDTO, elems -> TransactionsTpvDTO.builder().transactions(elems).build()))
            .orElse(null);
  }

  private <T, I, D> D toDTO(List<T> elems, Function<T, I> itemsTransform, Function<List<I>, D> dtoBuilder) {
    var items = elems.stream()
        .map(itemsTransform)
        .toList();
    return dtoBuilder.apply(items);
  }
}
