package com.santander.getnet.srv.merchant_portal.controller.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santander.getnet.srv.merchant_portal.dto.CommercesDTO;
import com.santander.getnet.srv.merchant_portal.dto.NuekRequestDTO;
import com.santander.getnet.srv.merchant_portal.service.NuekService;
import com.santander.getnet.srv.merchant_portal.utils.TestUtils;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import tools.jackson.databind.json.JsonMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import({MerchantPortalControllerErrorHandler.class})
class MerchantPortalControllerImplTest {

  @Autowired
  private WebTestClient webClient;

  @Autowired
  JsonMapper jsonMapper;

  @MockitoBean
  private NuekService nuekService;

  @Test
  void getCommerces() throws IOException {

    final var request = TestUtils.readObjectFromResources("files/request_commerces.json",
        jsonMapper, NuekRequestDTO.class);

    var mockedResult = CommercesDTO.builder().build();

    when(nuekService.getCommerces(any(NuekRequestDTO.class)))
        .thenReturn(mockedResult);

    var inParams = Map.of(
        "requestId", UUID.randomUUID().toString(),
        "personCode", UUID.randomUUID().toString().replaceAll("-", "").substring(0,25),
        "personType", "any",
        "order", "DSC"
    );

    final var result = webClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/commerces/{requestId}")
            .build(inParams))
        .accept(MediaType.APPLICATION_JSON)
        .header("x-sub-id", "id")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CommercesDTO.class)
        .returnResult();

    verify(nuekService).getCommerces(any(NuekRequestDTO.class));

    assertEquals(mockedResult, result.getResponseBody());

  }
}