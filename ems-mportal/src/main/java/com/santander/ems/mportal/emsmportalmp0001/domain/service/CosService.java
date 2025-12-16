package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEEncryptResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.PayloadCommerceRequest;

/**
 * The interface Cos service.
 */
public interface CosService {

  /**
   * Generate jwe jwe encrypt response.
   *
   * @param data the data
   * @return the jwe encrypt response
   */
  JWEEncryptResponse generateJwe(PayloadCommerceRequest data);

}
