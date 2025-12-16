package com.santander.ems.mportal.emsmportalmp0001.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Sas properties config.
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties("app.sas")
public class SasPropertiesConfig {

  /**
   * The Sas authenticate url.
   */
  private String authUrl;

  /**
   * The Credential type jwt.
   */
  private String credentialTypeJWT;

  /**
   * The Credential type token corp.
   */
  private String credentialTypeTokenCorp;

  /**
   * The Uid.
   */
  private String uid;

  /**
   * The Password.
   */
  private String password;

  /**
   * The Realm.
   */
  private String realm;
}
