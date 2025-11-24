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
        private String name;
    }
}
