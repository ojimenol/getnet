package com.santander.getnet.srv.merchant_portal.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommerceTpvsDTO {

    private String name;

    private List<TpvDTO> tpvs;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TpvDTO {
        private String personType;
        private String personCode;
        private String commerceCode;
        private Integer numTpv;
        private String numSerialTpv;
        private Double groupedBilling;
        private String groupedBillingDate;
        private String currency;
        private String terminalEnvCode;
        private String terminalEnvDesc;
        private String terminalModel;
        private String terminalModelDesc;
        private String dateFrom;
        private Double totalGroupedBilling;
        private String dataDatePart;
    }
}
