package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasResponse;

/**
 * The interface Sas authenticate credentials service.
 */
public interface SasAuthenticateCredentialsService {

  /**
   * Gets credentials.
   *
   * @return the credentials
   */
  SasResponse getCredentials();

}
