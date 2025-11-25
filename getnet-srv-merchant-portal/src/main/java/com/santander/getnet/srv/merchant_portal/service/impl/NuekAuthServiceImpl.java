package com.santander.getnet.srv.merchant_portal.service.impl;

import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.nuek.client.model.data.*;
import com.santander.getnet.srv.merchant_portal.dto.NuekRequestDTO;
import com.santander.getnet.srv.merchant_portal.mapper.JWERequestMapper;
import com.santander.getnet.srv.merchant_portal.model.JWERequest;
import com.santander.getnet.srv.merchant_portal.model.JWEToken;
import com.santander.getnet.srv.merchant_portal.model.SasToken;
import com.santander.getnet.srv.merchant_portal.service.NuekAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class NuekAuthServiceImpl implements NuekAuthService {

    private final NuekApi nuekClient;
    private final JWERequestMapper jweRequestMapper;

    public NuekAuthServiceImpl(NuekApi nuekClient, JWERequestMapper jweRequestMapper) {

        this.nuekClient = nuekClient;
        this.jweRequestMapper = jweRequestMapper;
    }

    public SasToken getSasToken(String id, String pwd, String realm) {
        final var httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON.toString()));

        return Optional.of(new CredentialsRequestIdAttributes().uid(id))
            .map(idAttr -> new CredentialsRequest().idAttributes(idAttr).password(pwd).realm(realm))
            .flatMap(req -> nuekClient.postGenerateSASTokens(httpHeaders, req).blockOptional())
            .map(this::toSasToken)
            .map(SasToken::init)
            .map(token -> token.expiration(this.getExpiration(token)))
            .orElse(null);
    }

    private int getExpiration(SasToken sasToken) {
        String token = sasToken.getJwt();
        String[] parts = token.split("\\.");

        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        System.out.println("Header: " + header);
        System.out.println("Payload: " + payload);
        return 0;
    }

    private SasToken toSasToken(PostGenerateSASTokens200Response sasResponse) {
        return SasToken.builder()
                .tokenCorp(sasResponse.getTokenCorp())
                .cookieCorp(sasResponse.getCookieCorp())
                .jwt(sasResponse.getJwt())
            .build();
    }

    public JWEToken getJWEToken4Commerces(NuekRequestDTO requestDTO) {
        var payload = JWERequest.JWEPayloadRequest.builder()
            .personType("F")
            .personCode("884407")
            .billingDateFrom("20250501")
            .billingDateTo("20251024")
            .listDateFrom("20251001")
            .listDateTo("20251031")
            .order("D")
            .build();

        return Optional.of(getSasTokenJwt())
            .map(jwt -> getJWEToken(jwt, buildJWERequest(payload)))
            .orElse(null);
    }

    public JWEToken getJWEToken4GroupedBilling() {
        var payload = JWERequest.JWEPayloadRequest.builder()
                .personType("F")
                .personCode("884407")
                .listDateFrom("20251001")
                .listDateTo("20251031")
                .build();

        return Optional.of(getSasTokenJwt())
                .map(jwt -> getJWEToken(jwt, buildJWERequest(payload)))
                .orElse(null);
    }

    public JWEToken getJWEToken4CommerceTpvs() {
        var payload = JWERequest.JWEPayloadRequest.builder()
                .personType("F")
                .personCode("884407")
                .commerceCode("0038833422")
                .billingDateFrom("20250501")
                .billingDateTo("20251024")
                .listDateFrom("20251001")
                .listDateTo("20251031")
                .order("D")
                .build();

        return Optional.of(getSasTokenJwt())
                .map(jwt -> getJWEToken(jwt, buildJWERequest(payload)))
                .orElse(null);
    }

    public JWEToken getJWEToken4TpvOperations() {
        var payload = JWERequest.JWEPayloadRequest.builder()
                .commerceContract("004918125240196905")
                .order("D")
                .dateFrom("20251001")
                .dateTo("20251024")
                .build();

        return Optional.of(getSasTokenJwt())
                .map(jwt -> getJWEToken(jwt, buildJWERequest(payload)))
                .orElse(null);
    }

    public JWEToken getJWEToken4TpvTransactions() {
        var payload = JWERequest.JWEPayloadRequest.builder()
                .commerceContract("004918125240196905")
                .valueDate("20251017")
                .totalDate("20251016")
                .totalOrder("662530")
                .build();

        return Optional.of(getSasTokenJwt())
                .map(jwt -> getJWEToken(jwt, buildJWERequest(payload)))
                .orElse(null);
    }

    private String getSasTokenJwt() {
        return getSasToken("aplcmce", "KCvtuy34#", "SantanderBCE").getJwt();
    }

    private JWERequest buildJWERequest(JWERequest.JWEPayloadRequest payload) {
        return JWERequest.builder()
                .keyalias("admisionemp_pre")
                .expiration(60)
                .payload(payload)
                .build();
    }

    private JWEToken getJWEToken(String jwt, JWERequest request) {

        final var httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + jwt));
        httpHeaders.put("Authentication", List.of("Bearer " + jwt));

        return Optional.of(request)
           .map(jweRequestMapper::jweRequestToJweClientRequest)
           .flatMap(req -> nuekClient.postJWEGenerateToken(httpHeaders, req).blockOptional())
           .map(PostJWEGenerateToken200Response::getJwe)
           .map(jweRequestMapper::toJweToken)
           .orElse(null);
    }
}
