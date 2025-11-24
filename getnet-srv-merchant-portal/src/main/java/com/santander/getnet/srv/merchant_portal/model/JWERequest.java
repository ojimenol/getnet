package com.santander.getnet.srv.merchant_portal.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWERequest {
    private String keyalias;
    private int expiration;
    private JWEPayloadRequest payload;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JWEPayloadRequest {
        private String billingDateFrom;
        private String billingDateTo;
        private String listDateFrom;
        private String listDateTo;
        private String order;
        private String personCode;
        private String personType;
        private String commerceCode;
        private String commerceContract;
        private String dateFrom;
        private String dateTo;
        private String valueDate;
        private String totalDate;
        private String totalOrder;
    }
}
