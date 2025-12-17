package com.santander.getnet.api;

import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.json.JsonMapper;

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
  JsonMapper getJsonMapper();

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