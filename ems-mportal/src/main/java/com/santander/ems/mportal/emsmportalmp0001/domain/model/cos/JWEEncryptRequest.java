package com.santander.ems.mportal.emsmportalmp0001.domain.model.cos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * The type Jwe encrypt request.
 */
@Data
@Builder
public class JWEEncryptRequest {

  /**
   * The Keyalias.
   */
  @JsonProperty("keyalias")
  private String keyalias;

  /**
   * The expiration.
   */
  @JsonProperty("expiration")
  private int expiration;

  /**
   * The Payload.
   */
  @JsonProperty("payload")
  private JWEPayload payload;

}
