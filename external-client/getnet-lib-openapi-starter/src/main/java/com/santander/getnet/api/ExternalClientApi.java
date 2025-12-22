package com.santander.getnet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

public interface ExternalClientApi {

  /**
   * Get the current base path
   * @return String the base path
   */
  String getBasePath();

  /**
   * Get the ObjectMapper used to make HTTP requests.
   * @return ObjectMapper objectMapper
   */
  ObjectMapper getObjectMapper();

  /**
   * Get the WebClient used to make HTTP requests.
   * @return WebClient webClient
   */
  WebClient getWebClient();

  /**
   * Set the WebClient used to make HTTP requests.
   * @param  webClient webClient
   */
  void setWebClient(WebClient webClient);
}