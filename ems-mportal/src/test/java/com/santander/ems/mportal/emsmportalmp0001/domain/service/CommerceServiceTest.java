package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerce;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.CommerceListResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEEncryptResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.PayloadCommerceRequest;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.impl.CommerceServiceImpl;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.config.NuekProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
public class CommerceServiceTest {

    @Mock
    WebClient.Builder webClientBuilder;

    @Mock
    CosService cosService;

    private CommerceService commerceService;

    @BeforeEach
    void setUp() throws Exception {
        var nuekProps = new NuekProperties();
        nuekProps.setCommerces("mock.commerces-url");

        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);

        var mockCommerce = CommerceListResponse.builder()
                .commerceList(List.of(Commerce.builder()
                        .commerceCode("code")
                        .build()))
                .build();

        var jsonCommerces = new ObjectMapper().writeValueAsString(mockCommerce);

        var webClientMock = WebClient.builder()
                .exchangeFunction(clientRequest ->
                        Mono.just(ClientResponse.create(HttpStatus.OK)
                                .header("content-type", "application/json")
                                .body(jsonCommerces)
                                .build()))
                .build();

        when(webClientBuilder.build()).thenReturn(webClientMock);

        var jweResponse = JWEEncryptResponse.builder().jwe("anyJwe").build();
        when(cosService.generateJwe(any(PayloadCommerceRequest.class))).thenReturn(jweResponse);

        commerceService = new CommerceServiceImpl(webClientBuilder, nuekProps, cosService);
    }

    @Test
    public void testGetCommerces() throws Exception {

        var commerces = commerceService.getCommerces("","","","","","","");

        assertNotNull(commerces);
        assertEquals(1, commerces.size());
        assertEquals("code", commerces.get(0).getCommerceCode());
    }
}
