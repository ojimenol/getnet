package com.santander.getnet.srv.merchant_portal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NuekRequestDTO {

  private String personCode;
  private String personType;
  private String commerceCode;
  private String commerceContract;
  private String order;
  private String dateFrom;
  private String dateTo;
  private String listDateFrom;
  private String listDateTo;
  @JsonProperty("props")
  private Map<String,Object> properties;

}
