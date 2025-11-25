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

  private final NuekService nuekService;

  private final MerchantPortalControllerErrorHandler errorHandler;

  @Override
  @GetMapping("/commerces/{requestId}")
  public ResponseEntity<CommercesDTO> getCommerces(@PathVariable("requestId") String requestId) {

      var request = NuekRequestDTO.builder()
          .requestId(requestId)
          .personCode("884407")
          .personType("F")
          .dateFrom("20250501")
          .dateTo("20251024")
          .listDateFrom("20251001")
          .listDateTo("20251031")
          .order("D")
          .build();

      return ResponseEntity.ok(nuekService.getCommerces(request));
  }

  @Override
  @GetMapping("/groupedBilling/{requestId}")
  public ResponseEntity<GroupedBillingDTO> getGroupedBilling(@PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getGroupedBilling(null));
  }

  @Override
  @GetMapping("/commerce/tpv/{requestId}")
  public ResponseEntity<CommerceTpvsDTO> getTpvsCommerce(@PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getCommercesTpv(null));
  }

  @Override
  @GetMapping("/tpv/operations/{requestId}")
  public ResponseEntity<OperationsTpvDTO> getTpvOperations(@PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getOperationsTpv(null));
  }

  @Override
  @GetMapping("/tpv/transactions/{requestId}")
  public ResponseEntity<TransactionsTpvDTO> getTpvTransactions(@PathVariable("requestId") String requestId) {

    return ResponseEntity.ok(nuekService.getTransactionsTpv(null));
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
