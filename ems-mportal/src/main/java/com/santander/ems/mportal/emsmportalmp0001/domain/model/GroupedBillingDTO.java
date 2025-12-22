package com.santander.ems.mportal.emsmportalmp0001.domain.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupedBillingDTO {

    private List<BillingDTO> billings;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BillingDTO {
        private String groupedBillingDate;
        private Double groupedBilling;
        private String currency;
    }
}
