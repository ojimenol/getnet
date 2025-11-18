package com.santander.getnet.jwt.handler;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class KeyHandler {

    RSAPublicKeySpec publicKeySpec;
    RSAPrivateKeySpec privateKeySpec;
    RSAPublicKey publicRsaKey;
    RSAPrivateKey privateRsaKey;

    public KeyHandler buildRSAKeyPair () {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(2048);

            // generate the key pair
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            // create KeyFactory and RSA Keys Specs
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
            privateKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);

            // generate (and retrieve) RSA Keys from the KeyFactory using Keys Specs
            publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            privateRsaKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

            return this;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
