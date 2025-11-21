package com.santander.getnet.srv.merchant_portal.service;

import com.santander.getnet.srv.merchant_portal.dto.*;

public interface NuekService {

  CommercesDTO getCommerces(MetadataDTO metadata);

  GroupedBillingDTO getGroupedBilling(MetadataDTO metadata);

  CommercesTpvsDTO getCommercesTpv(MetadataDTO metadata);

  OperationsTpvDTO getOperacionesTpv(MetadataDTO metadata);
}
