package com.santander.getnet.srv.merchant_portal.service.impl;

import com.santander.getnet.nuek.client.model.api.NuekApi;
import com.santander.getnet.nuek.client.model.data.*;
import com.santander.getnet.srv.merchant_portal.mapper.JWERequestMapper;
import com.santander.getnet.srv.merchant_portal.model.JWERequest;
import com.santander.getnet.srv.merchant_portal.model.JWEToken;
import com.santander.getnet.srv.merchant_portal.model.SasToken;
import com.santander.getnet.srv.merchant_portal.service.NuekAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

public class NuekAuthServiceImpl implements NuekAuthService {
    private static final Logger log = LoggerFactory.getLogger(NuekAuthServiceImpl.class);

    private final NuekApi nuekClient;
    private final JWERequestMapper jweRequestMapper;

    public NuekAuthServiceImpl(NuekApi nuekClient, JWERequestMapper jweRequestMapper) {

        this.nuekClient = nuekClient;
        this.jweRequestMapper = jweRequestMapper;
    }

    public SasToken getSasToken(String id, String pwd, String realm) {

        return Optional.of(new CredentialsRequestIdAttributes().uid(id))
            .map(idAttr -> new CredentialsRequest().idAttributes(idAttr).password(pwd).realm(realm))
            .flatMap(req -> nuekClient.postGenerateSASTokens(new HttpHeaders(), req).blockOptional())
            .map(this::toSasToken)
            .orElse(null);
    }

    private SasToken toSasToken(PostGenerateSASTokens200Response sasResponse) {
        return SasToken.builder()
                .tokenCorp(sasResponse.getTokenCorp())
                .cookieCorp(sasResponse.getCookieCorp())
                .jwt(sasResponse.getJwt())
            .build();
    }

    public JWEToken getJWEToken(JWERequest request) {

        return Optional.of(request)
           .map(jweRequestMapper::jweRequestToJweClientRequest)
           .flatMap(req -> nuekClient.postJWEGenerateToken(new HttpHeaders(), req).blockOptional())
           .map(PostJWEGenerateToken200Response::getJwe)
           .map(jweRequestMapper::toJweToken)
           .orElse(null);
    }
}
