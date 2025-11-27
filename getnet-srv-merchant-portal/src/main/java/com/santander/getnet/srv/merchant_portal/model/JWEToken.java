package com.santander.getnet.srv.merchant_portal.model;

import lombok.*;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JWEToken implements AuthToken {
    private String jwe;
    private Date expirationDate;

    public JWEToken expirationDate(Date date) {
        this.expirationDate = date;
        return this;
    }

    public boolean isExpired() {
        return Instant.ofEpochMilli(expirationDate.getTime()).isBefore(Instant.now());
    }
}
