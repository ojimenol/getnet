package com.santander.getnet.nuek.auth.client.model.api;

import com.santander.getnet.nuek.auth.client.autoconfig.NuekAuthClientAutoConfiguration;
import com.santander.getnet.nuek.auth.client.model.data.CredentialsRequest;
import com.santander.getnet.nuek.auth.client.model.data.CredentialsRequestIdAttributes;
import com.santander.getnet.nuek.auth.client.model.data.PostGenerateSASTokens200Response;
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
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {NuekAuthClientAutoConfiguration.class})
@EnableWireMock({@ConfigureWireMock(name = "my-nuekauthapi-mock")})
class NuekAuthApiTest {

    @Autowired
    private Environment environment;

    @Autowired
    private NuekAuthApi nuekAuthApi;

    @BeforeEach
    void setUp() {
        Optional.of("wiremock.server.port")
            .filter(environment::containsProperty)
            .map(environment::getProperty)
            .map(port -> UriComponentsBuilder
                .fromUriString(this.nuekAuthApi.getApiClient().getBasePath())
                .port(port)
                .build()
                .toUriString())
            .ifPresent(this.nuekAuthApi.getApiClient()::setBasePath);
    }

    @Test
    void nuekApiOkTest() throws Exception {

        var mockResponse = new PostGenerateSASTokens200Response()
            .jwt(UUID.randomUUID().toString())
            .tokenCorp(UUID.randomUUID().toString())
            .cookieCorp(UUID.randomUUID().toString());

        var jsonResponse = nuekAuthApi.getApiClient().getObjectMapper().writeValueAsString(mockResponse);

        stubFor(post(urlPathMatching("/sas/authenticate/credentials.*"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(jsonResponse)));

        var response = nuekAuthApi.postGenerateSASTokens(new HttpHeaders(), buildCredentials("", "", UUID.randomUUID().toString()))
            .blockOptional()
            .orElse(null);

        assertNotNull(response);

        assertNotNull(response.getJwt());
        assertNotNull(response.getCookieCorp());
        assertNotNull(response.getTokenCorp());

        assertEquals(mockResponse.getJwt(), response.getJwt());
        assertEquals(mockResponse.getTokenCorp(), response.getTokenCorp());
        assertEquals(mockResponse.getCookieCorp(), response.getCookieCorp());
    }

    @Test
    void nuekApiRetryTest() {

        stubFor(post(urlPathMatching("/sas/authenticate/credentials.*"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")));

        assertThrows(RuntimeException.class, () -> nuekAuthApi.postGenerateSASTokens(new HttpHeaders(), buildCredentials("", "", UUID.randomUUID().toString()))
            .blockOptional());

        verify(3, postRequestedFor(urlPathMatching("/sas/authenticate/credentials.*")));

    }

    private CredentialsRequest buildCredentials(String realm, String pwd, String uid) {
        return new CredentialsRequest()
            .realm(realm)
            .password(pwd)
            .idAttributes(new CredentialsRequestIdAttributes().uid(uid));

    }
}