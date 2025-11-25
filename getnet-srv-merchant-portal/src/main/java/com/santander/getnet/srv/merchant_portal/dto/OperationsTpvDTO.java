package com.santander.getnet.srv.merchant_portal.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationsTpvDTO {

    private List<OperationTpvDTO> operations;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OperationTpvDTO {
        private Double amountDiscounted;
        private Double amountSubscribed;
        private Double amountTotal;
        private String refTNumOpe;
        private Integer returns;
        private Integer sales;
        private String totalDate;
        private Integer totalOrder;
        private String totalTPV;
        private String totalTime;
        private String valueDate;
        private String cardType;
        private String currency;
    }
}
