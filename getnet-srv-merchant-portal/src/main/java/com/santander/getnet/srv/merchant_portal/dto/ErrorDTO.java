package com.santander.getnet.srv.merchant_portal.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String code;
    private String description;
    private String message;
    private List<ErrorDTO> errors;
    private Object data;
}
