package com.santander.getnet.srv.merchant_portal.mapper;

import com.santander.getnet.nuek.client.model.data.*;
import com.santander.getnet.srv.merchant_portal.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NuekApiMapper {

    CommercesDTO.CommerceDTO toCommerceDTO(Commerce commerce);

    GroupedBillingDTO.BillingDTO toGroupedBillingDTO(GroupedBilling groupedBilling);

    CommerceTpvsDTO.TpvDTO toCommerceTpvDTO(CommerceTpv commerceTpv);

    OperationsTpvDTO.OperationTpvDTO toOperationTpvDTO(TpvOperation operation);

    TransactionsTpvDTO.TransactionTpvDTO toTransactionTpvDTO(TpvTransaction transaction);
}
