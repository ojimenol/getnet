package com.santander.getnet.nuek.client.model.api;

import com.santander.getnet.nuek.client.autoconfig.NuekClientAutoConfiguration;
import com.santander.getnet.nuek.client.model.data.Commerce;
import com.santander.getnet.nuek.client.model.data.CommercesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {NuekClientAutoConfiguration.class})
@EnableWireMock({@ConfigureWireMock(name = "my-nuekapi-mock")})
class NuekApiTest {

    @Autowired
    private Environment environment;

    @Autowired
    private NuekApi nuekApi;

    @BeforeEach
    void setUp() {
        Optional.of("wiremock.server.port")
            .filter(environment::containsProperty)
            .map(environment::getProperty)
            .map(port -> UriComponentsBuilder
                .fromUriString(this.nuekApi.getApiClient().getBasePath())
                .port(port)
                .build()
                .toUriString())
            .ifPresent(this.nuekApi.getApiClient()::setBasePath);
    }

    @Test
    void nuekApiOkTest() {

        final var commerceCode = "A000";
        final var commerceName = "TestCommerce";

        var mockCommerce = new Commerce().commerceCode(commerceCode).commerceName(commerceName);
        var mockResponse = new CommercesResponse().commerceList(List.of(mockCommerce));

        var jsonResponse = nuekApi.getApiClient().getJsonMapper().writeValueAsString(mockResponse);

        stubFor(get(urlPathMatching("/api/Comercios/commerces.*"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(jsonResponse)));

        var response = nuekApi.getComercios(new HttpHeaders(), "", "", "", "", "", "", "")
                .blockOptional()
                .orElse(null);

        assertNotNull(response);
        assertNotNull(response.getCommerceList());
        assertEquals(1, response.getCommerceList().size());

        var commerce = response.getCommerceList().get(0);
        assertNotNull(commerce);

        assertNotNull(commerce.getCommerceCode());
        assertEquals(commerceCode, commerce.getCommerceCode());
        assertNotNull(commerce.getCommerceName());
        assertEquals(commerceName, commerce.getCommerceName());

    }

    @Test
    void nuekApiRetryTest() {

        stubFor(get(urlPathMatching("/api/Comercios/commerces.*"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")));

        assertThrows(RuntimeException.class, () -> nuekApi.getComercios(new HttpHeaders(), "", "", "", "", "", "", "")
                .blockOptional());

        verify(3, getRequestedFor(urlPathMatching("/api/Comercios/commerces.*")));

    }
}