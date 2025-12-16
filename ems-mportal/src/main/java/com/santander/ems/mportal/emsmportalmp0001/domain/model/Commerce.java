package com.santander.ems.mportal.emsmportalmp0001.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commerce {

  private String personType;
  private String personCode;
  private String commerceCode;
  private String commerceContract;
  private String iban;
  private String commerceName;
  private String postalAddress;
  private BigDecimal groupedBilling;
  private String groupedBillingDate;
  private String currency;
  private String dateFrom;
  private BigDecimal totalGroupedBilling;
  private String flagTpv;


}
