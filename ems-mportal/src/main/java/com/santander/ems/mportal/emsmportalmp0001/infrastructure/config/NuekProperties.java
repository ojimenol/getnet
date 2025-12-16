package com.santander.ems.mportal.emsmportalmp0001.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.nuek")
public class NuekProperties {
  private String commerces;
}
