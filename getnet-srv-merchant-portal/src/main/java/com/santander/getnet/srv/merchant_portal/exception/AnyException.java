package com.santander.getnet.srv.merchant_portal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnyException extends RuntimeException {

  private String globalExecutionId;
}
