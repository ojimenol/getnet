package com.santander.ems.mportal.emsmportalmp0001.domain.model.sas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Sas response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SasResponse {

  /**
   * The Token corp.
   */
  private String tokenCorp;
  /**
   * The Cookie corp.
   */
  private String cookieCorp;
  /**
   * The Jwt.
   */
  private String jwt;
}
