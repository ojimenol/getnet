package com.santander.getnet.srv.merchant_portal.service.impl;

import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.nuek.client.model.data.GetComercios200Response;
import com.santander.getnet.nuek.client.model.data.GetComerciosTPVs200Response;
import com.santander.getnet.nuek.client.model.data.GetGroupedBilling200Response;
import com.santander.getnet.nuek.client.model.data.GetTPVOperations200Response;
import com.santander.getnet.srv.merchant_portal.dto.*;
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

  public NuekServiceImpl(NuekApi nuekClient) {
    this.nuekClient = nuekClient;
  }

  @Override
  public CommercesDTO getCommerces(MetadataDTO metadata) {

    return nuekClient.getComercios(new HttpHeaders(), metadata.getPersonCode(), metadata.getPersonType(),
            metadata.getDateFrom(), metadata.getDateTo(), metadata.getOrder(), metadata.getListDateFrom(), metadata.getListDateTo())
        .blockOptional()
        .map(GetComercios200Response::getComercios)
        .map(items ->
            toDTO(items, CommercesDTO.CommerceDTO.class, elems -> CommercesDTO.builder().commerces(elems).build()))
        .orElse(null);
  }

  @Override
  public GroupedBillingDTO getGroupedBilling(MetadataDTO metadata) {
    return nuekClient.getGroupedBilling(new HttpHeaders(), metadata.getPersonCode(), metadata.getPersonType(),
            metadata.getOrder(), metadata.getListDateFrom(), metadata.getListDateTo())
        .blockOptional()
        .map(GetGroupedBilling200Response::getGroupedBilling)
        .map(items ->
            toDTO(items, GroupedBillingDTO.BillingDTO.class, elems -> GroupedBillingDTO.builder().billings(elems).build()))
        .orElse(null);
  }

  @Override
  public CommercesTpvsDTO getCommercesTpv(MetadataDTO metadata) {

    return nuekClient.getComerciosTPVs(new HttpHeaders(), metadata.getPersonCode(), metadata.getPersonType(),
            metadata.getCommerceCode(), metadata.getDateFrom(), metadata.getDateTo(),
            metadata.getListDateFrom(), metadata.getListDateTo(), metadata.getOrder())
        .blockOptional()
        .map(GetComerciosTPVs200Response::getTpvs)
        .map(items ->
            toDTO(items, CommercesTpvsDTO.TpvDTO.class, elems -> CommercesTpvsDTO.builder().tpvs(elems).build()))
        .orElse(null);
  }

  @Override
  public OperationsTpvDTO getOperacionesTpv(MetadataDTO metadata) {
    return nuekClient.getTPVOperations(new HttpHeaders(), metadata.getCommerceContract(), metadata.getOrder(), metadata.getDateFrom(), metadata.getDateTo())
        .blockOptional()
        .map(GetTPVOperations200Response::getOperations)
        .map(items ->
            toDTO(items, OperationsTpvDTO.OperationTpvDTO.class, elems -> OperationsTpvDTO.builder().operations(elems).build()))
        .orElse(null);
  }

  private <T, I, D> D toDTO(List<T> elems, Class<I> itemsClass, Function<List<I>, D> dtoBuilder) {
    var items = elems.stream()
        .map(itemsClass::cast)
        .toList();
    return dtoBuilder.apply(items);
  }
}
