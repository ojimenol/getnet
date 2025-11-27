package com.santander.getnet.srv.merchant_portal.model;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SasToken implements AuthToken {

    private String tokenCorp;
    private String cookieCorp;
    private String jwt;
    private Date expirationDate;

    public SasToken expirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public boolean isExpired() {
        return Instant.ofEpochMilli(expirationDate.getTime()).isBefore(Instant.now());
    }
}
