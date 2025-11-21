package com.santander.getnet.srv.merchant_portal.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CommercesDTO {

    private List<CommerceDTO> commerces;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class CommerceDTO {
        private String name;
    }
}
