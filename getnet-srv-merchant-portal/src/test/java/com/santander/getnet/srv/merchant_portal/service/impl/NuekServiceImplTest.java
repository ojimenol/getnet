package com.santander.getnet.srv.merchant_portal.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
class NuekServiceImplTest {

  @Mock
  WebClient webClient;

  @InjectMocks
  NuekServiceImpl nuekService;

  @BeforeEach
  public void setUp() {
  }

  /*@Test
  void call_getComercios() {
    final var pacs002v10 = new Pacs002v10();
    when(webClient.post())
        .thenReturn(new CommercesDTO());

    StepVerifier.create(paymentService.getPaymentStatus("BIC", "INSTRUCTION-ID"))
        .expectNext(pacs002v10)
        .verifyComplete();
  }

  @Test
  void call_getStatusDetailed() {
    final var pacs002v10 = new Pacs002v10();
    when(paymentStatusClient.getPacs002v10Detail(anyString(), anyString()))
        .thenReturn(Mono.just(pacs002v10));

    StepVerifier.create(paymentService.getDetailedPaymentStatus("BIC", "INSTRUCTION-ID"))
        .expectNext(pacs002v10)
        .verifyComplete();
  }*/
}