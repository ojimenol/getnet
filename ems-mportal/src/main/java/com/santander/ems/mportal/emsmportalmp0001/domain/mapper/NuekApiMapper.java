package com.santander.ems.mportal.emsmportalmp0001.domain.mapper;

import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.*;
import com.santander.getnet.nuek.client.model.data.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NuekApiMapper {

    @Mappings({
        @Mapping(source = "command.billingDateFrom", target = "dateFrom"),
        @Mapping(source = "command.billingDateTo", target = "dateTo")
    })
    NuekRequestDTO toRequestParams(CommercesGetCommand command);

    Commerces.CommerceDTO toCommerceDTO(Commerce commerce);

    GroupedBillingDTO.BillingDTO toGroupedBillingDTO(GroupedBilling groupedBilling);

    CommerceTpvsDTO.TpvDTO toCommerceTpvDTO(CommerceTpv commerceTpv);

    OperationsTpvDTO.OperationTpvDTO toOperationTpvDTO(TpvOperation operation);

    TransactionsTpvDTO.TransactionTpvDTO toTransactionTpvDTO(TpvTransaction transaction);
}
