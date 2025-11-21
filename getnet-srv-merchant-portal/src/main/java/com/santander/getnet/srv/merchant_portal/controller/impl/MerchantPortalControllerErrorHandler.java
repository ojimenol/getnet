package com.santander.getnet.srv.merchant_portal.controller.impl;

import com.santander.getnet.srv.merchant_portal.dto.ErrorDTO;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebInputException;

@Component
@Slf4j
public class MerchantPortalControllerErrorHandler {

  public ResponseEntity<Object> handleDefaultError(Exception exception) {
    log.error("Default error.", exception);
    var errors =
        buildError(
            exception,
            exc -> ErrorDTO.builder()
                .code("500")
                .description(exc.getMessage())
                .message("Internal Server Error")
                .build());

    return buildResponseEntityWithBody(HttpStatus.INTERNAL_SERVER_ERROR, errors);
  }

  public ResponseEntity<Object> handleWebClientResponseException(
      WebClientResponseException webClientResponseException) {
    log.error("Web Client Error.", webClientResponseException);

    return ResponseEntity
        .status(webClientResponseException.getStatusCode())
        .body(webClientResponseException.getResponseBodyAs(ErrorDTO.class));
  }

  public ResponseEntity<Object> handleServerWebInputException(
      ServerWebInputException serverWebInputException) {
    log.error("ServerWebInput exception.", serverWebInputException);
    var errors = buildError(
        serverWebInputException,
        exception -> ErrorDTO.builder()
            .code("400")
            .description(exception.getMessage())
            .message("Bad Request")
            .build()
    );

    return buildResponseEntityWithBody(HttpStatus.BAD_REQUEST, errors);
  }

  public ResponseEntity<Object> handleWebExchangeBindException(
      WebExchangeBindException webExchangeBindException) {
    log.error("Bad Request exception.", webExchangeBindException);
    var errors = buildErrors(
        webExchangeBindException.getFieldErrors(),
        error -> ErrorDTO.builder()
            .code("400")
            .description(error.getField() + " " + error.getDefaultMessage())
            .message("Bad Request").build());

    return buildResponseEntityWithBody(HttpStatus.BAD_REQUEST, errors);
  }

  private <T> ResponseEntity<T> buildResponseEntityWithBody(HttpStatus httpStatus, T body) {

    return ResponseEntity.status(httpStatus).body(body);
  }

  private <T extends Exception> ErrorDTO buildError(T exception, Function<T, ErrorDTO> builder) {
    return builder.apply(exception);
  }

  private static <T> ErrorDTO buildErrors(List<T> elemList, Function<T, ErrorDTO> errorBuilder) {
    var errors = Stream.ofNullable(elemList)
        .filter(Predicate.not(List::isEmpty))
        .flatMap(List::stream)
        .map(errorBuilder)
        .toList();

    return ErrorDTO.builder().errors(errors).build();
  }
}