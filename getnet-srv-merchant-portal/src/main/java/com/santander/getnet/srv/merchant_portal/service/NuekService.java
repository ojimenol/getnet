package com.santander.getnet.srv.merchant_portal.service;

import com.santander.getnet.srv.merchant_portal.dto.*;

public interface NuekService {

  CommercesDTO getCommerces(NuekRequestDTO metadata);

  GroupedBillingDTO getGroupedBilling(NuekRequestDTO metadata);

  CommerceTpvsDTO getCommercesTpv(NuekRequestDTO metadata);

  OperationsTpvDTO getOperationsTpv(NuekRequestDTO metadata);

  TransactionsTpvDTO getTransactionsTpv(NuekRequestDTO metadata);
}
