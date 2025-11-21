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
        private String name;
    }
}
