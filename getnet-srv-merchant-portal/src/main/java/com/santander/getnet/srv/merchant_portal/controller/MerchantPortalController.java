package com.santander.getnet.srv.merchant_portal.controller;

import com.santander.getnet.srv.merchant_portal.dto.GroupedBillingDTO;
import com.santander.getnet.srv.merchant_portal.dto.CommercesDTO;
import com.santander.getnet.srv.merchant_portal.dto.CommercesTpvsDTO;
import com.santander.getnet.srv.merchant_portal.dto.OperationsTpvDTO;
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
      @Size(min = 1, max = 36) String requestId,
      @NotNull
      @Pattern(regexp = "^[a-zA-Z_0-9 ]*$")
      @Size(min = 1, max = 25) String personCode,
      @NotNull
      @Pattern(regexp = "^[a-zA-Z]*$")
      @Size(min = 1, max = 5) String personType,
      @NotNull
      @Pattern(regexp = "^ASC|DSC*$")
      @Size(min = 3, max = 3) String order);

    ResponseEntity<GroupedBillingDTO> getGroupedBilling(
        @NotNull
        @Pattern(regexp = UUID_PATTERN)
        @Size(min = 2, max = 36) String xClientId,
        @Pattern(regexp = "^[a-zA-Z_0-9 ]*$")
        @Size(min = 1, max = 35) String paymentId);

    ResponseEntity<CommercesTpvsDTO> getTpvsCommerce(
        @NotNull
        @Pattern(regexp = UUID_PATTERN)
        @Size(min = 2, max = 36) String xClientId,
        @Pattern(regexp = "^[a-zA-Z_0-9 ]*$")
        @Size(min = 1, max = 35) String paymentId);

    ResponseEntity<OperationsTpvDTO> getTpvOperations(
        @NotNull
        @Pattern(regexp = UUID_PATTERN)
        @Size(min = 2, max = 36) String xClientId,
        @Pattern(regexp = "^[a-zA-Z_0-9 ]*$")
        @Size(min = 1, max = 35) String paymentId);
}
