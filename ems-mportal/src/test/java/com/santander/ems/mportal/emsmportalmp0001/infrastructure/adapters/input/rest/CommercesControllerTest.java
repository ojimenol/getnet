package com.santander.ems.mportal.emsmportalmp0001.infrastructure.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.Commerces;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEEncryptResponse;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasResponse;
import com.santander.getnet.nuek.client.model.data.Commerce;
import com.santander.getnet.nuek.client.model.data.CommercesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(properties = {
    "app.nuek.commerces.url=http://localhost:${wiremock.server.port}",
    "app.nuek.auth.url=http://localhost:${wiremock.server.port}"})
@AutoConfigureMockMvc
@WithMockUser
@EnableWireMock({@ConfigureWireMock(name = "my-apis-mock")})
class CommercesControllerTest {

    @Autowired
    private Environment environment;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCallCommerceEndpoint() throws Exception {

        var sasResponse = SasResponse.builder().jwt("anyJwt").build();
        var jsonSasResponse = new ObjectMapper().writeValueAsString(sasResponse);

        stubFor(WireMock.post(urlPathEqualTo("/sas/authenticate/credentials"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonSasResponse)));

        var cosResponse = JWEEncryptResponse.builder().jwe("anyJwe").build();
        var jsonCosResponse = new ObjectMapper().writeValueAsString(cosResponse);

        stubFor(WireMock.post(urlPathEqualTo("/cos/jwe/scos/generate"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonCosResponse)));
        var commercesApiResponse = buildCommercesResponse();

        var jsonApiCommercesResponse = new ObjectMapper()
            //.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
            .writeValueAsString(commercesApiResponse);

        var jsonMvcCommercesResponse = new ObjectMapper()
                //.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                .writeValueAsString(buildMvcCommercesResponse(commercesApiResponse));

        stubFor(WireMock.get(urlPathMatching("/api/Comercios/commerces.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonApiCommercesResponse)));

        mockMvc.perform(get("/ems-mportal-mp0001/v2/commerce")
                    .header("Authorization","auth")
                    .param("personCode", "personCode")
                    .param("personType", "personType")
                    .param("billingDateFrom", "billingDateFrom")
                    .param("billingDateTo", "billingDateTo")
                    .param("order", "order")
                    .param("listDateFrom", "listDateFrom")
                    .param("listDateTo", "listDateTo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonMvcCommercesResponse));
    }

    private CommercesResponse buildCommercesResponse() {
        var commerce = new Commerce()
            .commerceCode("commerceCode");

        return new CommercesResponse()
            .commerceList(List.of(commerce));
    }

    private Commerces buildMvcCommercesResponse(CommercesResponse response) {
        return Stream.ofNullable(response)
            .map(CommercesResponse::getCommerceList)
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .map(Commerce::getCommerceCode)
            .map(Commerces.CommerceDTO.builder()::commerceCode)
            .map(Commerces.CommerceDTO.CommerceDTOBuilder::build)
            .map(List::of)
            .map(Commerces.builder()::commerces)
            .map(Commerces.CommercesBuilder::build)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Can't create Commerces Response"));
    }
}

