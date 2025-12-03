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

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MerchantPortalControllerImpl implements MerchantPortalController {

  private final NuekService nuekService;

  private final MerchantPortalControllerErrorHandler errorHandler;

  @Override
  @GetMapping("/v2/commerces/{requestId}")
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
  @GetMapping("/v2/groupedBilling/{requestId}")
  public ResponseEntity<GroupedBillingDTO> getGroupedBilling(@PathVariable("requestId") String requestId) {

    var request = NuekRequestDTO.builder()
            .requestId(requestId)
            .personCode("884407")
            .personType("F")
            .listDateFrom("20251001")
            .listDateTo("20251031")
            .order("D")
            .build();

    return ResponseEntity.ok(nuekService.getGroupedBilling(request));
  }

  @Override
  @GetMapping("/v2/commerce/tpv/{requestId}")
  public ResponseEntity<CommerceTpvsDTO> getTpvsCommerce(@PathVariable("requestId") String requestId) {

    var request = NuekRequestDTO.builder()
            .requestId(requestId)
            .personCode("884407")
            .personType("F")
            .commerceCode("0038833422")
            .dateFrom("20250501")
            .dateTo("20251024")
            .listDateFrom("20251001")
            .listDateTo("20251031")
            .order("D")
            .build();

    return ResponseEntity.ok(nuekService.getCommercesTpv(request));
  }

  @Override
  @GetMapping("/v2/tpv/operations/{requestId}")
  public ResponseEntity<OperationsTpvDTO> getTpvOperations(@PathVariable("requestId") String requestId) {

      var request = NuekRequestDTO.builder()
            .commerceContract("004918125240196905")
            .dateFrom("20251001")
            .dateTo("20251024")
            .order("D")
            .build();

      return ResponseEntity.ok(nuekService.getOperationsTpv(request));
  }

  @Override
  @GetMapping("/v2/tpv/transactions/{requestId}")
  public ResponseEntity<TransactionsTpvDTO> getTpvTransactions(@PathVariable("requestId") String requestId) {

    var request = NuekRequestDTO.builder()
            .commerceContract("004918125240196905")
            .properties(Map.of("valueDate", "20251017", "totalDate", "20251016", "totalOrder", "662530"))
            .build();

    return ResponseEntity.ok(nuekService.getTransactionsTpv(request));
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
