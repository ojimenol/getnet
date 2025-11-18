package com.santander.getnet.jwt.handler;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Optional;
import java.util.UUID;

public class JwtSigner {

    public String buildJWSfromJWT(JWTClaimsSet jwt) {
        KeyHandler keyHandler = new KeyHandler().buildRSAKeyPair();

        var header = new JWSHeader.Builder(JWSAlgorithm.RS256)
            .type(JOSEObjectType.JWT)
            .keyID(UUID.randomUUID().toString())
            .build();

        return Optional.of(jwt)
            .map(token -> new SignedJWT(header, token))
            .map(this::sign)
            .map(JWSObject::serialize)
            .orElse(null);
    }

    private SignedJWT sign (SignedJWT jwt) {
        Optional.of(new KeyHandler())
            .map(KeyHandler::buildRSAKeyPair)
            .map(keyHandler -> keyHandler.privateRsaKey)
            .map(RSASSASigner::new)
            .ifPresent(signer -> this.sign(jwt, signer));

        return jwt;
    }

    private void sign(SignedJWT jwt, RSASSASigner signer) {
        try {
            jwt.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}