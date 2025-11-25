package com.santander.getnet.srv.merchant_portal.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsTpvDTO {

    private List<TransactionTpvDTO> transactions;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionTpvDTO {
        private String cardBrand;
        private String cardPan;
        private String cardTypeCode;
        private String currency;
        private String dateOperation;
        private String numOperation;
        private String operationType;
        private Double saleImport;
        private String timeOperation;
        private Integer totalOrder;
    }
}
