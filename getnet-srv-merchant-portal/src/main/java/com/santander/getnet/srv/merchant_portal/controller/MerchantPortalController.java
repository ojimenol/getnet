package com.santander.getnet.srv.merchant_portal.controller;

import com.santander.getnet.srv.merchant_portal.dto.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;

public interface MerchantPortalController {

    String UUID_PATTERN =
      "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    ResponseEntity<CommercesDTO> getCommerces(
      @NotNull
      @Pattern(regexp = "^[a-zA-Z_0-9\\-]*$")
      @Size(min = 1, max = 36) String requestId);

    ResponseEntity<GroupedBillingDTO> getGroupedBilling(
        @NotNull
        @Pattern(regexp = "^[a-zA-Z_0-9\\-]*$")
        @Size(min = 1, max = 36) String requestId);

    ResponseEntity<CommerceTpvsDTO> getTpvsCommerce(
        @NotNull
        @Pattern(regexp = "^[a-zA-Z_0-9\\-]*$")
        @Size(min = 1, max = 36) String requestId);

    ResponseEntity<OperationsTpvDTO> getTpvOperations(
        @NotNull
        @Pattern(regexp = "^[a-zA-Z_0-9\\-]*$")
        @Size(min = 1, max = 36) String requestId);

    ResponseEntity<TransactionsTpvDTO> getTpvTransactions(
            @NotNull
            @Pattern(regexp = "^[a-zA-Z_0-9\\-]*$")
            @Size(min = 1, max = 36) String requestId);
}
