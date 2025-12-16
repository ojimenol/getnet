package com.santander.ems.mportal.emsmportalmp0001.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class Cos properties config.
 * for endpoints related to COS
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties("app.cos")
public class CosPropertiesConfig {

  /**
   * The Cos jwe url.
   */
  private String jweurl;

  /**
   * The Cos keyalias.
   */
  private String keyalias;

  /**
   * The Santander client id.
   */
  private String santanderClientId;
}
