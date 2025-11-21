package com.santander.getnet.srv.merchant_portal.dto;

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
        private String name;
    }
}
