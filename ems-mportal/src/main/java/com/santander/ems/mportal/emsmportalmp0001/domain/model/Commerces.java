package com.santander.ems.mportal.emsmportalmp0001.domain.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Commerces {

    private List<CommerceDTO> commerces;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class CommerceDTO {
        private String personType;
        private String personCode;
        private String commerceCode;
        private String commerceContract;
        private String iban;
        private String commerceName;
        private String postalAddress;
        private Double groupedBilling;
        private String groupedBillingDate;
        private String currency;
        private String dateFrom;
        private Double totalGroupedBilling;
        private String flagTpv;
    }
}
