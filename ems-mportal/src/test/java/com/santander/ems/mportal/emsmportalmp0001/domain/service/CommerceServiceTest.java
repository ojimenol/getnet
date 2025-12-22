package com.santander.ems.mportal.emsmportalmp0001.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.command.rest.CommercesGetCommand;
import com.santander.ems.mportal.emsmportalmp0001.domain.mapper.NuekApiMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerces;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.NuekRequestDTO;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEEncryptResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEToken;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.PayloadCommerceRequest;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.impl.NuekServiceImpl;
import com.santander.ems.mportal.emsmportalmp0001.infrastructure.config.NuekProperties;
import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.nuek.client.model.data.Commerce;
import com.santander.getnet.nuek.client.model.data.CommercesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.http.HttpHeaders;
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
    NuekApi nuekApi;

    @Mock
    NuekApiMapper nuekApiMapper;

    @Mock
    NuekAuthService nuekAuthService;

    private NuekService nuekService;

    @BeforeEach
    void setUp() throws Exception {
        var nuekProps = new NuekProperties();
        nuekProps.setCommerces("mock.commerces-url");

        var mockCommerce = Commerces.builder()
                .commerces(List.of(Commerces.CommerceDTO.builder()
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

        var jweResponse = JWEEncryptResponse.builder().jwe("anyJwe").build();

        nuekService = new NuekServiceImpl(nuekApi, nuekApiMapper, nuekAuthService);
    }

    @Test
    public void testGetCommerces() throws Exception {

        var mockResponse = Mono.just("code")
            .map(new Commerce()::commerceCode)
            .map(List::of)
            .map(new CommercesResponse()::commerceList);

        when(nuekAuthService.getJWEToken4Commerces(any(NuekRequestDTO.class)))
            .thenReturn(JWEToken.builder().jwe("anyJwe").build());

        when(nuekApi.getComercios(any(HttpHeaders.class),
                anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn(mockResponse);

        when(nuekApiMapper.toCommerceDTO(any(Commerce.class)))
            .thenReturn(Commerces.CommerceDTO.builder().commerceCode("code").build());

        var response = nuekService.getCommerces(NuekRequestDTO.builder()
            .personCode("")
            .personType("")
            .dateFrom("")
            .dateTo("")
            .order("")
            .listDateFrom("")
            .listDateTo("")
            .build());

        assertNotNull(response);
        var commerces = response.getCommerces();
        assertEquals(1, commerces.size());
        assertEquals("code", commerces.get(0).getCommerceCode());
    }
}
