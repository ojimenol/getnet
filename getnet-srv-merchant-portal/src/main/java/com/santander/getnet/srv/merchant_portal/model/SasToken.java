package com.santander.getnet.srv.merchant_portal.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SasToken {

    private String tokenCorp;
    private String cookieCorp;
    private String jwt;
    private Instant timestamp;
    private int expiration;

    public SasToken init() {
        timestamp = Instant.now();
        return this;
    }

    public SasToken expiration(int expiration) {
        expiration = expiration;
        return this;
    }
}
