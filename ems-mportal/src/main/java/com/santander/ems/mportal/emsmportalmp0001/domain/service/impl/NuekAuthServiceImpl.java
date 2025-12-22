package com.santander.ems.mportal.emsmportalmp0001.domain.service.impl;

import com.nimbusds.jwt.JWTParser;
import com.santander.ems.mportal.emsmportalmp0001.domain.mapper.JWERequestMapper;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.NuekRequestDTO;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWERequest;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.cos.JWEToken;
import com.santander.ems.mportal.emsmportalmp0001.domain.model.sas.SasToken;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.NuekAuthService;
import com.santander.ems.mportal.emsmportalmp0001.domain.service.TokenService;
import com.santander.getnet.nuek.auth.client.model.api.NuekAuthApi;
import com.santander.getnet.nuek.auth.client.model.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NuekAuthServiceImpl implements NuekAuthService {

    private static final Logger LOG = LoggerFactory.getLogger(NuekAuthServiceImpl.class);

    private static final int JWE_EXPIRATION_SECONDS = 60;

    private final NuekAuthApi nuekClient;
    private final JWERequestMapper jweRequestMapper;
    private final TokenService tokenService;

    public NuekAuthServiceImpl(NuekAuthApi nuekClient, JWERequestMapper jweRequestMapper, TokenService tokenService) {
        this.nuekClient = nuekClient;
        this.jweRequestMapper = jweRequestMapper;
        this.tokenService = tokenService;
    }

    public SasToken getSasToken(String id, String pwd, String realm) {
        if (tokenService.tokenExistsAndNotExpired("jwt")) {
            LOG.info("Returning reused JWT");
            return tokenService.getToken("jwt", SasToken.class);
        }

        LOG.info("Calling sas to get JWT");

        final var httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON.toString()));

        var sasToken = Optional.of(new CredentialsRequestIdAttributes().uid(id))
            .map(idAttr -> new CredentialsRequest().idAttributes(idAttr).password(pwd).realm(realm))
            .flatMap(req -> nuekClient.postGenerateSASTokens(httpHeaders, req).blockOptional())
            .map(this::toSasToken)
            .map(this::setJWTExpirationDate)
            .orElse(null);

        if (Objects.nonNull(sasToken)) {
            tokenService.putToken("jwt", sasToken);
            LOG.info("SasToken call OK");
        }

        return sasToken;
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

    public JWEToken getJWEToken4GroupedBilling(NuekRequestDTO requestDTO) {
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

    public JWEToken getJWEToken4CommerceTpvs(NuekRequestDTO requestDTO) {
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

    public JWEToken getJWEToken4TpvOperations(NuekRequestDTO requestDTO) {
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

    public JWEToken getJWEToken4TpvTransactions(NuekRequestDTO requestDTO) {
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
                .expiration(JWE_EXPIRATION_SECONDS)
                .payload(payload)
                .build();
    }

    private JWEToken getJWEToken(String jwt, JWERequest request) {
        var tokenKey = "jwe" + request.hashCode();

        if (tokenService.tokenExistsAndNotExpired(tokenKey)) {
            LOG.info("Returning reused JWE for " + tokenKey);
            return tokenService.getToken(tokenKey, JWEToken.class);
        }
        LOG.info("Calling cos to get JWE");

        final var httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.ACCEPT, List.of(MediaType.APPLICATION_JSON.toString()));
        httpHeaders.put(HttpHeaders.CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON.toString()));
        httpHeaders.put("X-Clientid", List.of("darwin"));
        httpHeaders.put(HttpHeaders.AUTHORIZATION, List.of("Bearer " + jwt));
        httpHeaders.put("Authentication", List.of("Bearer " + jwt));

        var preRequestTime = Instant.now();
        var jwe = Optional.of(request)
           .map(jweRequestMapper::jweRequestToJweClientRequest)
           .flatMap(req -> nuekClient.postJWEGenerateToken(httpHeaders, req).blockOptional())
           .map(PostJWEGenerateToken200Response::getJwe)
           .map(jweRequestMapper::toJweToken)
           .map(token -> this.setJWEExpirationDate(token, preRequestTime, JWE_EXPIRATION_SECONDS))
           .orElse(null);

        if (Objects.nonNull(jwe)) {
            tokenService.putToken("jwe" + request.hashCode(), jwe);
            LOG.info("JWEToken call OK");
        }
        return jwe;
    }

    private SasToken setJWTExpirationDate(SasToken sas) {
        try {
            var jwtDecoded = JWTParser.parse(sas.getJwt());
            return sas.expirationDate(jwtDecoded.getJWTClaimsSet().getExpirationTime());
        } catch (Exception e) {
            sas.expirationDate(new Date());
            return sas;
        }
    }

    private JWEToken setJWEExpirationDate(JWEToken token, Instant start, long seconds) {
        return token.expirationDate(new Date(start.toEpochMilli() + (seconds * 1000)));
    }
}
