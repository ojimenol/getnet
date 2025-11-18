package com.santander.getnet.jwt.handler;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;
import java.util.Optional;

public class JwtCrypto {

    public String buildJWEfromJWT(JWTClaimsSet jwt) {
        KeyHandler keyHandler = new KeyHandler().buildRSAKeyPair();

        System.out.println("--------------------------");
        System.out.println("Claim Set : \n" + jwt);

        // create the JWT header and specify:
        //  RSA-OAEP as the encryption algorithm
        //  128-bit AES/GCM as the encryption method
        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_512, EncryptionMethod.A256GCM);

        var keys = new KeyHandler().buildRSAKeyPair();

        var jwe = Optional.of(jwt)
            .map(token -> new EncryptedJWT(header, token))
            .map(jwtEnc -> encrypt(jwtEnc, keys))
            .orElse(null);

        // serialize to JWT compact form
        String jwtString = jwe.serialize();
        System.out.println("\nJwt Compact Form : " + jwtString);

        // in order to read back the data from the token using your private RSA key:
        // parse the JWT text string using EncryptedJWT object
        try {
            jwe = EncryptedJWT.parse(jwtString);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        // do the decryption
        decrypt(jwe, keys);

        print(jwe);

        return jwe.getParsedString();
    }

    private EncryptedJWT encrypt (EncryptedJWT jwe, KeyHandler keys) {
        Optional.of(keys)
            .map(keyHandler -> keyHandler.publicRsaKey)
            .map(RSAEncrypter::new)
            .ifPresent(encrypter -> this.encrypt(jwe, encrypter));

        return jwe;
    }

    private void encrypt(EncryptedJWT jwe, JWEEncrypter encrypter) {
        try {
            jwe.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private void decrypt (EncryptedJWT jwe, KeyHandler keys) {
        Optional.of(keys)
            .map(keyHandler -> keyHandler.privateRsaKey)
            .map(RSADecrypter::new)
            .ifPresent(decrypter -> this.decrypt(jwe, decrypter));
    }

    private void decrypt(EncryptedJWT jwe, JWEDecrypter decrypter) {
        try {
            jwe.decrypt(decrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private void print(EncryptedJWT jwe) {
        try {
            // print out the claims
            System.out.println("===========================================================");
            System.out.println("Issuer: [ " + jwe.getJWTClaimsSet().getIssuer() + "]");
            System.out.println("Subject: [" + jwe.getJWTClaimsSet().getSubject() + "]");
            System.out.println("Audience size: [" + jwe.getJWTClaimsSet().getAudience().size() + "]");
            System.out.println("Expiration Time: [" + jwe.getJWTClaimsSet().getExpirationTime() + "]");
            System.out.println("Not Before Time: [" + jwe.getJWTClaimsSet().getNotBeforeTime() + "]");
            System.out.println("Issue At: [" + jwe.getJWTClaimsSet().getIssueTime() + "]");
            System.out.println("JWT ID: [" + jwe.getJWTClaimsSet().getJWTID() + "]");
            System.out.println("===========================================================");
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
