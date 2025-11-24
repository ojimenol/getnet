package com.santander.getnet.srv.merchant_portal.controller.impl;

import com.santander.getnet.srv.merchant_portal.controller.MerchantPortalController;
import com.santander.getnet.srv.merchant_portal.dto.*;
import com.santander.getnet.srv.merchant_portal.service.NuekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebInputException;

@RestController
@RequiredArgsConstructor
public class MerchantPortalControllerImpl implements MerchantPortalController {

  private static final String HDR_X_CLIENT_ID = "x-sub-id";

  private final NuekService nuekService;

  private final MerchantPortalControllerErrorHandler errorHandler;

  @Override
  @GetMapping("/commerces/{requestId}/{personCode}/{personType}/{order}")
  public ResponseEntity<CommercesDTO> getCommerces(
      @PathVariable("requestId") String requestId,
      @PathVariable("personCode") String personCode,
      @PathVariable("personType") String personType,
      @PathVariable("order") String order) {

      var metadata = NuekRequestDTO.builder()
          .personCode(personCode)
          .personType(personType)
          .order(order)
          .build();

      return ResponseEntity.ok(nuekService.getCommerces(metadata));
  }

  @Override
  @GetMapping("/groupedBilling/{requestId}")
  public ResponseEntity<GroupedBillingDTO> getGroupedBilling(
      @RequestHeader(HDR_X_CLIENT_ID) String xClientId,
      @PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getGroupedBilling(null));
  }

  @Override
  @GetMapping("/commerces/tpv/{requestId}")
  public ResponseEntity<CommercesTpvsDTO> getTpvsCommerce(
      @RequestHeader(HDR_X_CLIENT_ID) String xClientId,
      @PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getCommercesTpv(null));
  }

  @Override
  @GetMapping("/operations/tpv/{requestId}/")
  public ResponseEntity<OperationsTpvDTO> getTpvOperations(
      @RequestHeader(HDR_X_CLIENT_ID) String xClientId,
      @PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getOperationsTpv(null));
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleException(Exception e) {
    if (e instanceof WebExchangeBindException webExchangeBindException) {
      return errorHandler.handleWebExchangeBindException(webExchangeBindException);
    } else if (e instanceof ServerWebInputException theEx) {
      return errorHandler.handleServerWebInputException(theEx);
    } else if (e instanceof WebClientResponseException webClientResponseException) {
      return errorHandler.handleWebClientResponseException(webClientResponseException);
    } else {
      return errorHandler.handleDefaultError(e);
    }
  }
}
