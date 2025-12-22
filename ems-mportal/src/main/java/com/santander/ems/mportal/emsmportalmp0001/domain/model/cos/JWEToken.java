package com.santander.ems.mportal.emsmportalmp0001.domain.model.cos;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.AuthToken;
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
