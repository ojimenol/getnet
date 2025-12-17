package com.santander.ems.mportal.emsmportalmp0001.domain.model.cos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JWEPayload {
    private String billingDateFrom;
    private String billingDateTo;
    private String listDateFrom;
    private String listDateTo;
    private String order;
    private String personCode;
    private String personType;
    private String commerceCode;
    private String commerceContract;
    private String dateFrom;
    private String dateTo;
    private String valueDate;
    private String totalDate;
    private String totalOrder;
}
