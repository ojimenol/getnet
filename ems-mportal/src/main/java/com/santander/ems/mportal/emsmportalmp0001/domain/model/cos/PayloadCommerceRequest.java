package com.santander.ems.mportal.emsmportalmp0001.domain.model.cos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PayloadCommerceRequest {

  private String personCode;
  private String personType;
  private String billingDateFrom;
  private String billingDateTo;
  private String listDateFrom;
  private String listDateTo;
  private String order;
}
