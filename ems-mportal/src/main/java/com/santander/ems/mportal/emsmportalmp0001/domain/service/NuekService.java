package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.*;

public interface NuekService {

  Commerces getCommerces(NuekRequestDTO request);

  GroupedBillingDTO getGroupedBilling(NuekRequestDTO request);

  CommerceTpvsDTO getCommercesTpv(NuekRequestDTO request);

  OperationsTpvDTO getOperationsTpv(NuekRequestDTO request);

  TransactionsTpvDTO getTransactionsTpv(NuekRequestDTO request);
}
