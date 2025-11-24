package com.santander.getnet.srv.merchant_portal.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SasToken {

    private String tokenCorp;
    private String cookieCorp;
    private String jwt;
}
